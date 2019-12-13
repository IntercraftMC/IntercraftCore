package net.intercraft.intercraftcore.init.capabilities.fluid_container;

import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.concurrent.Callable;

public class FluidContainerStorage implements Capability.IStorage<IFluidContainer>
{

    @CapabilityInject(IFluidContainer.class)
    public static Capability<IFluidContainer> FLUID_CONTAINER_CAPABILITY = null;

    @Override
    public INBT writeNBT(Capability<IFluidContainer> capability, IFluidContainer instance, Direction side)
    {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("volume",instance.getVolume());
        tag.putString("fluid",instance.getFluid().getName());
        return tag;
    }

    @Override
    public void readNBT(Capability<IFluidContainer> capability, IFluidContainer instance, Direction side, INBT nbt)
    {
        if (nbt instanceof CompoundNBT) {
            CompoundNBT tag = (CompoundNBT)nbt;
            instance.setVolume(tag.getShort("volume"));
            instance.setFluid(FluidType.valueOf(tag.getString("fluid").toUpperCase()));
        }
    }

    public static class Factory implements Callable<IFluidContainer>
    {

        @Override
        public IFluidContainer call() throws Exception
        {
            return new FluidContainer();
        }
    }
}