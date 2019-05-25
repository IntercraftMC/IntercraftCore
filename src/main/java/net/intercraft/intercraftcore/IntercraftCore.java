package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.RegistrationHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MODID)
public class IntercraftCore {

    public static IntercraftCore instance;

    public IntercraftCore() {

        // Save a self reference
        instance = this;

        // Setup initial event listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistrationHandler::register);
    }



    public void onCommonSetup(final FMLCommonSetupEvent event) {
        //PROXY.setup();
    }

    public void onClientSetup(final FMLClientSetupEvent event) {

    }
}
