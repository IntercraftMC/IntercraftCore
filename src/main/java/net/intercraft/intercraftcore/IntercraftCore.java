package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.IntercraftEventHandler;
import net.intercraft.intercraftcore.init.RegistrationHandler;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationStorage;
import net.minecraft.util.ResourceLocation;
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
    //public static CapabilityRadiation radiation;
    public static final ResourceLocation RAD_ID = new ResourceLocation(Reference.MODID,"radiation");

    public static int defDensity = 3;

    public IntercraftCore()
    {

        // Save a self reference
        instance = this;

        // Setup initial event listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistrationHandler::register);


    }


    public void onCommonSetup(final FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(IRadiation.class, new RadiationStorage(), new RadiationStorage.Factory());
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapability);

        //PROXY.setup();
        //CapabilityManager.INSTANCE.register(IRadiation.class, new RadiationStorage(), new RadiationStorage.Factory());

        //radiation = new CapabilityRadiation();
        //radiation.register();

    }

    public void onClientSetup(final FMLClientSetupEvent event)
    {

    }
}
