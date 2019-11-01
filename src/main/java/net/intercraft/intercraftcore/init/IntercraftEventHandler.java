package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.command.IdentityHiddenDebugCommand;
import net.intercraft.intercraftcore.command.OreVeinDebugCommand;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IIdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenProvider;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.OreVeinProvider;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.intercraft.intercraftcore.item.masks.ModelBand;
import net.intercraft.intercraftcore.networking.MessageIdentityHidden;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

@Mod.EventBusSubscriber
public class IntercraftEventHandler
{

    private static final ResourceLocation cobblestoneID = new ResourceLocation("forge","cobblestone");
    private static final ResourceLocation gravelID = new ResourceLocation("forge","gravel");
    private static final ResourceLocation anvilID = new ResourceLocation("minecraft","anvil");
    private static final ResourceLocation smashableBlocksID = new ResourceLocation(Reference.MODID,"smashable_blocks");

    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event)
    {
        RadiationDebugCommand.register(event.getCommandDispatcher());
        OreVeinDebugCommand.register(event.getCommandDispatcher());
        IdentityHiddenDebugCommand.register(event.getCommandDispatcher());
    }

    //@SubscribeEvent
    public static void onBlockFall(final EntityEvent.EntityConstructing event)
    {

        //if (!(event.getEntity() instanceof PlayerEntity))
        if (event.getEntity() instanceof FallingBlockEntity)
            System.out.println(event.getEntity().getDisplayName().getString());
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(final PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof PlayerEntity) {
            IIdentityHidden hiddenTarget = event.getTarget().getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
            IntercraftCore.NETWORK.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new MessageIdentityHidden(event.getPlayer().getEntityId(), hiddenTarget.getHidden()));
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event)
    {
        IdentityHidden.updatePlayerNames(event.getPlayer(),event.getPlayer().dimension);
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(final PlayerEvent.PlayerChangedDimensionEvent event)
    {
        IdentityHidden.updatePlayerNames(event.getPlayer(),event.getTo());
    }


    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event)
    {
        if (!event.isWasDeath()) {
            IRadiation radiationOri = event.getOriginal().getCapability(RadiationProvider.RAD_CAP).orElseThrow(NullPointerException::new);
            IRadiation radiationNew = event.getPlayer().getCapability(RadiationProvider.RAD_CAP).orElseThrow(NullPointerException::new);
            radiationNew.setAbsorbed(radiationOri.getAbsorbed());
            radiationNew.setExposure(radiationOri.getExposure());

            IIdentityHidden hiddenOri = event.getOriginal().getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
            IIdentityHidden hiddenNew = event.getPlayer().getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
            hiddenNew.setHidden(hiddenOri.getHidden());
        }
        IdentityHidden.updatePlayerNames(event.getPlayer(),event.getPlayer().dimension);
    }




    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderEntity(RenderLivingEvent.Specials.Pre event)
    {
        if (event.getEntity() instanceof PlayerEntity) {
            if (event.isCancelable()) {

                IIdentityHidden hidden = event.getEntity().getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
                if (hidden.getHidden())
                    event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onExplosion(final ExplosionEvent.Detonate event)
    {
        Random random = new Random();

        //System.out.println(event.getExplosion().getPosition());

        if (!event.getWorld().isRemote) {
            for (BlockPos pos : event.getExplosion().getAffectedBlockPositions()) {
                Block block = event.getWorld().getBlockState(pos).getBlock();

                if (BlockTags.getCollection().getOrCreate(cobblestoneID).contains(block)) {
                    if (random.nextInt(9) == 0) {
                        if (random.nextInt(9) == 0)
                            event.getWorld().setBlockState(pos, IntercraftBlocks.SANDSUBSTITUTE.getDefaultState());
                        else
                            event.getWorld().setBlockState(pos, IntercraftBlocks.GRAVELSUBSTITUTE.getDefaultState());
                    }


                } else if (BlockTags.getCollection().getOrCreate(gravelID).contains(block)) {
                    if (random.nextInt(9) == 0) {
                        event.getWorld().setBlockState(pos, IntercraftBlocks.SANDSUBSTITUTE.getDefaultState());
                    } else {
                        event.getWorld().setBlockState(pos, IntercraftBlocks.GRAVELSUBSTITUTE.getDefaultState());
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onLightningHit(final EntityStruckByLightningEvent event)
    {
        Entity entity = event.getEntity();

        if (entity.getType() == EntityType.GUARDIAN) {
            ElderGuardianEntity elderGuardianEntity = EntityType.ELDER_GUARDIAN.create(entity.world);

            elderGuardianEntity.setLocationAndAngles(entity.posX,entity.posY,entity.posZ,entity.rotationYaw,entity.rotationPitch);

            entity.world.addEntity(elderGuardianEntity);
            entity.remove();

        }
    }


    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.world instanceof ServerWorld) {

            /*Chunk chunk = event.world.getChunk(0,0);

            chunk.getCapability(PatternProvider.PAT_CAP).isPresent(cap -> cap.tick());*/


            ((ServerWorld) event.world).getEntities().forEach(entity -> {

                if (entity instanceof FallingBlockEntity) {

                    if (!(BlockTags.getCollection().getOrCreate(anvilID).contains(((FallingBlockEntity) entity).getBlockState().getBlock()))) return;

                    BlockPos pos = entity.getPosition();
                    BlockPos blockPos = new BlockPos(pos.getX(),pos.getY()-0.1d,pos.getZ());



                    if (BlockTags.getCollection().getOrCreate(smashableBlocksID).contains(event.world.getBlockState(blockPos).getBlock())) {
                        entity.addVelocity(0,0.1d,0);

                        event.world.destroyBlock(blockPos,true);
                    }

                }

                entity.getCapability(RadiationProvider.RAD_CAP).ifPresent(cap -> cap.tick(entity));
            });
        }
    }



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
            private final LazyOptional<ICurio> curioOpt =
                    LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
                                                     @Nullable Direction side) {

                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };

        event.addCapability(CuriosCapability.ID_ITEM, provider);


    }


    public static void attachCapabilityEntity(AttachCapabilitiesEvent<Entity> event)
    {

        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(IntercraftCore.HID_ID, new IdentityHiddenProvider((PlayerEntity) event.getObject()));

        if (event.getObject() instanceof LivingEntity)
            if (!((LivingEntity) event.getObject()).isEntityUndead())
                if (event.getObject() instanceof CreatureEntity || event.getObject() instanceof PlayerEntity) {

                    event.addCapability(IntercraftCore.RAD_ID, new RadiationProvider());

                }
    }

    public static void attachCapabilityChunk(AttachCapabilitiesEvent<Chunk> event)
    {
        event.addCapability(IntercraftCore.VEIN_ID, new OreVeinProvider());
    }
}
