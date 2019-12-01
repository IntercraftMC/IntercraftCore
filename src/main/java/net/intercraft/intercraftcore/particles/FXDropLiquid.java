package net.intercraft.intercraftcore.particles;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.world.World;

public class FXDropLiquid extends Particle
{

    private final short red, green, blue;
    private final float maxAge;

    public FXDropLiquid(World world, double x, double y, double z, float maxAge, short red, short green, short blue)
    {
        super(world,x,y,z,0,0,0);
        this.red    = red;
        this.green  = green;
        this.blue   = blue;
        this.maxAge = maxAge;

        particleGravity = 0;
        motionX = motionY = motionZ = 0;

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

    }

    @Override
    public void renderParticle(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float part = 16 + age;

        float var8 = part % 8 / 8.0F;
        float var9 = var8 + 0.0624375F*2;
        float var10 = part / 8 / 8.0F;
        float var11 = var10 + 0.0624375F*2;
        float var12 = 0.1f * 1;

        float var13 = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float var14 = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float var15 = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);


        buffer.pos(var13 - rotationX * var12 - rotationXY * var12, var14 - rotationZ * var12, var15 - rotationYZ * var12 - rotationXZ * var12).tex(var9, var11).color(red, green, blue, 1).endVertex();
        buffer.pos(var13 - rotationX * var12 + rotationXY * var12, var14 + rotationZ * var12, var15 - rotationYZ * var12 + rotationXZ * var12).tex(var9, var10).color(red, green, blue, 1).endVertex();
        buffer.pos(var13 + rotationX * var12 + rotationXY * var12, var14 + rotationZ * var12, var15 + rotationYZ * var12 + rotationXZ * var12).tex(var8, var10).color(red, green, blue, 1).endVertex();
        buffer.pos(var13 + rotationX * var12 - rotationXY * var12, var14 - rotationZ * var12, var15 + rotationYZ * var12 - rotationXZ * var12).tex(var8, var11).color(red, green, blue, 1).endVertex();
    }

    @Override
    public void tick()
    {
        //super.tick();

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (age++ >= maxAge)
            setExpired();

        motionY -= 0.04D * particleGravity;

        this.move(motionX,motionY,motionZ);




        if (age > 1)
            setExpired();


    }

    @Override
    public IParticleRenderType getRenderType()
    {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
