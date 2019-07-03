package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.IntercraftEventHandler;
import net.intercraft.intercraftcore.init.RegistrationHandler;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationStorage;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MODID)
public class IntercraftCore
{
    public static IntercraftCore instance;

    public IntercraftCore()
    {

        // Save a self reference
        instance = this;

        // Setup initial event listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistrationHandler::register);

        //MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::onPLayerJoin);
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::ColorHandlerEvent);
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::ColorBlockHandlerEvent);
    }


    public void onCommonSetup(final FMLCommonSetupEvent event)
    {
        //PROXY.setup();
        CapabilityManager.INSTANCE.register(IRadiation.class, new RadiationStorage(), new RadiationStorage.Factory());
    }

    public void onClientSetup(final FMLClientSetupEvent event)
    {

    }
}
