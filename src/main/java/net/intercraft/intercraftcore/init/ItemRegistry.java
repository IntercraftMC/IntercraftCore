package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.elements.Copper;
import net.intercraft.intercraftcore.item.ItemTest;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class ItemRegistry {

    /**
     * A test item for reference
     */
    public static final Item ITEM_TEST;

    public static final Item COPPER;

    /**
     * The complete list of items to help automate registration
     */
    protected static final List<Item> items;

    static
    {
        /**
         * Create the list of items to register
         */
        items = Arrays.asList(
                ITEM_TEST = new ItemTest((new Item.Properties()).group(ItemGroup.REDSTONE)),
                COPPER = new Copper()

        );
    }

    /**
     * Register items
     * @param event
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        if (event.getGenericType() == Item.class)
            event.getRegistry().registerAll((Item[]) items.toArray());
    }
}
