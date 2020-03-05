package net.intercraft.intercraftcore.client;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.tileentity.CableCaseTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;

public class CableCaseTileEntityRenderer<T extends CableCaseTileEntity> extends TileEntityRendererFast<T>
{
    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer)
    {

        for (Item item : te.getPlates()) {
            if (item != null) {
                buffer.setTranslation(x,y,z);
                renderWalls(te, buffer);
            }
        }


    }


    private void renderWalls(T te, BufferBuilder buffer)
    {


        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(Reference.MODID+":block/block_solid");
        float u1 = sprite.getMinU(), v1 = sprite.getMinV(), u2 = sprite.getMaxU(), v2 = sprite.getMaxV();

        int upCombined = getWorld().getCombinedLight(te.getPos().up(), 0);
        int upLMa = upCombined >> 16 & 65535;
        int upLMb = upCombined & 65535;

        float xMin = 0, xMax = 4, zMin = 0, zMax = 4;

        buffer.pos(xMin, 5, zMax).tex(u1,v2).lightmap(upLMa,upLMb).endVertex();
        buffer.pos(xMax, 5, zMax).tex(u2,v2).lightmap(upLMa,upLMb).endVertex();
        buffer.pos(xMax, 5, zMin).tex(u2,v1).lightmap(upLMa,upLMb).endVertex();
        buffer.pos(xMin, 5, zMin).tex(u1,v1).lightmap(upLMa,upLMb).endVertex();

    }
}
