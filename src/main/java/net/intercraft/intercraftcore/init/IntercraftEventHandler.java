package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
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
        for (TileEntity entity : event.world.loadedTileEntityList) {
            if (entity.getCapability(RadiationProvider.RAD_CAP).isPresent()) {
                IRadiation cap = entity.getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());
                cap.tick(entity);
            }

        }
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

        if (event.getObject() instanceof EntityPlayer || event.getObject() instanceof EntityAnimal ||
            event.getObject() instanceof EntityCreeper || event.getObject() instanceof EntitySpider ||
            event.getObject() instanceof EntityEnderman || event.getObject() instanceof EntityEndermite) {
            event.addCapability(IntercraftCore.RAD_ID, new RadiationProvider());
        }
    }




}
