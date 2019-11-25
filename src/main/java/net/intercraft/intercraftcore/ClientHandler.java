package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.IntercraftParticles;
import net.intercraft.intercraftcore.particles.ParticleDropLiquidType;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
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

    public static final List<ParticleType<?>> particles = new LinkedList<>();

    /**
     * Textures not loaded as blocks or items.
     */

    private static final ResourceLocation[] textures = new ResourceLocation[] {
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