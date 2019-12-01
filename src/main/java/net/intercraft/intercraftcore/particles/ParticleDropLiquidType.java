package net.intercraft.intercraftcore.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ParticleDropLiquidType extends ParticleType<ParticleDropLiquidData>
{

    public ParticleDropLiquidType()
    {
        super(false, ParticleDropLiquidData.DESERIALIZER);
        setRegistryName("liquid_drop");
    }

    public static class Factory implements IParticleFactory<ParticleDropLiquidData>
    {
        @Nullable
        @Override
        public Particle makeParticle(@Nonnull ParticleDropLiquidData data,@Nonnull  World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            return new FXDropLiquid(worldIn,x,y,z,data.maxAge,data.r,data.g,data.b);
        }
    }
}
