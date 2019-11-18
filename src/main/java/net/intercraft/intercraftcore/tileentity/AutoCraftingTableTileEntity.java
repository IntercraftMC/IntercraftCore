package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;

public class AutoCraftingTableTileEntity extends TileEntity implements ISidedInventory
{

    private static final int size = 9;

    private NonNullList<ItemStack> materialSlots = NonNullList.withSize(size, ItemStack.EMPTY);


    public AutoCraftingTableTileEntity()
    {
        super(IntercraftTileEntities.AUTOCRAFTINGTABLE);
    }


    @Override
    public int[] getSlotsForFace(Direction side)
    {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction)
    {
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return materialSlots.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return true;
    }

    @Override
    public void clear()
    {

    }


    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);

        materialSlots = NonNullList.withSize(materialSlots.size(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound,materialSlots);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound,materialSlots);
        return compound;
    }
}
