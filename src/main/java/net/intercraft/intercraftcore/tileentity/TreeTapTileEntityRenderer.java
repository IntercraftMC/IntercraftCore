package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.BucketType;
import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeTileEntity;
import net.minecraftforge.fluids.IFluidTank;

import java.util.BitSet;
import java.util.List;

public class TreeTapTileEntityRenderer<T extends TreeTapTileEntity> extends TileEntityRendererFast<T>
{

    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer)
    {
        // *Confusion sound*


        BlockPos pos = te.getPos();

        net.minecraft.world.IEnviromentBlockReader world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
        BlockState state = world.getBlockState(pos);


        if (state.has(BlockProperties.BUCKET))
            if (state.get(BlockProperties.BUCKET) != BucketType.NONE && te.getVolume() > 0) {
                //buffer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());

//                buffer.pos(0,0,0).color(1,1,1,1).endVertex();
//                buffer.pos(4,0,0).color(1,1,1,1).endVertex();
//                buffer.pos(0,0,4).color(1,1,1,1).endVertex();
//                buffer.pos(4,0,4).color(1,1,1,1).endVertex();
//
//                buffer.pos(0,4,0).color(1,1,1,1).endVertex();
//                buffer.pos(4,4,0).color(1,1,1,1).endVertex();
//                buffer.pos(0,4,4).color(1,1,1,1).endVertex();
//                buffer.pos(4,4,4).color(1,1,1,1).endVertex();



                //render(te,x,y,z,buffer,state, te.getFluidType());


            }


    }

    private void render(T te, double x, double y, double z, BufferBuilder buffer, BlockState state, FluidType type)
    {
        buffer.setTranslation(x,y,z);



        //minX, minY, minZ, maxX, maxY, maxZ
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite("minecraft:water_still");
        float u1 = sprite.getMinU(), v1 = sprite.getMinV(), u2 = sprite.getMaxU(), v2 = sprite.getMaxV();



        buffer.pos(0.06, 0.5, 0.06).color(1,1,1,1).tex(u1, v1).endVertex();
        buffer.pos(0.06, 0.5, 0.93).color(1,1,1,1).tex(u2, v1).endVertex();
        buffer.pos(0.93, 0.5, 0.93).color(1,1,1,1).tex(u2, v2).endVertex();
        buffer.pos(0.93, 0.5, 0.06).color(1,1,1,1).tex(u1, v2).endVertex();


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
