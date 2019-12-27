package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.Sounds;
import net.intercraft.intercraftcore.block.BlockFrameLithium;
import net.intercraft.intercraftcore.block.BlockSolidLithium;
import net.intercraft.intercraftcore.command.IdentityHiddenDebugCommand;
import net.intercraft.intercraftcore.command.OreVeinDebugCommand;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IIdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenProvider;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.Radiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.intercraft.intercraftcore.item.ItemLithium;
import net.intercraft.intercraftcore.networking.messages.MessageIdentityHidden;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import java.util.Random;

@Mod.EventBusSubscriber
public class IntercraftEventHandler
{

    private static final ResourceLocation cobblestoneID     = new ResourceLocation("forge","cobblestone");
    private static final ResourceLocation gravelID          = new ResourceLocation("forge","gravel");
    private static final ResourceLocation anvilID           = new ResourceLocation("minecraft","anvil");
    private static final ResourceLocation smashableBlocksID = new ResourceLocation(Reference.MODID,"smashable_blocks");


    private static final float maxReactionVolume = 4.6f;

    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event)
    {
        RadiationDebugCommand.register(event.getCommandDispatcher());
        OreVeinDebugCommand.register(event.getCommandDispatcher());
        IdentityHiddenDebugCommand.register(event.getCommandDispatcher());
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderTickEvent(final TickEvent.RenderTickEvent event)
    {
        if (Minecraft.getInstance().isGameFocused()) {
            Minecraft.getInstance().mainWindow.setFramerateLimit(Minecraft.getInstance().gameSettings.framerateLimit);
        } else {
            Minecraft.getInstance().mainWindow.setFramerateLimit(4);
        }
    }

    //@SubscribeEvent
    public static void onBlockFall(final EntityEvent.EntityConstructing event)
    {

        //if (!(event.getEntity() instanceof PlayerEntity))
        if (event.getEntity() instanceof FallingBlockEntity)
            System.out.println(event.getEntity().getDisplayName().getString());
    }

    @SubscribeEvent//(priority = EventPriority.HIGHEST)
    public static void onBlockHarvest(final BlockEvent.HarvestDropsEvent event)
    {
        ItemStack tool = event.getHarvester().getActiveItemStack();
        if (!tool.isEnchanted()) return;
        int lvl = EnchantmentHelper.getEnchantmentLevel(IntercraftEnchantments.AUTO_PICKUP,tool);
        if (lvl > 0) {
            for (ItemStack drop : event.getDrops()) {
                boolean moved = event.getHarvester().addItemStackToInventory(drop);
                if (moved) {
                    drop.shrink(drop.getCount());
                    //System.out.println("Moved the drop to player's inventory.");
                }
            }
        }

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

                if (entity instanceof ItemEntity) {
                    ItemStack stack = ((ItemEntity) entity).getItem();
                    //if (ItemTags.getCollection().getOrCreate(reactiveWithWater).contains(stack.getItem()))
                    if (stack.getItem() instanceof ItemLithium || getBlock(stack) instanceof BlockSolidLithium || getBlock(stack) instanceof BlockFrameLithium)
                        if (entity.isInWater()) {
                            double x = entity.posX, y = entity.posY, z = entity.posZ;
                            entity.remove();

                            float volume = Math.min(maxReactionVolume,stack.getCount()*0.6f), range = Math.min(0.6f,0.3f*(stack.getCount()));
                            short smoke = ((short) (24 * (stack.getCount()*0.5f)));

                            if (stack.getItem() instanceof BlockItem) { // Large item.
                                //volume = 0.8f+stack.getCount()*0.2f;
                                volume = Math.min(maxReactionVolume,stack.getCount()*0.8f);
                                range  = Math.min(0.8f,0.3f*stack.getCount());
                                smoke  = ((short) (96 * (stack.getCount()*0.5f)));
                                //entity.world.createExplosion(entity,x,y,z,0.1f,Explosion.Mode.BREAK);
                            } else if (isIn(stack,"_nugget") || isIn(stack,"_dustsmall")) {  // Small item.
                                //volume = 0.1f+stack.getCount()*0.2f;
                                volume = Math.min(maxReactionVolume,stack.getCount()*0.3f);
                                smoke  = ((short) (16 * (stack.getCount()*0.5f)));
                            }


                            if (Minecraft.getInstance().world != null) {

                                Minecraft.getInstance().world.playSound(x,y,z, Sounds.REACTION_WATER, SoundCategory.AMBIENT,volume,1,false);
                                for (short i=0;i<smoke;i++) {
                                    Minecraft.getInstance().world.addParticle(ParticleTypes.LARGE_SMOKE, x+nextRandomRange(-range,range), y+nextRandomRange(0,0.6f+range), z+nextRandomRange(-range,range), 0, -0.04f, 0);
                                }


                            }
                        }
                }


                if (entity instanceof FallingBlockEntity) {

                    if (!(BlockTags.getCollection().getOrCreate(anvilID).contains(((FallingBlockEntity) entity).getBlockState().getBlock()))) return;

                    BlockPos pos = entity.getPosition();
                    BlockPos blockPos = new BlockPos(pos.getX(),pos.getY()-0.1d,pos.getZ());



                    if (BlockTags.getCollection().getOrCreate(smashableBlocksID).contains(event.world.getBlockState(blockPos).getBlock())) {
                        entity.addVelocity(0,0.1d,0);

                        event.world.destroyBlock(blockPos,true);
                    }

                }

                if (event.world.getGameTime() % Radiation.tickrate == 0)
                    entity.getCapability(RadiationProvider.RAD_CAP).ifPresent(cap -> cap.tick(entity));
            });
        }
    }

    private static boolean isIn(ItemStack stack, String search)
    {
        return stack.getItem().getRegistryName().getPath().contains(search);
    }

    private static float nextRandomRange(float min, float max) {
        Random r = new Random();
        return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0f;
    }

    private static Block getBlock(ItemStack stack)
    {
        return Block.getBlockFromItem(stack.getItem());
    }
}
