package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.IntercraftCoreRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MODID)
public class IntercraftCore {

    public static IntercraftCore instance;

    //public static ItemGroup RESOURCES;

    public IntercraftCore() {

        // Save a self reference
        instance = this;

        //RESOURCES = new ItemGroupResources("resources");

        // Setup initial event listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(IntercraftCoreRegistry::register);
    }



    public void onCommonSetup(final FMLCommonSetupEvent event) {
        //PROXY.setup();
    }

    public void onClientSetup(final FMLClientSetupEvent event) {

    }
}
