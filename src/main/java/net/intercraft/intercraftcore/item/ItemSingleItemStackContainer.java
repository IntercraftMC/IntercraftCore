package net.intercraft.intercraftcore.item;

import io.netty.buffer.Unpooled;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftDamageSources;
import net.intercraft.intercraftcore.inventory.container.ContainerSingleItemStackContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemSingleItemStackContainer extends Item
{

    private static final ResourceLocation open = new ResourceLocation(Reference.MODID,"open");

    private final float isolation; // How much a radioactive material is being blocked while in here (0 = no blocking, 1 = full blocking).
    //private final short inventorySize; // 1 >= size >= 4. Not a replacement of Shulker Boxes.
    private final int tint;

    private static short failedToOpen = 0;
    private boolean isOpen = false;
    private NonNullList<ItemStack> items;


    public ItemSingleItemStackContainer(String name, float isolation, int tint)
    {
        super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1));

        addPropertyOverride(open,(itemStack, worldIn, entityLivingBase) -> isOpen ? 1 : 0);

        this.isolation     = isolation;
        this.tint          = tint;
        //this.items         = NonNullList.withSize(inventorySize, ItemStack.EMPTY);

        setRegistryName(name);
    }


    /*@Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        if (context.isPlacerSneaking()) {
            cycleOpen();
            // Open the gui.
        }

        return super.onItemUse(context);
    }*/

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote) {

            if (playerIn.isSneaking()) {
                if (failedToOpen != 0)
                    failedToOpen = 0;
                openContainer(playerIn);

                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            } else {
                String sneakBtn = Minecraft.getInstance().gameSettings.keyBindSneak.getLocalizedName(), useBtn = Minecraft.getInstance().gameSettings.keyBindUseItem.getLocalizedName();

                if (failedToOpen > 40) {
                    // Anger the devs advancement.

                    playerIn.attackEntityFrom(IntercraftDamageSources.DISOBEDIENCE, playerIn.getAbsorptionAmount() + playerIn.getHealth());
                    failedToOpen = 0;
                }
                else if (failedToOpen > 28) {
                    playerIn.sendStatusMessage(new TranslationTextComponent("info."+Reference.MODID+".tip.container.open.baby"),true);
                    openContainer(playerIn);
                }
                else if (failedToOpen > 20)
                    playerIn.sendStatusMessage(new TranslationTextComponent("info."+Reference.MODID+".tip.container.open.feed_up",sneakBtn,useBtn),true);
                else if (failedToOpen > 12)
                    playerIn.sendStatusMessage(new TranslationTextComponent("info."+Reference.MODID+".tip.container.open.annoyed",sneakBtn,useBtn),true);
                else if (failedToOpen > 4)
                    playerIn.sendStatusMessage(new TranslationTextComponent("info."+Reference.MODID+".tip.container.open",sneakBtn,useBtn),true);

                failedToOpen++;
            }

            return new ActionResult<>(ActionResultType.FAIL, stack);
            //return super.onItemRightClick(worldIn, playerIn, handIn);
        }

        return new ActionResult<>(ActionResultType.FAIL, stack);
    }

    public void openContainer(PlayerEntity playerIn)
    {
        playerIn.openContainer(new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("container." + Reference.MODID + ".itemstack_storage." + getRegistryName().getPath());
            }

            @Nullable
            @Override
            public Container createMenu(int id, PlayerInventory inventory, PlayerEntity playerEntity) {
                PacketBuffer buffer = new PacketBuffer(Unpooled.buffer(4, 4).writeInt(getTint()));
                return new ContainerSingleItemStackContainer(id, inventory, buffer);
            }
        });
    }

    @Override
    public String getTranslationKey()
    {
        return super.getTranslationKey();
    }

    public float getIsolation()
    {
        return isolation;
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public NonNullList<ItemStack> getContainedItemStacks()
    {
        return items;
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
    }

    public void cycleOpen()
    {
        isOpen = !isOpen;
    }

    public void setCointainedItemStack(NonNullList<ItemStack> stacks)
    {
        items = stacks;
    }

    public int getTint()
    {
        return tint;
    }
}
