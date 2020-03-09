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
            plateSize           = 0.8f, // The max side size of the plate.
            plateConnectionSize = 0.2f, // The max connection side size of the plate.
            plateWidth          = 0.2f; // The width of the plate.


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


    private void renderPlate(T te, byte plate, String texture, BufferBuilder buffer)
    {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(texture);
        float u1 = sprite.getMinU(), v1 = sprite.getMinV(), u2 = sprite.getMaxU(), v2 = sprite.getMaxV();

        float[] c = te.getPlate(plate) instanceof ItemElement ? UtilBlocks.toFractal(Util.hex2rgb(((ItemElement) te.getPlate(plate)).getTint()),255) : new float[] {1,1,1};

        int combined, lma, lmb;
        switch (plate) {
            case 3: // South
                combined = getWorld().getCombinedLight(te.getPos().south(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                break;
            case 0: // West
                combined = getWorld().getCombinedLight(te.getPos().west(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                break;
            case 2: // East
                combined = getWorld().getCombinedLight(te.getPos().east(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                break;
            case 4: // Up
                combined = getWorld().getCombinedLight(te.getPos().up(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                break;
            case 5: // Down
                combined = getWorld().getCombinedLight(te.getPos().down(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

                break;
            default: // North
                combined = getWorld().getCombinedLight(te.getPos().north(),0);
                lma = combined >> 16 & 65535;
                lmb = combined  & 65535;

        }



        //int upCombined = getWorld().getCombinedLight(te.getPos().up(), 0);

        //renderPlate(buffer,te,Direction.NORTH,u1,v1,u2,v2,1,1,1);


        /*float xMin = 0.3f, xMax = 0.5f, zMin = 0.3f, zMax = 0.5f;

        buffer.pos(xMin, 0.4f, zMax).tex(u1,v2).color(1,1,1,1).lightmap(upLMa,upLMb).endVertex();
        buffer.pos(xMax, 0.4f, zMax).tex(u2,v2).color(1,1,1,1).lightmap(upLMa,upLMb).endVertex();
        buffer.pos(xMax, 0.4f, zMin).tex(u2,v1).color(1,1,1,1).lightmap(upLMa,upLMb).endVertex();
        buffer.pos(xMin, 0.4f, zMin).tex(u1,v1).color(1,1,1,1).lightmap(upLMa,upLMb).endVertex();*/




    }
}
