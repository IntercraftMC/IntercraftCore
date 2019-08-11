package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.element.*;
import net.intercraft.intercraftcore.item.ItemBucketResin;
import net.intercraft.intercraftcore.item.ItemTest;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.item.group.ItemUraniumGroup;
import net.intercraft.intercraftcore.ore.ElementComposition;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.intercraft.intercraftcore.ore.elements.ItemHardChunkCopper;
import net.intercraft.intercraftcore.ore.elements.ItemHardChunkLead;
import net.intercraft.intercraftcore.ore.elements.ItemHardChunkTin;
import net.minecraft.item.Item;

public class IntercraftItems
{
    /**
     * A test item for reference
     */
    public static final Item TEST;
    public static final Item BUCKETRESIN;

    /**
     * Item Groups
     */
    public static final ItemElementGroup COPPER;
    public static final ItemElementGroup LEAD;
    public static final ItemElementGroup SILVER;
    public static final ItemElementGroup TIN;
    public static final ItemElementGroup TUNGSTEN;
    public static final ItemElementGroup URANIUM;


    /**
     * Ore Chunks
     * */

    public static final ItemHardChunk COPPERCHUNK;
    public static final ItemHardChunk TINCHUNK;
    public static final ItemHardChunk LEADCHUNK;

    /**
     * Instantiate all items
     */
    static {
        TEST = new ItemTest((new Item.Properties()).group(IntercraftItemGroups.RESOURCES));
        BUCKETRESIN = new ItemBucketResin();

        COPPER = new ItemElementGroup(new Copper());
        LEAD = new ItemElementGroup(new Lead());
        SILVER = new ItemElementGroup(new Silver());
        TIN = new ItemElementGroup(new Tin());
        TUNGSTEN = new ItemElementGroup(new Tungsten());
        URANIUM = new ItemUraniumGroup(new Uranium());


        COPPERCHUNK = new ItemHardChunkCopper();
        TINCHUNK = new ItemHardChunkTin();
        LEADCHUNK = new ItemHardChunkLead();
    }

    /**
     * Register all items
     */
    public static void register()
    {
        registerItem(TEST);

        registerItems(BUCKETRESIN);

        registerElementItems(COPPER);
        registerElementItems(LEAD);
        registerElementItems(SILVER);
        registerElementItems(TIN);
        registerElementItems(TUNGSTEN);
        registerElementItems(URANIUM);

        registerItems(COPPERCHUNK,TINCHUNK,LEADCHUNK);
    }

    /**
     * Register an item
     * @param item
     */
    protected static void registerItem(Item item)
    {
        RegistrationHandler.items.add(item);
    }

    /**
     * Register an array of items
     * @param items
     */
    protected static void registerItems(Item... items)
    {
        for (Item item : items) {
            if (item != null) {
                RegistrationHandler.items.add(item);
            }
        }
    }

    /**
     * Register an element group
     * @param group
     */
    protected static void registerElementItems(ItemElementGroup group)
    {
        registerItems(group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE);
    }

}
