package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.elements.*;
import net.intercraft.intercraftcore.item.ItemTest;
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
    public static final Item LEAD;
    public static final Item SILVER;
    public static final Item TIN;
    public static final Item TUNGSTEN;
    public static final Item URANIUM;

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

                COPPER = new Copper(),
                LEAD = new Lead(),
                SILVER = new Silver(),
                TIN = new Tin(),
                TUNGSTEN = new Tungsten(),
                URANIUM = new Uranium()

        );
    }

    /**
     * Register items
     * @param event
     */
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        if (event.getGenericType() == Item.class)
            event.getRegistry().registerAll((Item[]) items.toArray());
    }
}
