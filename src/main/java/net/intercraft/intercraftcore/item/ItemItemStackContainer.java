package net.intercraft.intercraftcore.item;

import io.netty.buffer.Unpooled;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.inventory.container.ContainerItemItemStack;
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

public class ItemItemStackContainer extends Item
{

    private static final ResourceLocation open = new ResourceLocation(Reference.MODID,"open");

    private final float isolation; // How much a radioactive material is being blocked while in here (0 = no blocking, 1 = full blocking).
    private final short inventorySize; // 1 >= size >= 4. Not a replacement of Shulker Boxes.
    private final int tint;

    private boolean isOpen = false;
    private NonNullList<ItemStack> items;


    public ItemItemStackContainer(String name, short inventorySize, float isolation, int tint)
    {
        super(new Item.Properties().group(ItemGroup.TOOLS));

        addPropertyOverride(open,(itemStack, worldIn, entityLivingBase) -> isOpen ? 1 : 0);

        this.isolation     = isolation;
        this.inventorySize = inventorySize;
        this.tint          = tint;
        this.items         = NonNullList.withSize(inventorySize, ItemStack.EMPTY);

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


        if (playerIn.isSneaking()) {

            playerIn.openContainer(new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName()
                {
                    return new TranslationTextComponent("container."+Reference.MODID+".itemstack_storage."+getRegistryName().getPath());
                }

                @Nullable
                @Override
                public Container createMenu(int id, PlayerInventory inventory, PlayerEntity playerEntity)
                {
                    return new ContainerItemItemStack(id,inventory,new PacketBuffer(Unpooled.buffer().writeInt(playerIn.getEntityId())));
                }
            });

            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }

        return new ActionResult<>(ActionResultType.FAIL, stack);
        //return super.onItemRightClick(worldIn, playerIn, handIn);
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

    public short getInventorySize()
    {
        return inventorySize;
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
