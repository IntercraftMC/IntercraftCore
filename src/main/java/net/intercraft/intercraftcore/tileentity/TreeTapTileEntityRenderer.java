package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.BucketType;
import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeTileEntity;
import net.minecraftforge.fluids.IFluidTank;
import org.lwjgl.opengl.GL11;

import java.util.BitSet;
import java.util.List;

public class TreeTapTileEntityRenderer<T extends TreeTapTileEntity> extends TileEntityRendererFast<T>
{

    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer)
    {
        // *Happiness sound*


        BlockPos pos = te.getPos();

        net.minecraft.world.IEnviromentBlockReader world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
        BlockState state = world.getBlockState(pos);


        if (state.has(BlockProperties.BUCKET))
            if (state.get(BlockProperties.BUCKET) != BucketType.NONE && te.getVolume() > 0) {
                render(te,x,y,z,buffer,state);


            }


    }

    private void render(T te, double x, double y, double z, BufferBuilder buffer, BlockState state)
    {
        buffer.setTranslation(x,y,z);



        //minX, minY, minZ, maxX, maxY, maxZ
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite("minecraft:block/water_still");
        float u1 = sprite.getMinU(), v1 = sprite.getMinV(), u2 = sprite.getMaxU(), v2 = sprite.getMaxV();

        int upCombined = getWorld().getCombinedLight(te.getPos().up(), 0);
        int upLMa = upCombined >> 16 & 65535;
        int upLMb = upCombined & 65535;

        float xMin = 0.06f,xMax = 0.93f,yMin = 0.125f,yMax = 0.5f,zMin = 0.06f,zMax = 0.93f;


        float yLev = yMin+(yMax-yMin)*(((float)te.volume)/(float)TreeTapTileEntity.maxVolume);

        buffer.pos(xMin, yLev, zMax).color(1,1,1,te.fluidType.getAlpha()).tex(u1, v2).lightmap(upLMa, upLMb).endVertex();
        buffer.pos(xMax, yLev, zMax).color(1,1,1,te.fluidType.getAlpha()).tex(u2, v2).lightmap(upLMa, upLMb).endVertex();
        buffer.pos(xMax, yLev, zMin).color(1,1,1,te.fluidType.getAlpha()).tex(u2, v1).lightmap(upLMa, upLMb).endVertex();
        buffer.pos(xMin, yLev, zMin).color(1,1,1,te.fluidType.getAlpha()).tex(u1, v1).lightmap(upLMa, upLMb).endVertex();


        /*switch (state.get(HORIZONTAL_FACING)) {
            case WEST: {

            }
            case EAST: {

            }
            case SOUTH: {

            }
            default: {

            }


        }*/




    }


    /*private AxisAlignedBB getRenderBounds(T te, AxisAlignedBB tankBounds)
    {
        float percent = (float) te.getVolume() / TreeTapTileEntity.maxVolume;

        double tankHeight = tankBounds.maxY - tankBounds.minY;
        double y1 = tankBounds.minY, y2 = (tankBounds.minY + (tankHeight * percent));

        return new AxisAlignedBB(tankBounds.minX,y1,tankBounds.minZ, tankBounds.maxX, y2, tankBounds.maxZ);
    }



    class TankRenderInfo {

        final IFluidTank tank;
        final AxisAlignedBB bounds;
        final BitSet faces = new BitSet(6);

        TankRenderInfo(IFluidTank tank, AxisAlignedBB bounds, Direction... renderFaces) {
            this.tank = tank;
            this.bounds = bounds;
            if (renderFaces.length == 0) {
                faces.set(0, 6, true);
            } else {
                for (Direction face : renderFaces) {
                    faces.set(face.getIndex(), true);
                }
            }
        }

        TankRenderInfo without(Direction face) {
            faces.clear(face.getIndex());
            return this;
        }

        boolean shouldRender(Direction face) {
            return faces.get(face.getIndex());
        }
    }*/

}
