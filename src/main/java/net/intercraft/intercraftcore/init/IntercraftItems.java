package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.element.*;
import net.intercraft.intercraftcore.item.ItemBucketResin;
import net.intercraft.intercraftcore.item.ItemTest;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.item.group.ItemUraniumGroup;
import net.intercraft.intercraftcore.item.masks.ItemGlasses;
import net.intercraft.intercraftcore.item.masks.ItemMask;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.intercraft.intercraftcore.ore.elements.ItemHardChunkCopper;
import net.intercraft.intercraftcore.ore.elements.ItemHardChunkLead;
import net.intercraft.intercraftcore.ore.elements.ItemHardChunkTin;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class IntercraftItems
{
    /**
     * Miscellaneous Items
     */
    public static final Item TEST;
    public static final Item BUCKETRESIN;


    /**
    * Face masks
    * */

    public static final ItemMask DEVILMASK;

    public static final ItemGlasses SUNGLASSES;


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

        DEVILMASK = new ItemMask("devil_mask",new ResourceLocation(Reference.MODID,"textures/masks/devil_mask.png"), true);

        SUNGLASSES = new ItemGlasses("sun_glasses", new ResourceLocation(Reference.MODID,"textures/masks/sun_glasses.png"));

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

        registerItems(DEVILMASK,SUNGLASSES);

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

    /*protected static void registerBlocks(Block... blocks)
    {
        for (Block block : blocks) {
            if (block != null) {
                RegistrationHandler.blocks.add(block);
                RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(IntercraftItemGroups.RESOURCES)).setRegistryName(block.getRegistryName()));
            }
        }
    }*/

    /**
     * Register an element group
     * @param group
     */
    protected static void registerElementItems(ItemElementGroup group)
    {
        registerItems(group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE);
        //registerBlocks(group.BLOCK_SOLID);
    }

}
