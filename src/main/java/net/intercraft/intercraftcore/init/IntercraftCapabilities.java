package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.block.BlockBubbleColumn;
import net.intercraft.intercraftcore.init.capabilities.bubble_column_drag.BubbleColumnDragProvider;
import net.intercraft.intercraftcore.init.capabilities.bubble_column_drag.BubbleColumnDragStorage;
import net.intercraft.intercraftcore.init.capabilities.bubble_column_drag.IBubbleColumnDrag;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IIdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenProvider;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenStorage;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.IOreVeins;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.OreVeinProvider;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.OreVeinStorage;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationStorage;
import net.intercraft.intercraftcore.item.masks.ModelBand;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;


@Mod.EventBusSubscriber
public class IntercraftCapabilities
{

    public static final ResourceLocation
            RAD_ID = new ResourceLocation(Reference.MODID,"radiation"),
            HID_ID = new ResourceLocation(Reference.MODID,"identity_hidden"),
            //BUC_ID = new ResourceLocation(Reference.MODID,"bubble_column_drag"),
            VEN_ID = new ResourceLocation(Reference.MODID,"ore_vein");

    public static void init()
    {
        CapabilityManager.INSTANCE.register(IRadiation.class,        new RadiationStorage(),        new RadiationStorage.Factory());
        CapabilityManager.INSTANCE.register(IIdentityHidden.class,   new IdentityHiddenStorage(),   new IdentityHiddenStorage.Factory());
        CapabilityManager.INSTANCE.register(IBubbleColumnDrag.class, new BubbleColumnDragStorage(), new BubbleColumnDragStorage.Factory());
        CapabilityManager.INSTANCE.register(IOreVeins.class,         new OreVeinStorage(),          new OreVeinStorage.Factory());

    }


    /*@SubscribeEvent
    public static void attachCapabilityBlock(AttachCapabilitiesEvent<Block> event)
    {
        if (BlockTags.getCollection().getOrCreate(BlockBubbleColumn.transparentID).contains(event.getObject()) && event.getObject().getDefaultState().has(WATERLOGGED)) {
            event.addCapability(BUC_ID, new BubbleColumnDragProvider());
        }
    }*/


    @SubscribeEvent
    public static void attachCapabilityItem(AttachCapabilitiesEvent<ItemStack> event)
    {

        if (!(event.getObject() instanceof ItemStack)) return;

        if (event.getObject().getItem() != Items.CLOCK) return;



        ResourceLocation texture = new ResourceLocation(Reference.MODID,"textures/masks/clock.png");


        ICurio curio = new ICurio() {
            @Override
            public boolean hasRender(String identifier, LivingEntity entityLivingBase)
            {
                return true;
            }

            @Override
            public void doRender(String identifier, LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {

                Minecraft.getInstance().getTextureManager().bindTexture(texture);

                net.intercraft.intercraftcore.api.RenderHelper.followLeftArmTransformation(entitylivingbaseIn, ModelBand.modelClock.wrist);
                ModelBand.modelClock.render(entitylivingbaseIn,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scale);


            }
        };

        ICapabilityProvider provider = new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
                                                     @Nullable Direction side) {

                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };

        event.addCapability(CuriosCapability.ID_ITEM, provider);


    }

    @SubscribeEvent
    public static void attachCapabilityEntity(AttachCapabilitiesEvent<Entity> event)
    {

        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(HID_ID, new IdentityHiddenProvider((PlayerEntity) event.getObject()));

        if (event.getObject() instanceof LivingEntity)
            if (!((LivingEntity) event.getObject()).isEntityUndead())
                if (event.getObject() instanceof CreatureEntity || event.getObject() instanceof PlayerEntity) {

                    event.addCapability(RAD_ID, new RadiationProvider());

                }
    }

    @SubscribeEvent
    public static void attachCapabilityChunk(AttachCapabilitiesEvent<Chunk> event)
    {
        event.addCapability(VEN_ID, new OreVeinProvider());
    }
}