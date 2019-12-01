package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.ClientHandler;
import net.intercraft.intercraftcore.particles.ParticleDropLiquidData;
import net.intercraft.intercraftcore.particles.ParticleDropLiquidType;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;

import java.util.Arrays;

public class IntercraftParticles
{
    public static final ParticleType<ParticleDropLiquidData> LIQUID_DRIP;


    static {

        LIQUID_DRIP = new ParticleDropLiquidType();

    }


    public static void register()
    {
        registerParticles(LIQUID_DRIP);

    }

    protected static void registerParticles(ParticleType<?>...particleTypes)
    {
        ClientHandler.particles.addAll(Arrays.asList(particleTypes));
    }

    /*private static BasicParticleType buildP(String key, boolean alwaysShow)
    {

        BasicParticleType type = new BasicParticleType(alwaysShow);
        type.setRegistryName(key);

        return type;

        //return (BasicParticleType) Registry.<ParticleType<? extends IParticleData>>register(Registry.PARTICLE_TYPE, key, new BasicParticleType(alwaysShow));
    }*/

}
