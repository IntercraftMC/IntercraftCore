package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.command.OreVeinDebugCommand;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.OreVeinProvider;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class IntercraftEventHandler
{

    /*private static final ResourceLocation cobblestoneID = new ResourceLocation("forge","cobblestone");
    private static final ResourceLocation gravelID = new ResourceLocation("forge","gravel");*/

    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event)
    {
        RadiationDebugCommand.register(event.getCommandDispatcher());
        OreVeinDebugCommand.register(event.getCommandDispatcher());
    }


    /*public static void onExplosion(final ExplosionEvent event)
    {
        //System.out.println(event.getExplosion().getPosition());

        if (!event.getWorld().isRemote) {
            for (BlockPos pos : event.getExplosion().getAffectedBlockPositions()) {
                Block block = event.getWorld().getBlockState(pos).getBlock();

                if (BlockTags.getCollection().getOrCreate(cobblestoneID).contains(block)) {
                    //System.out.println("Blew up Cobblestone.");

                } else if (BlockTags.getCollection().getOrCreate(gravelID).contains(block)) {
                    System.out.println("Blew up Gravel.");
                }
            }
        }

    }*/


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




            ((ServerWorld) event.world).getEntities().forEach(entity -> {

                entity.getCapability(RadiationProvider.RAD_CAP).ifPresent(cap -> cap.tick(entity));
            });
        }
    }




    public static void attachCapabilityEntity(AttachCapabilitiesEvent<Entity> event)
    {
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
