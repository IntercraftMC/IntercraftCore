package net.intercraft.intercraftcore.init.capabilities.stackContainer;

import net.intercraft.intercraftcore.init.capabilities.fluid_container.FluidContainer;
import net.intercraft.intercraftcore.init.capabilities.fluid_container.FluidContainerStorage;
import net.intercraft.intercraftcore.init.capabilities.fluid_container.IFluidContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class StackFluidContainerProvider implements ICapabilitySerializable
{
    private final FluidContainer fluid;
    private final ItemStackHandler inventory;

    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(this::getInventory);
    private final LazyOptional<IFluidContainer> fluidHandler = LazyOptional.of(this::getFluid);

    public StackFluidContainerProvider(short slots, short maxVolume)
    {
        inventory = new ItemStackHandler(slots);
        fluid     = new FluidContainer(maxVolume);
    }

    protected FluidContainer getFluid()
    {
        return fluid;
    }
    protected ItemStackHandler getInventory()
    {
        return inventory;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public INBT serializeNBT()
    {
        CompoundNBT compoundNBT = new CompoundNBT();

        compoundNBT.put("items", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,getInventory(),null));
        compoundNBT.put("fluid", FluidContainerStorage.FLUID_CONTAINER_CAPABILITY.getStorage().writeNBT(FluidContainerStorage.FLUID_CONTAINER_CAPABILITY,getFluid(),null));

        return compoundNBT;
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        if (nbt instanceof CompoundNBT) {
            CompoundNBT compoundNBT = (CompoundNBT)nbt;
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getInventory(), null, compoundNBT.get("items"));
            FluidContainerStorage.FLUID_CONTAINER_CAPABILITY.getStorage().readNBT(FluidContainerStorage.FLUID_CONTAINER_CAPABILITY, getFluid(), null, compoundNBT.get("fluid"));
        }
    }

    @SuppressWarnings("unchecked")
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return itemHandler.cast();
        else if (cap == FluidContainerStorage.FLUID_CONTAINER_CAPABILITY)
            return fluidHandler.cast();
        return (LazyOptional<T>) LazyOptional.empty();
    }
}