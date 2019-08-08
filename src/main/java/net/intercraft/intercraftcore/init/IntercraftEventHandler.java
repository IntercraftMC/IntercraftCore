package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.AnimalEntity;
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

    //public static void onEntityTick(TickEvent.E)



    /*@SubscribeEvent
    public void onEntityAdded(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityAnimal || event.getEntity() instanceof EntityPlayer) {
            event.getEntity().getCapability(IntercraftCore.radiation.RAD_CAP);
        }
    }*/



    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {

        //TODO do it on all living entities except mobs from the blacklist (ex. Undead).

        if (event.getObject() instanceof PlayerEntity || event.getObject() instanceof AnimalEntity ||
            event.getObject() instanceof CreeperEntity || event.getObject() instanceof SpiderEntity ||
            event.getObject() instanceof EndermanEntity || event.getObject() instanceof EndermiteEntity ||
            event.getObject() instanceof VillagerEntity) {
            event.addCapability(IntercraftCore.RAD_ID, new RadiationProvider());
        }
    }




}
