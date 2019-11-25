package net.intercraft.intercraftcore.api;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftParticles;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;

public enum     FluidType
{
    WATER(       "water",        (short)80,  0x4a73e7, "minecraft:block/water_still",                ParticleTypes.FALLING_WATER),
    RESIN(       "resin",        (short)200, 0xf9de96, Reference.MODID+":block/liquids/resin_still", ParticleTypes.FALLING_WATER),
    RUBBER_RESIN("rubber_resin", (short)120, 0xdbeaec, Reference.MODID+":block/liquids/resin_still", ParticleTypes.FALLING_WATER),

    NONE( "none", (short)0,null,null);


    private final String name, texture;
    private final short viscosity;
    private final int tint;
    private final ParticleType basicParticleType;

    /**
     * FluidType Constructor
     *
     * @param name name of the liquid.
     * @param viscosity drop rate.
     * @param tint colour override of the texture, -1 for no tint.
     * @param texture texture name in texture map.
     * @param particleType drop particle.
     */

    FluidType(String name, short viscosity, int tint, String texture, ParticleType particleType)
    {
        this.name              = name;
        this.viscosity         = viscosity;
        this.tint              = tint;
        this.texture           = texture;
        this.basicParticleType = particleType;
    }

    FluidType(String name, short viscosity, String texture, BasicParticleType particleType)
    {
        this(name,viscosity,-1,texture,particleType);
    }

    public String toString()
    {
        return String.format("{%s,%s,%s}",name,tint,viscosity);
    }

    public String getName()
    {
        return name;
    }

    public int getViscosity()
    {
        return viscosity;
    }

    public int getTint()
    {
        return tint;
    }

    public String getTexture()
    {
        return texture;
    }

    public ParticleType getParticle()
    {
        return basicParticleType;
    }

}
