package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.CreativeTabBase;
import net.intercraft.intercraftcore.init.ItemRegistry;
import net.intercraft.intercraftcore.init.PotionRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MODID)
public class IntercraftCore {

    public static IntercraftCore instance;

    public static ItemGroup RESOURCES;

    public IntercraftCore() {

        // Save a self reference
        instance = this;

        // Creative Tabs are created here
        RESOURCES = new CreativeTabBase("resources");

        // Setup initial event listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ItemRegistry::registerItems);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(PotionRegistry::registerPotions);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event) {
        //PROXY.setup();
    }

    public void onClientSetup(final FMLClientSetupEvent event) {

    }
}
