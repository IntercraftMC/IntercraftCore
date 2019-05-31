package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.item.element.*;
import net.intercraft.intercraftcore.item.ItemTest;
import net.minecraft.item.Item;

public class IntercraftItems
{
    /**
     * A test item for reference
     */
    public static final Item TEST;

    /**
     * Element Items
     */
    public static final Item[] COPPER;
    public static final Item[] LEAD;
    public static final Item[] SILVER;
    public static final Item[] TIN;
    public static final Item[] TUNGSTEN;
    public static final Item[] URANIUM;

    /**
     * Instantiate all items
     */
    static {
        TEST     = new ItemTest((new Item.Properties()).group(IntercraftItemGroups.RESOURCES));
        COPPER   = Util.create(new Copper(),new boolean[] {true,true,true,true,true});
        LEAD     = Util.create(new Lead(),new boolean[] {true,true,true,true,true});
        SILVER   = Util.create(new Silver(),new boolean[] {true,true,true,true,true});
        TIN      = Util.create(new Tin(),new boolean[] {true,true,true,true,true});
        TUNGSTEN = Util.create(new Tungsten(),new boolean[] {true,true,true,true,true});
        URANIUM  = Util.create(new Uranium(),new boolean[] {true,true,true,true,true});
    }

    /**
     * Register all items
     */
    public static void register()
    {
        registerItem(TEST);
        registerItems(COPPER);
        registerItems(LEAD);
        registerItems(SILVER);
        registerItems(TIN);
        registerItems(TUNGSTEN);
        registerItems(URANIUM);
    }

    /**
     * Register an item
     */
    protected static void registerItem(Item item)
    {
        RegistrationHandler.items.add(item);
    }
    protected static void registerItems(Item[] item)
    {
        RegistrationHandler.items.add(item[0]);
    }
}
