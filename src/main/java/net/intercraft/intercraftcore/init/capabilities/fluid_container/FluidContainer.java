package net.intercraft.intercraftcore.init.capabilities.fluid_container;

import net.intercraft.intercraftcore.api.FluidType;

public class FluidContainer implements IFluidContainer
{
    private short volume, maxVolume;
    private FluidType fluid = FluidType.NONE;

    public FluidContainer(short maxVolume)
    {
        this.maxVolume = maxVolume;
    }

    public FluidContainer()
    {
        maxVolume = 1000;
    }


    @Override
    public FluidType getFluid()
    {
        return fluid;
    }

    @Override
    public short getVolume()
    {
        return volume;
    }

    @Override
    public short getMaxVolume()
    {
        return maxVolume;
    }

    @Override
    public float getPercent() // Because I'm lazy.
    {
        return (float) volume/maxVolume;
    }

    @Override
    public void setFluid(FluidType fluid)
    {
        this.fluid = fluid;
    }

    @Override
    public void setVolume(short volume)
    {
        this.volume = volume;
    }
}
