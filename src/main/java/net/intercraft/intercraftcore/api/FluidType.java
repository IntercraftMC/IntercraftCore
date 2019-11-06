package net.intercraft.intercraftcore.api;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;

public enum FluidType
{
    NONE( "none", 0,  0.0f, null,                                         null),
    WATER("water",80 ,0.6f, Reference.MODID+":block/liquids/water_still", ParticleTypes.FALLING_WATER),
    RESIN("resin",120,1.0f, Reference.MODID+":block/liquids/resin_still", ParticleTypes.FALLING_WATER);




    private final String name, texture;
    private final double viscosity;
    private final float alpha;
    private final BasicParticleType basicParticleType;

    FluidType(String name, double viscosity, float alpha, String texture, BasicParticleType particleType)
    {
        this.name = name;
        this.viscosity = viscosity;
        this.alpha = alpha;
        this.texture = texture;
        this.basicParticleType = particleType;
    }

    public String toString()
    {
        return String.format("{%s:%s}",getName(),viscosity);
    }

    public String getName()
    {
        return this.name;
    }

    public double getViscosity()
    {
        return this.viscosity;
    }

    public float getAlpha()
    {
        return this.alpha;
    }

    public String getTexture()
    {
        return texture;
    }

    public BasicParticleType getParticle()
    {
        return this.basicParticleType;
    }

}
