package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.IntercraftEventHandler;
import net.intercraft.intercraftcore.init.RegistrationHandler;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IIdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenStorage;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.IOreVeins;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.OreVeinStorage;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationStorage;
import net.intercraft.intercraftcore.init.gen.OreGen;
import net.intercraft.intercraftcore.init.loot.functions.BlockItemFunction;
import net.intercraft.intercraftcore.init.loot.functions.HarderSetCount;
import net.intercraft.intercraftcore.networking.IntercraftPacketHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

@Mod(Reference.MODID)
public class IntercraftCore
{
    public static IntercraftCore instance;
    public static final ResourceLocation RAD_ID = new ResourceLocation(Reference.MODID,"radiation");
    public static final ResourceLocation HID_ID = new ResourceLocation(Reference.MODID,"identity_hidden");
    public static final ResourceLocation VEIN_ID = new ResourceLocation(Reference.MODID,"ore_vein");
    //public static final ResourceLocation PAT_ID = new ResourceLocation(Reference.MODID,"pattern");

    public static final SimpleChannel NETWORK = IntercraftPacketHandler.getNetworkChannel();

    public static final int defDensity = 3;

    public IntercraftCore()
    {

        // Save a self reference
        instance = this;

        // Setup initial event listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueue);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistrationHandler::register);




    }

    public void enqueue(final InterModEnqueueEvent event)
    {

        final String[] slots = new String[] {
                "pocket",
                "wrist",
                "mask"
        };



        for (String slot : slots) {
            InterModComms.sendTo(CuriosAPI.MODID, CuriosAPI.IMC.REGISTER_TYPE,
                    () -> new CurioIMCMessage(slot));
        }

        InterModComms.sendTo(CuriosAPI.MODID, CuriosAPI.IMC.REGISTER_ICON,
                () -> new Tuple<>("pocket", new ResourceLocation(Reference.MODID,"textures/item/empty_pocket_slot.png")));
        InterModComms.sendTo(CuriosAPI.MODID, CuriosAPI.IMC.REGISTER_ICON,
                () -> new Tuple<>("wrist", new ResourceLocation(Reference.MODID,"textures/item/empty_wrist_slot.png")));
        InterModComms.sendTo(CuriosAPI.MODID, CuriosAPI.IMC.REGISTER_ICON,
                () -> new Tuple<>("mask", new ResourceLocation(Reference.MODID,"textures/item/empty_mask_slot.png")));


    }


    public void onCommonSetup(final FMLCommonSetupEvent event)
    {

        LootFunctionManager.registerFunction(new BlockItemFunction.Serializer());
        LootFunctionManager.registerFunction(new HarderSetCount.Serializer());
        //LootConditionManager.registerCondition(new DestroyedBy.Serializer());


        CapabilityManager.INSTANCE.register(IRadiation.class,       new RadiationStorage(),      new RadiationStorage.Factory());
        CapabilityManager.INSTANCE.register(IOreVeins.class,        new OreVeinStorage(),        new OreVeinStorage.Factory());
        CapabilityManager.INSTANCE.register(IIdentityHidden.class,  new IdentityHiddenStorage(), new IdentityHiddenStorage.Factory());
        //CapabilityManager.INSTANCE.register(IPattern.class,         new PatternStorage(),        new PatternStorage.Factory());

        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapabilityEntity);
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapabilityChunk);
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapabilityItem);

        OreGen.setupOreGen();

        //PROXY.setup();
    }

    public void onClientSetup(final FMLClientSetupEvent event)
    {
        //ClientRegistry.bindTileEntitySpecialRenderer(TreeTapTileEntity.class, new TreeTapTileEntityRenderer<>()); // Broken as heck.
    }
}
