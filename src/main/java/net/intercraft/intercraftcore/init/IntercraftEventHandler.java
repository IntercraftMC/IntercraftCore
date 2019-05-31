package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.item.element.ItemElement;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;


public class IntercraftEventHandler
{
    /*public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        System.out.println(event.player.getName());
    }*/
    public static void onPLayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        //IRadiation radiation = event.getPlayer().getCapability();
        //IRadiation radiation = event.getPlayer().getCapability(RadiationProvider.RAD_CAP, null);
    }

    public static void ColorHandlerEvent(ColorHandlerEvent.Item itemColorHandlerEvent) {
        //itemColorHandlerEvent.getItemColors().getColor(new ItemStack(IntercraftItems.COPPER),1);
        itemColorHandlerEvent.getItemColors().register((a,b) ->  {
            return ((ItemElement)a.getItem()).getColor();
        }, IntercraftItems.COPPER, IntercraftItems.URANIUM);

    }

}
