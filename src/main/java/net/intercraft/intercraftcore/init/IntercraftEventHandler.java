package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;


@Mod.EventBusSubscriber
public class IntercraftEventHandler
{


    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event)
    {
        RadiationDebugCommand.register(event.getCommandDispatcher());
    }


    @SubscribeEvent
    public static void onEntityTick(TickEvent.WorldTickEvent event)
    {
        if (event.world instanceof ServerWorld)
            ((ServerWorld) event.world).getEntities().forEach(entity -> {
                entity.getCapability(RadiationProvider.RAD_CAP).ifPresent(cap -> cap.tick(entity));
            });
    }




    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof LivingEntity)
            if (!((LivingEntity) event.getObject()).isEntityUndead())
                if (event.getObject() instanceof CreatureEntity || event.getObject() instanceof PlayerEntity) {

                    event.addCapability(IntercraftCore.RAD_ID, new RadiationProvider());

                }
    }
}
