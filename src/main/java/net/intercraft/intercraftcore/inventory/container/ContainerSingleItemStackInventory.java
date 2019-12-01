package net.intercraft.intercraftcore.inventory.container;

import net.intercraft.intercraftcore.item.ItemSingleItemStackContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;

public class ContainerSingleItemStackInventory extends Inventory
{

    private ItemStack stack = ItemStack.EMPTY;
    private ItemStack itemStorage;


    public ContainerSingleItemStackInventory(ItemStack itemStorage)
    {
        super(1);
        this.itemStorage = itemStorage;

        loadFromItem();

        /*if (itemStorage.hasTag()) {
            CompoundNBT nbt = itemStorage.serializeNBT();
            NonNullList<ItemStack> stacks = NonNullList.withSize(1,ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(nbt,stacks);
            this.stack = stacks.get(0);
        }*/
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return !(stack.getItem() instanceof ItemSingleItemStackContainer);
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return stack == ItemStack.EMPTY;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return stack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        stack.shrink(count);
        saveToItem();

        super.markDirty();
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack s = stack;
        stack = ItemStack.EMPTY;
        saveToItem();
        super.markDirty();
        return s;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.stack = stack;
        saveToItem();

        super.markDirty();
    }

    /*@Override
    public void markDirty()
    {
        CompoundNBT nbt = new CompoundNBT();
        NonNullList<ItemStack> stacks = NonNullList.withSize(1,this.stack);
        ItemStackHelper.saveAllItems(nbt,stacks);
        this.itemStorage.setTag(nbt);
    }*/

    /*public void read(ListNBT p_70486_1_)
    {
        for(int i = 0; i < this.getSizeInventory(); ++i) {
            this.setInventorySlotContents(i, ItemStack.EMPTY);
        }

        for(int k = 0; k < p_70486_1_.size(); ++k) {
            CompoundNBT compoundnbt = p_70486_1_.getCompound(k);
            int j = compoundnbt.getByte("Slot") & 255;
            if (j >= 0 && j < this.getSizeInventory()) {
                this.setInventorySlotContents(j, ItemStack.read(compoundnbt));
            }
        }

    }

    public ListNBT write()
    {
        ListNBT listnbt = new ListNBT();

        for(int i = 0; i < this.getSizeInventory(); ++i) {
            ItemStack itemstack = this.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                CompoundNBT compoundnbt = new CompoundNBT();
                compoundnbt.putByte("Slot", (byte)i);
                itemstack.write(compoundnbt);
                listnbt.add(compoundnbt);
            }
        }

        return listnbt;
    }*/

    public void saveToItem()
    {
        /*CompoundNBT nbt = new CompoundNBT();
        NonNullList<ItemStack> stacks = NonNullList.withSize(1,this.stack);
        ItemStackHelper.saveAllItems(nbt,stacks);
        this.itemStorage.setTag(nbt);*/

        CompoundNBT nbt = new CompoundNBT();
        nbt.putByte("Slot",(byte)0);
        this.stack.write(nbt);
        this.itemStorage.setTag(nbt);
    }

    public void loadFromItem()
    {
        CompoundNBT nbt = this.itemStorage.getTag();
        int j = nbt.getByte("Slot") & 255;
        this.setInventorySlotContents(j,ItemStack.read(nbt));
        //return ItemStack.read(nbt);
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
}
