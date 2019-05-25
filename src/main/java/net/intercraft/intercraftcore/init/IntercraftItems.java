package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.elements.*;
import net.intercraft.intercraftcore.item.ItemTest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * All items are created and grouped here
 */
public class IntercraftItems {

    /**
     * A test item for reference
     */
    public static final Item TEST;

    /**
     * Element Items
     */
    public static final Item COPPER;
    public static final Item LEAD;
    public static final Item SILVER;
    public static final Item TIN;
    public static final Item TUNGSTEN;
    public static final Item URANIUM;

    /**
     * Instantiate all items
     */
    static
    {
        TEST = new ItemTest((new Item.Properties()).group(IntercraftItemGroups.RESOURCES));
        COPPER = new Copper();
        LEAD = new Lead();
        SILVER = new Silver();
        TIN = new Tin();
        TUNGSTEN = new Tungsten();
        URANIUM = new Uranium();
    }

    /**
     * Register all items
     */
    public static void register()
    {
        registerItem(TEST);
        registerItem(COPPER);
        registerItem(LEAD);
        registerItem(SILVER);
        registerItem(TIN);
        registerItem(TUNGSTEN);
        registerItem(URANIUM);
    }

    /**
     * Register an item
     */
    protected static void registerItem(Item item)
    {
        RegistrationHandler.items.add(item);
    }
}
