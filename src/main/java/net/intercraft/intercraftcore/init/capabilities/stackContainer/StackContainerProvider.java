package net.intercraft.intercraftcore.init.capabilities.stackContainer;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class StackContainerProvider implements ICapabilitySerializable
{
    private final ItemStackHandler inventory;

    /**
     * StackContainerProvider Constructor for creating and saving a inventory to a stack.
     *
     * @param slots the size of the inventory (duh).
     */

    public StackContainerProvider(short slots)
    {
        inventory = new ItemStackHandler(slots);
    }

    protected ItemStackHandler getInventory()
    {
        return inventory;
    }

    @Override
    public INBT serializeNBT()
    {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,getInventory(),null);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getInventory(), null, nbt);
    }

    @SuppressWarnings("unchecked")
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (LazyOptional<T>) LazyOptional.of(() -> getInventory());
        return (LazyOptional<T>) LazyOptional.empty();
    }
}