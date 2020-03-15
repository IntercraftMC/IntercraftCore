package net.intercraft.intercraftcore.client;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.Util;
import net.intercraft.intercraftcore.api.UtilBlocks;
import net.intercraft.intercraftcore.item.ItemElement;
import net.intercraft.intercraftcore.tileentity.CableCaseTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;

public class CableCaseTileEntityRenderer<T extends CableCaseTileEntity> extends TileEntityRendererFast<T>
{

    private static final float
            plateSize           = 0.625f,  // The max side size of the plate.
            plateConnectionSize = 0.200f,  // The max connection side size of the plate.
            plateWidth          = 0.0625f, // The width of the plate.
    min = 0.1875f, minZ = 0.125f, maxZ = 0.875f, max = min + plateSize; // Render Plate bounds.

    private static float ALPHA = 1.0f;


    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer)
    {
        buffer.setTranslation(x,y,z);

        for (byte i=0;i<te.getPlates().length;i++) {
            if (te.getPlate(i) != null) {
                renderPlate(te, i, te.getPlate(i) instanceof ItemElement ? Reference.MODID+":block/block_solid" : te.getPlate(i).getRegistryName().toString(), buffer);
            }
        }


    }


    private void renderWires(T te, BufferBuilder buffer)
    {

    }

    private void renderModule(T te, BufferBuilder buffer)
    {

    }


    private void renderPlate(T te, byte plate, String texture, BufferBuilder buffer)
    {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(texture);
        float u1 = sprite.getMinU(), v1 = sprite.getMinV(), u2 = sprite.getMaxU(), v2 = sprite.getMaxV();

        final float[] c = te.getPlate(plate) instanceof ItemElement ? UtilBlocks.toFractal(Util.hex2rgb(((ItemElement) te.getPlate(plate)).getTint()),255) : new float[] {1,1,1};
        final UtilBlocks.Connections[] n = UtilBlocks.Connections.getConnectionFromValue(plate).getNeighbors();

        int combined, lma, lmb;
        switch (plate) {
            case 3: // South
                combined = getWorld().getCombinedLight(te.getPos().south(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                buffer.pos(min,min,maxZ).color(c[0],c[1],c[2],ALPHA).tex(u2,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(min,max,maxZ).color(c[0],c[1],c[2],ALPHA).tex(u2,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(max,max,maxZ).color(c[0],c[1],c[2],ALPHA).tex(u1,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(max,min,maxZ).color(c[0],c[1],c[2],ALPHA).tex(u1,v1).lightmap(lma,lmb).endVertex();

                break;
            case 0: // West
                combined = getWorld().getCombinedLight(te.getPos().west(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                buffer.pos(minZ,min,min).color(c[0],c[1],c[2],ALPHA).tex(u2,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(minZ,max,min).color(c[0],c[1],c[2],ALPHA).tex(u2,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(minZ,max,max).color(c[0],c[1],c[2],ALPHA).tex(u1,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(minZ,min,max).color(c[0],c[1],c[2],ALPHA).tex(u1,v1).lightmap(lma,lmb).endVertex();

                break;
            case 2: // East
                combined = getWorld().getCombinedLight(te.getPos().east(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                buffer.pos(maxZ,min,max).color(c[0],c[1],c[2],ALPHA).tex(u2,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(maxZ,max,max).color(c[0],c[1],c[2],ALPHA).tex(u2,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(maxZ,max,min).color(c[0],c[1],c[2],ALPHA).tex(u1,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(maxZ,min,min).color(c[0],c[1],c[2],ALPHA).tex(u1,v1).lightmap(lma,lmb).endVertex();

                break;
            case 4: // Up
                combined = getWorld().getCombinedLight(te.getPos().up(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                buffer.pos(min,maxZ,max).color(c[0],c[1],c[2],ALPHA).tex(u1,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(max,maxZ,max).color(c[0],c[1],c[2],ALPHA).tex(u2,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(max,maxZ,min).color(c[0],c[1],c[2],ALPHA).tex(u2,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(min,maxZ,min).color(c[0],c[1],c[2],ALPHA).tex(u1,v1).lightmap(lma,lmb).endVertex();

                break;
            case 5: // Down
                combined = getWorld().getCombinedLight(te.getPos().down(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                buffer.pos(max,minZ,max).color(c[0],c[1],c[2],ALPHA).tex(u1,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(min,minZ,max).color(c[0],c[1],c[2],ALPHA).tex(u1,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(min,minZ,min).color(c[0],c[1],c[2],ALPHA).tex(u2,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(max,minZ,min).color(c[0],c[1],c[2],ALPHA).tex(u2,v2).lightmap(lma,lmb).endVertex();

                break;
            default: // North
                combined = getWorld().getCombinedLight(te.getPos().north(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                buffer.pos(max,min,minZ).color(c[0],c[1],c[2],ALPHA).tex(u2,v1).lightmap(lma,lmb).endVertex();
                buffer.pos(max,max,minZ).color(c[0],c[1],c[2],ALPHA).tex(u2,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(min,max,minZ).color(c[0],c[1],c[2],ALPHA).tex(u1,v2).lightmap(lma,lmb).endVertex();
                buffer.pos(min,min,minZ).color(c[0],c[1],c[2],ALPHA).tex(u1,v1).lightmap(lma,lmb).endVertex();

        }
    }
}
