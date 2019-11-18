package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.BucketType;
import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class TreeTapTileEntityRenderer<T extends TreeTapTileEntity> extends TileEntityRendererFast<T>
{

    private static final float
            liquidLength          = 0.27f,  // The length/size of the square.
            liquidOffset          = 0.321f, // How far it should offset to fit in the bucket.
            liquidMaxHeightOffset = 0.02f;  // How high up the liquid can reach in the bucket. 0 for flush.

    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer)
    {
        BlockPos pos = te.getPos();

        net.minecraft.world.IEnviromentBlockReader world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
        BlockState state = world.getBlockState(pos);


        if (state.has(BlockProperties.BUCKET))
            if (state.get(BlockProperties.BUCKET) != BucketType.NONE && te.getVolume() > 0 && te.fluidType != FluidType.NONE) {
                buffer.setTranslation(x,y,z);
                render(te,buffer,state);

            }


    }

    private void render(T te, BufferBuilder buffer, BlockState state)
    {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(te.fluidType.getTexture());
        float u1 = sprite.getMinU(), v1 = sprite.getMinV(), u2 = sprite.getMaxU(), v2 = sprite.getMaxV();

        int upCombined = getWorld().getCombinedLight(te.getPos().up(), 0);
        int upLMa = upCombined >> 16 & 65535;
        int upLMb = upCombined & 65535;

        float xMin = 0.5f - liquidLength / 2, xMax = 0.5f + liquidLength / 2, yMin = 0.125f + liquidMaxHeightOffset, yMax = 0.5f, zMin = 0.5f- liquidLength / 2, zMax = 0.5f + liquidLength / 2;

        float yLev = (yMin+(yMax-yMin)*(((float)te.volume)/(float)TreeTapTileEntity.maxVolume)) - liquidMaxHeightOffset;

        switch (state.get(HORIZONTAL_FACING)) {
            case WEST:
                xMax += liquidOffset;
                xMin += liquidOffset;
                break;

            case EAST:
                xMax -= liquidOffset;
                xMin -= liquidOffset;
                break;

            case SOUTH:
                zMax -= liquidOffset;
                zMin -= liquidOffset;
                break;

            default: case NORTH:
                zMax += liquidOffset;
                zMin += liquidOffset;
        }


        float[] c = hex2rgb(te.fluidType.getTint());

        buffer.pos(xMin, yLev, zMax).color(c[0],c[1],c[2],1f).tex(u1, v2).lightmap(upLMa, upLMb).endVertex();
        buffer.pos(xMax, yLev, zMax).color(c[0],c[1],c[2],1f).tex(u2, v2).lightmap(upLMa, upLMb).endVertex();
        buffer.pos(xMax, yLev, zMin).color(c[0],c[1],c[2],1f).tex(u2, v1).lightmap(upLMa, upLMb).endVertex();
        buffer.pos(xMin, yLev, zMin).color(c[0],c[1],c[2],1f).tex(u1, v1).lightmap(upLMa, upLMb).endVertex();
    }


    private static float[] hex2rgb(int hex)
    {
        if (hex != -1) {
            int r = (hex & 0xFF0000) >> 16, g = (hex & 0xFF00) >> 8, b = (hex & 0xFF);
            return new float[]{r/255f,g/255f,b/255f};
        } else return new float[]{1,1,1};
    }
}
