package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.ore.BlockHardOre;
import net.intercraft.intercraftcore.ore.BlockOreCopper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
        ItemElementGroup[] groups = {
                IntercraftItems.COPPER,
                IntercraftItems.LEAD,
                IntercraftItems.SILVER,
                IntercraftItems.TIN,
                IntercraftItems.TUNGSTEN,
                IntercraftItems.URANIUM
        };
        for (ItemElementGroup group : groups) {
            Item[] items = { group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE };
            for (Item item : items) {
                if (item != null) {
                    itemColorHandlerEvent.getItemColors().register((a, b) -> {
                        return group.element.tint;
                    }, item);
                }
            }
        }

        /*itemColorHandlerEvent.getItemColors().register((a,b) -> { // Doesn't work. :/
            return ((BlockHardOre)a.getItem()).getTint();
        }, IntercraftBlocks.COPPERORE.asItem());*/

    }

    public static void ColorBlockHandlerEvent(ColorHandlerEvent.Block blockColorEvent) {
        blockColorEvent.getBlockColors().register((a,b,c,d) -> {
            return ((BlockHardOre)a.getBlock()).getTint();
        }, IntercraftBlocks.COPPERORE, IntercraftBlocks.TINORE);
    }

}
