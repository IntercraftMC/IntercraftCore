package net.intercraft.intercraftcore;

import com.mojang.blaze3d.platform.GlStateManager;
import net.intercraft.intercraftcore.client.CableCaseTileEntityRenderer;
import net.intercraft.intercraftcore.init.IntercraftItems;
import net.intercraft.intercraftcore.init.IntercraftParticles;
import net.intercraft.intercraftcore.particles.ParticleDropLiquidType;
import net.intercraft.intercraftcore.tileentity.CableCaseTileEntity;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.intercraft.intercraftcore.client.TreeTapTileEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler
{

    private static final ResourceLocation HAZMAT_MASK_BLUR = new ResourceLocation(Reference.MODID,"textures/misc/hazmat_mask_blur.png");

    public static final List<ParticleType<?>> particles = new LinkedList<>();

    /**
     * Textures not loaded as blocks or items.
     */

    private static final ResourceLocation[] textures = new ResourceLocation[] {
        new ResourceLocation(Reference.MODID,"block/liquids/resin_still")
    };


    @SubscribeEvent
    public static void renderGameOverlay(final RenderGameOverlayEvent event)
    {
        ItemStack stack = Minecraft.getInstance().player.inventory.armorItemInSlot(3);
        if (Minecraft.getInstance().gameSettings.thirdPersonView == 0)
            renderHazMatBlur(event.getWindow().getWidth(),event.getWindow().getHeight());
    }

    private static void renderHazMatBlur(int w, int h)
    {

        GlStateManager.disableDepthTest();
        GlStateManager.depthMask(false);
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlphaTest();
        Minecraft.getInstance().getTextureManager().bindTexture(HAZMAT_MASK_BLUR);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0.0D, (double)h, -90.0D).endVertex();
        bufferbuilder.pos((double)w, (double)h, -90.0D).endVertex();
        bufferbuilder.pos((double)w, 0.0D, -90.0D).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, -90.0D).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepthTest();
        GlStateManager.enableAlphaTest();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

    }





    @SubscribeEvent
    public static void attachTER(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TreeTapTileEntity.class, new TreeTapTileEntityRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(CableCaseTileEntity.class, new CableCaseTileEntityRenderer<>());
        //ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.MODID,"large_glass_jar"));
    }

    @SubscribeEvent
    public static void onTextureStitch(final TextureStitchEvent.Pre event)
    {
        if (event.getMap().getBasePath().equals("textures")) {
            for (ResourceLocation texture : textures)
                event.addSprite(texture);
        }

    }

    //@SubscribeEvent
    public static void onParticleRegisterFactory(final ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particles.registerFactory(IntercraftParticles.LIQUID_DRIP, new ParticleDropLiquidType.Factory());
    }

    //@SubscribeEvent
    public static void onParticleTypeRegistry(final RegistryEvent.Register<ParticleType<?>> event)
    {
        IntercraftParticles.register();
        particles.forEach(particle -> event.getRegistry().register(particle));
        System.out.println("Particle registration done.");
    }

    /*protected static void registerParticles(final RegistryEvent.Register<ParticleType<?>> event)
    {
        IntercraftParticles.register();
        particles.forEach(particle -> event.getRegistry().register(particle));
        System.out.println("Particle registration done.");
    }*/

}