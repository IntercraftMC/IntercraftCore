package net.intercraft.intercraftcore;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MODID)
public class IntercraftCore {
    //public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
    //public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public IntercraftCore() {
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event) {
        //PROXY.setup();

    }

    public void onClientSetup(final FMLClientSetupEvent event) {

    }
}
