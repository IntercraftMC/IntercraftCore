package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler
{

    /**
     * Textures not loaded as blocks or items.
     */

    private static final ResourceLocation[] textures = new ResourceLocation[] {
        new ResourceLocation(Reference.MODID,"block/liquids/water_still"),
        new ResourceLocation(Reference.MODID,"block/liquids/resin_still")
    };

    @SubscribeEvent
    public static void attachTER(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TreeTapTileEntity.class, new TreeTapTileEntityRenderer<>());
    }

    @SubscribeEvent
    public static void onTextureStitch(final TextureStitchEvent.Pre event)
    {
        for (ResourceLocation texture : textures) { event.addSprite(texture); }
    }

}