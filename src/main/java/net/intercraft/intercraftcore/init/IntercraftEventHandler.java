package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.item.ItemElement;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.ore.BlockHardOre;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.lang.reflect.Type;


public class IntercraftEventHandler
{
    /*public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        System.out.println(event.player.getName());
    }*/
    public static void onPLayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        //IRadiation radiation = event.getPlayer().getCapability();
        //IRadiation radiation = event.getPlayer().getCapability(RadiationProvider.RAD_CAP, null);
    }

    public static void ColorHandler(ColorHandlerEvent event) {
        ItemElementGroup[] groups = {
                IntercraftItems.COPPER,
                IntercraftItems.LEAD,
                IntercraftItems.SILVER,
                IntercraftItems.TIN,
                IntercraftItems.TUNGSTEN,
                IntercraftItems.URANIUM
        };



        Type generic = event.getClass();

        if (generic == ColorHandlerEvent.Block.class) {

        } else if (generic == ColorHandlerEvent.Item.class) {

        }
    }

    public static void ItemColorHandlerEvent(ColorHandlerEvent.Item itemColorHandlerEvent)
    {

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
                    itemColorHandlerEvent.getItemColors().register((ItemElement)item, item);
                }
            }
        }

        Block[] blocks = {
                IntercraftBlocks.COPPERORE,
                IntercraftBlocks.TINORE
        };

        Item[] items = {
                IntercraftItems.COPPERCHUNK,
                IntercraftItems.TINCHUNK
        };

        for (Block block : blocks)
            itemColorHandlerEvent.getItemColors().register((BlockHardOre)block, block);
        for (Item item : items)
            itemColorHandlerEvent.getItemColors().register((ItemHardChunk)item, item);
    }

    public static void BlockColorHandlerEvent(ColorHandlerEvent.Block blockColorEvent)
    {

        Block[] blocks = {
                IntercraftBlocks.COPPERORE,
                IntercraftBlocks.TINORE
        };

        for (Block block : blocks)
            blockColorEvent.getBlockColors().register((BlockHardOre)block, block);
    }
}
