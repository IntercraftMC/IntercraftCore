package net.intercraft.intercraftcore.init.capabilities.fluid_container;

import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.world.chunk.Chunk;

public interface IFluidContainer
{
    FluidType getFluid();

    short getVolume();

    short getMaxVolume();

    float getPercent();

    void setFluid(FluidType fluid);

    void setVolume(short volume);

    /*void setFluid(FluidType fluid, Chunk chunk);
    void setVolume(int volume, Chunk chunk);*/
}
