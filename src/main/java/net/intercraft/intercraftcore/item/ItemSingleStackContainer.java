package net.intercraft.intercraftcore.item;

import io.netty.buffer.Unpooled;
import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftDamageSources;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.init.capabilities.stackContainer.StackContainerProvider;
import net.intercraft.intercraftcore.inventory.container.ContainerSingleItemStackContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSingleStackContainer extends Item
{

    public static final ResourceLocation open = new ResourceLocation(Reference.MODID,"open");

    private final float isolation;
    private final int tint;

    private boolean isOpen = false;


    //private final ItemStackHandler stackHandler;

    /**
     * Item Constructor
     *
     * @param name the name of the item.
     * @param isolation how much radiation from the stack it holds is blocked. 0 = no block, 1 = full block.
     * @param tint the colour the second layer will be tinted with. -1 for no tint.
     */

    public ItemSingleStackContainer(Item.Properties properties,String name, float isolation, int tint)
    {
        super(properties.maxStackSize(1).group(IntercraftItemGroups.CONTAINERS));

        //this.stackHandler = new ItemStackHandler(1);

        if (isolation < 0 || isolation > 1) throw new IllegalArgumentException("Can only be between 0 and 1!");

        addPropertyOverride(open,(itemStack, worldIn, entityLivingBase) -> isOpen ? 1 : 0);

        this.isolation = isolation;
        this.tint      = tint;

        setRegistryName(name);
    }

    public ItemSingleStackContainer(Item.Properties properties,String name, float isolation)
    {
        this(properties,name,isolation,-1);
    }


    /*public ItemStackHandler getStackHandler()
    {
        return stackHandler;
    }*/

    public ItemStack getContainedItemStack(ItemStack stack)
    {
        IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        return handler.getStackInSlot(0);
        //return stackHandler.getStackInSlot(0);
    }

    /*protected INBT writeNBT(IItemHandler handler)
    {
        CompoundNBT toRet = new CompoundNBT();
        CompoundNBT stackW = new CompoundNBT();
        handler.getStackInSlot(0).write(stackW);
        toRet.put("Stack",stackW);
        //stack.setTag(toRet);
        return toRet;
    }

    protected void readNBT(IItemHandler handler,INBT inbt)
    {
        CompoundNBT nbt = (CompoundNBT)inbt;
        ((IItemHandlerModifiable) handler).setStackInSlot(0,ItemStack.read((CompoundNBT) nbt.get("Stack")));
    }*/



    public void setOpen(boolean open)
    {
        isOpen = open;
    }

    public boolean cycleOpen()
    {
        isOpen = !isOpen;
        return isOpen;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return new StackContainerProvider((short) 1);
    }


    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack)
    {
        IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("Slot",0);
        handler.getStackInSlot(0).write(nbt);
        System.out.println("Get");
        System.out.println("write: "+handler.getStackInSlot(0).getDisplayName().getFormattedText());
        System.out.println("supplied: "+stack.getDisplayName().getFormattedText());

        return nbt;

        //return (CompoundNBT) writeNBT(stackHandler);


    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        ItemStack readStack =  ItemStack.read(nbt);
        System.out.println("Read");
        System.out.println("read: "+readStack.getDisplayName().getFormattedText());
        System.out.println("supplied: "+stack.getDisplayName().getFormattedText());
        IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        ((IItemHandlerModifiable) handler).setStackInSlot(0,readStack);

        //readNBT(stackHandler,nbt);
    }

    @Override
    public boolean shouldSyncTag()
    {
        return true;
    }

    public boolean hasFluid()
    {
        return false;
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);

        /*IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        System.out.println(handler.getStackInSlot(0).getDisplayName().getFormattedText());*/

        if (playerIn.isSneaking()) {
            if (IntercraftCore.failedToOpen != 0)
                IntercraftCore.failedToOpen = 0;
            openContainer(playerIn);

            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        } else {
            String
                    sneakBtn = Minecraft.getInstance().gameSettings.keyBindSneak.getLocalizedName(),
                    useBtn = Minecraft.getInstance().gameSettings.keyBindUseItem.getLocalizedName(),
                    key = "info."+Reference.MODID+".tip.container.open";

            if (IntercraftCore.failedToOpen > 40) {
                // Anger the devs advancement.
                IntercraftDamageSources.killPlayer(playerIn,IntercraftDamageSources.DISOBEDIENCE);
                IntercraftCore.failedToOpen = 0;
            }
            else if (IntercraftCore.failedToOpen > 28) {
                playerIn.sendStatusMessage(new TranslationTextComponent(key+".baby"),true);
                openContainer(playerIn);
            }
            else if (IntercraftCore.failedToOpen > 20)
                playerIn.sendStatusMessage(new TranslationTextComponent(key+".feed_up",sneakBtn,useBtn),true);
            else if (IntercraftCore.failedToOpen > 12)
                playerIn.sendStatusMessage(new TranslationTextComponent(key +".annoyed",sneakBtn,useBtn),true);
            else if (IntercraftCore.failedToOpen > 4)
                playerIn.sendStatusMessage(new TranslationTextComponent(key,sneakBtn,useBtn),true);

            IntercraftCore.failedToOpen++;
        }

        return new ActionResult<>(ActionResultType.FAIL, stack);
        //return super.onItemRightClick(worldIn, playerIn, handIn);


    }

    protected void openContainer(PlayerEntity playerIn)
    {
        playerIn.openContainer(new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName()
            {
                return new TranslationTextComponent("container." + Reference.MODID + ".itemstack_storage." + getRegistryName().getPath());
            }

            @Nullable
            @Override
            public Container createMenu(int id, PlayerInventory inventory, PlayerEntity playerEntity)
            {
                PacketBuffer buffer = new PacketBuffer(Unpooled.buffer(4, 4).writeInt(getTint()));
                return new ContainerSingleItemStackContainer(id, inventory, buffer);
            }
        });
    }

    /*@Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack)
    {
        IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);

        System.out.println("Send share tag.");

        return null;
    }*/

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        ItemStack containedStack = getContainedItemStack(stack);
        String k = super.getTranslationKey();
        return containedStack == ItemStack.EMPTY ? k : I18n.format(k+".full", containedStack.getDisplayName().getFormattedText());
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public float getIsolation()
    {
        return isolation;
    }

    public int getTint()
    {
        return tint;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        TranslationTextComponent text = new TranslationTextComponent("info."+Reference.MODID+".tip.container.open",
                Minecraft.getInstance().gameSettings.keyBindSneak.getLocalizedName(),
                Minecraft.getInstance().gameSettings.keyBindUseItem.getLocalizedName());
        text.setStyle(new Style().setColor(TextFormatting.YELLOW));
        tooltip.add(text);

        //ItemStack stack1 = getContainedItemStack(stack);
        ItemStack stack1 = getContainedItemStack(stack);

        if (stack1 != ItemStack.EMPTY && stack1.getMaxStackSize() > 1)
            tooltip.add(new StringTextComponent("x"+stack1.getCount()).setStyle(new Style().setColor(TextFormatting.GRAY)));

    }

    /*public void setCointainedItemStack(NonNullList<ItemStack> stacks)
    {
        items = stacks;
    }*/
}
