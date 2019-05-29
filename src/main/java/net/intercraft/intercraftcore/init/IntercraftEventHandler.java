package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.Radiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationStorage;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class IntercraftEventHandler
{
    /*public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        System.out.println(event.player.getName());
    }*/
    public static void onPLayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        //IRadiation radiation = event.getPlayer().getCapability();
    }
}
