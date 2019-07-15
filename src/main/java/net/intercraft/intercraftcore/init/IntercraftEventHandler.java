package net.intercraft.intercraftcore.init;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


@Mod.EventBusSubscriber
public class IntercraftEventHandler
{
    //@SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //System.out.println(event.player.getName());
        //event.player.sendMessage(new TextComponentString("Spamming!!"));

        // Not sure if it would be better to have a big server tick function lowering the player's @exposure value by 1 per tick or make a different clock for each player. Just do it on online players.
    }
    @SubscribeEvent
    public static void onPLayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        event.getPlayer().sendMessage(new TextComponentString(String.format("Hello %s.", event.getPlayer().getName().getFormattedText())));

        //IRadiation radiation = event.getPlayer().getCapability();
        //IRadiation radiation = event.getPlayer().getCapability(RadiationProvider.RAD_CAP, null);


        // Check whenever the player already has a @exposure value and then add the capability radiation to them with the value, otherwise put it on the minimum threshold.
    }




}
