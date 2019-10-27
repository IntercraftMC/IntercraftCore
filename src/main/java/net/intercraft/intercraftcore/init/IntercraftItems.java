package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.element.alloys.Brass;
import net.intercraft.intercraftcore.element.alloys.Bronze;
import net.intercraft.intercraftcore.element.alloys.Steel;
import net.intercraft.intercraftcore.element.metals.*;
import net.intercraft.intercraftcore.element.nonmetals.Carbon;
import net.intercraft.intercraftcore.element.nonmetals.Silicon;
import net.intercraft.intercraftcore.item.ItemBucketResin;
import net.intercraft.intercraftcore.item.ItemBucketWood;
import net.intercraft.intercraftcore.item.ItemTest;
import net.intercraft.intercraftcore.item.ItemWireCoil;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.item.group.ItemUraniumGroup;
import net.intercraft.intercraftcore.item.masks.ItemGlasses;
import net.intercraft.intercraftcore.item.masks.ItemMask;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class IntercraftItems
{
    /**
     * Miscellaneous Items
     */

    public static final Item TEST;

    /**
     * Face masks
     */

    public static final ItemMask DEVILMASK;
    public static final ItemGlasses SUNGLASSES;



    /**
    * Fluid Buckets
    */

    public static final Item BUCKET_OAK;
    public static final Item BUCKET_SPRUCE;
    public static final Item BUCKET_BIRCH;
    public static final Item BUCKET_JUNGLE;
    public static final Item BUCKET_ACACIA;
    public static final Item BUCKET_DARK_OAK;

    public static final Item WATER_BUCKET_OAK;
    public static final Item WATER_BUCKET_SPRUCE;
    public static final Item WATER_BUCKET_BIRCH;
    public static final Item WATER_BUCKET_JUNGLE;
    public static final Item WATER_BUCKET_ACACIA;
    public static final Item WATER_BUCKET_DARK_OAK;

    public static final Item RESIN_BUCKET_OAK;
    public static final Item RESIN_BUCKET_SPRUCE;
    public static final Item RESIN_BUCKET_BIRCH;
    public static final Item RESIN_BUCKET_JUNGLE;
    public static final Item RESIN_BUCKET_ACACIA;
    public static final Item RESIN_BUCKET_DARK_OAK;


    public static final Item RESIN_BUCKET;

    //public static final Item OILBUCKET;

    /**
     * Coils
     */

    public static final Item COPPERCOIL;


    /**
     * Item Groups
     */

    public static final ItemElementGroup ALUMINIUM;
    public static final ItemElementGroup COPPER;
    public static final ItemElementGroup GOLD;
    public static final ItemElementGroup IRIDIUM;
    public static final ItemElementGroup IRON;
    public static final ItemElementGroup LEAD;
    public static final ItemElementGroup SILVER;
    public static final ItemElementGroup THORIUM;
    public static final ItemElementGroup TIN;
    public static final ItemElementGroup TITANIUM;
    public static final ItemElementGroup TUNGSTEN;
    public static final ItemElementGroup URANIUM;
    public static final ItemElementGroup ZINC;

    public static final ItemElementGroup CARBON;
    public static final ItemElementGroup SILICON;

    public static final ItemElementGroup BRASS;
    public static final ItemElementGroup BRONZE;
    public static final ItemElementGroup STEEL;



    /**
     * Instantiate all items
     */

    static {
        TEST = new ItemTest((new Item.Properties()).group(ItemGroup.MISC));


        DEVILMASK = new ItemMask("devil_mask",new ResourceLocation(Reference.MODID,"textures/masks/devil_mask.png"));
        SUNGLASSES = new ItemGlasses("sun_glasses", new ResourceLocation(Reference.MODID,"textures/masks/sun_glasses.png"));


        BUCKET_OAK = new ItemBucketWood("oak");
        BUCKET_SPRUCE = new ItemBucketWood("spruce");
        BUCKET_BIRCH = new ItemBucketWood("birch");
        BUCKET_JUNGLE = new ItemBucketWood("jungle");
        BUCKET_ACACIA = new ItemBucketWood("acacia");
        BUCKET_DARK_OAK = new ItemBucketWood("dark_oak");

        WATER_BUCKET_OAK = new ItemBucketWood(Fluids.WATER,"oak",BUCKET_OAK);
        WATER_BUCKET_SPRUCE = new ItemBucketWood(Fluids.WATER,"spruce",BUCKET_SPRUCE);
        WATER_BUCKET_BIRCH = new ItemBucketWood(Fluids.WATER,"birch",BUCKET_BIRCH);
        WATER_BUCKET_JUNGLE = new ItemBucketWood(Fluids.WATER,"jungle",BUCKET_JUNGLE);
        WATER_BUCKET_ACACIA = new ItemBucketWood(Fluids.WATER,"acacia",BUCKET_ACACIA);
        WATER_BUCKET_DARK_OAK = new ItemBucketWood(Fluids.WATER,"dark_oak",BUCKET_DARK_OAK);

        RESIN_BUCKET_OAK = new ItemBucketResin("oak");
        RESIN_BUCKET_SPRUCE = new ItemBucketResin("spruce");
        RESIN_BUCKET_BIRCH = new ItemBucketResin("birch");
        RESIN_BUCKET_JUNGLE = new ItemBucketResin("jungle");
        RESIN_BUCKET_ACACIA = new ItemBucketResin("acacia");
        RESIN_BUCKET_DARK_OAK = new ItemBucketResin("dark_oak");

        RESIN_BUCKET = new ItemBucketResin();


        COPPERCOIL = new ItemWireCoil("cu",0.2,1024,50, false, ElementDictionary.COPPER.getColorPrimary());



        ALUMINIUM = new ItemElementGroup(new Aluminium());
        COPPER = new ItemElementGroup(new Copper());
        GOLD = new ItemElementGroup(new Gold());
        IRIDIUM = new ItemElementGroup(new Iridium());
        IRON = new ItemElementGroup(new Iron());
        LEAD = new ItemElementGroup(new Lead());
        SILVER = new ItemElementGroup(new Silver());
        THORIUM = new ItemElementGroup(new Thorium());
        TIN = new ItemElementGroup(new Tin());
        TITANIUM = new ItemElementGroup(new Titanium());
        TUNGSTEN = new ItemElementGroup(new Tungsten());
        URANIUM = new ItemUraniumGroup(new Uranium());
        ZINC = new ItemElementGroup(new Zinc());

        CARBON = new ItemElementGroup(new Carbon());
        SILICON = new ItemElementGroup(new Silicon());

        BRASS = new ItemElementGroup(new Brass());
        BRONZE = new ItemElementGroup(new Bronze());
        STEEL = new ItemElementGroup(new Steel());
    }

    /**
     * Register all items
     */
    public static void register()
    {
        registerItem(TEST);

        registerItems(DEVILMASK,SUNGLASSES);


        registerItems(BUCKET_OAK,      BUCKET_SPRUCE,      BUCKET_BIRCH,      BUCKET_JUNGLE,      BUCKET_ACACIA,      BUCKET_DARK_OAK);
        registerItems(WATER_BUCKET_OAK,WATER_BUCKET_SPRUCE,WATER_BUCKET_BIRCH,WATER_BUCKET_JUNGLE,WATER_BUCKET_ACACIA,WATER_BUCKET_DARK_OAK);
        registerItems(RESIN_BUCKET_OAK,RESIN_BUCKET_SPRUCE,RESIN_BUCKET_BIRCH,RESIN_BUCKET_JUNGLE,RESIN_BUCKET_ACACIA,RESIN_BUCKET_DARK_OAK);
        registerItems(RESIN_BUCKET);

        registerItem(COPPERCOIL);


        registerElementItems(ALUMINIUM);
        registerElementItems(COPPER);
        registerElementItems(GOLD);
        registerElementItems(IRIDIUM);
        registerElementItems(IRON);
        registerElementItems(LEAD);
        registerElementItems(SILVER);
        registerElementItems(THORIUM);
        registerElementItems(TIN);
        registerElementItems(TITANIUM);
        registerElementItems(TUNGSTEN);
        registerElementItems(URANIUM);
        registerElementItems(ZINC);

        registerElementItems(CARBON);
        registerElementItems(SILICON);

        registerElementItems(BRASS);
        registerElementItems(BRONZE);
        registerElementItems(STEEL);
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
        registerItems(group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE, group.GEAR, group.ROD, group.CHUNK);
        //registerBlocks(group.BLOCK_SOLID);
    }

}
