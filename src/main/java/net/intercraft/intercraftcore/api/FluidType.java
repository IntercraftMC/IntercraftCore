package net.intercraft.intercraftcore.api;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;

public enum FluidType
{
    NONE( "none", 0,0,   null),
    WATER("water",2,0.4, ParticleTypes.FALLING_WATER),
    RESIN("resin",3,1,   ParticleTypes.FALLING_WATER);

    private final String name;
    private final double viscosity;
    private final double alpha;
    private final BasicParticleType basicParticleType;

    FluidType(String name, double viscosity, double alpha, BasicParticleType particleType)
    {
        this.name = name;
        this.viscosity = viscosity;
        this.alpha = alpha;
        this.basicParticleType = particleType;
    }

    public String toString()
    {
        return this.name+":"+this.viscosity;
    }

    public String getName()
    {
        return this.name;
    }

    public double getViscosity()
    {
        return this.viscosity;
    }
    public double getAlpha()
    {
        return this.alpha;
    }

    public BasicParticleType getParticle()
    {
        return this.basicParticleType;
    }
}
