package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.init.IntercraftCapabilities;
import net.intercraft.intercraftcore.init.IntercraftContainerTypes;
import net.intercraft.intercraftcore.init.RegistrationHandler;
import net.intercraft.intercraftcore.init.gen.OreGen;
import net.intercraft.intercraftcore.init.loot.conditions.ConditionIsFluid;
import net.intercraft.intercraftcore.init.loot.conditions.ConditionIsFull;
import net.intercraft.intercraftcore.init.loot.functions.FunctionBlockItemFunction;
import net.intercraft.intercraftcore.init.loot.functions.FunctionHarderSetCount;
import net.intercraft.intercraftcore.inventory.container.ContainerScreenChunkLoaderTimer;
import net.intercraft.intercraftcore.inventory.container.ContainerScreenSingleItemStackContainer;
import net.intercraft.intercraftcore.networking.IntercraftPacketHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
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

        IntercraftCapabilities.init();

        LootFunctionManager.registerFunction(new FunctionBlockItemFunction.Serializer());
        LootFunctionManager.registerFunction(new FunctionHarderSetCount.Serializer());
        //LootConditionManager.registerCondition(new DestroyedBy.Serializer());
        LootConditionManager.registerCondition(new ConditionIsFull.Serializer());
        LootConditionManager.registerCondition(new ConditionIsFluid.Serializer());
        //CapabilityManager.INSTANCE.register(IPattern.class,         new PatternStorage(),        new PatternStorage.Factory());


        /*MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapabilityEntity);
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapabilityChunk);
        MinecraftForge.EVENT_BUS.addListener(IntercraftEventHandler::attachCapabilityItem);*/

        OreGen.setupOreGen();

        //PROXY.setup();
    }

    public void onClientSetup(final FMLClientSetupEvent event)
    {
        ScreenManager.registerFactory(IntercraftContainerTypes.ITEMITEMSTACK_CONTAINER, ContainerScreenSingleItemStackContainer::new);
        ScreenManager.registerFactory(IntercraftContainerTypes.CHUNKLOADER_TIMER_INTERFACE, ContainerScreenChunkLoaderTimer::new);
    }
}
