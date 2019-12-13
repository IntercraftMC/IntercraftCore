package net.intercraft.intercraftcore.init.capabilities.fluid_container;
/*
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;


public class FluidContainerProvider implements ICapabilitySerializable
{
    private final FluidContainer container;


    public FluidContainerProvider(short volume)
    {
        container = new FluidContainer(volume);
    }

    @Override
    public INBT serializeNBT()
    {
        return FluidContainerStorage.FLUID_CONTAINER_CAPABILITY.getStorage().writeNBT(FluidContainerStorage.FLUID_CONTAINER_CAPABILITY,container,null);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        FluidContainerStorage.FLUID_CONTAINER_CAPABILITY.getStorage().readNBT(FluidContainerStorage.FLUID_CONTAINER_CAPABILITY, container, null, nbt);
    }

    @SuppressWarnings("unchecked")
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == FluidContainerStorage.FLUID_CONTAINER_CAPABILITY)
            return (LazyOptional<T>) LazyOptional.of(() -> container);
        return (LazyOptional<T>) LazyOptional.empty();
    }
}
*/