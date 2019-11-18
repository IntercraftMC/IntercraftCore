package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.element.alloys.*;
import net.intercraft.intercraftcore.element.metals.*;
import net.intercraft.intercraftcore.element.nonmetals.*;
import net.intercraft.intercraftcore.item.*;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.item.group.ItemUraniumGroup;
import net.intercraft.intercraftcore.item.masks.ItemGlasses;
import net.intercraft.intercraftcore.item.masks.ItemMask;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class IntercraftItems
{
    /**
     * Miscellaneous Items
     */

    public static final Item TEST;
    public static final Item AMBER_RAW;
    public static final Item AMBER_CUT;

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
    
    public static final Item RUBBER_RESIN_BUCKET_OAK;
    public static final Item RUBBER_RESIN_BUCKET_SPRUCE;
    public static final Item RUBBER_RESIN_BUCKET_BIRCH;
    public static final Item RUBBER_RESIN_BUCKET_JUNGLE;
    public static final Item RUBBER_RESIN_BUCKET_ACACIA;
    public static final Item RUBBER_RESIN_BUCKET_DARK_OAK;

    public static final Item RUBBER_RESIN_BUCKET;

    //public static final Item OILBUCKET;

    /**
     * Coils
     */

    public static final Item COPPER_COIL;
    public static final Item INSULATED_COPPER_COIL;


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
        TEST      = new ItemTest((new Item.Properties()).group(ItemGroup.MISC));
        AMBER_RAW = new ItemGeneric("amber_raw",new Item.Properties().group(IntercraftItemGroups.RESOURCES));
        AMBER_CUT = new ItemGeneric("amber_cut",new Item.Properties().group(IntercraftItemGroups.RESOURCES));

        DEVILMASK  = new ItemMask(   "devil_mask",  new ResourceLocation(Reference.MODID,"textures/masks/devil_mask.png"));
        SUNGLASSES = new ItemGlasses("sun_glasses", new ResourceLocation(Reference.MODID,"textures/masks/sun_glasses.png"));


        BUCKET_OAK      = new ItemBucketWood("oak");
        BUCKET_SPRUCE   = new ItemBucketWood("spruce");
        BUCKET_BIRCH    = new ItemBucketWood("birch");
        BUCKET_JUNGLE   = new ItemBucketWood("jungle");
        BUCKET_ACACIA   = new ItemBucketWood("acacia");
        BUCKET_DARK_OAK = new ItemBucketWood("dark_oak");

        WATER_BUCKET_OAK      = new ItemBucketWood(Fluids.WATER,"oak",BUCKET_OAK);
        WATER_BUCKET_SPRUCE   = new ItemBucketWood(Fluids.WATER,"spruce",BUCKET_SPRUCE);
        WATER_BUCKET_BIRCH    = new ItemBucketWood(Fluids.WATER,"birch",BUCKET_BIRCH);
        WATER_BUCKET_JUNGLE   = new ItemBucketWood(Fluids.WATER,"jungle",BUCKET_JUNGLE);
        WATER_BUCKET_ACACIA   = new ItemBucketWood(Fluids.WATER,"acacia",BUCKET_ACACIA);
        WATER_BUCKET_DARK_OAK = new ItemBucketWood(Fluids.WATER,"dark_oak",BUCKET_DARK_OAK);

        RESIN_BUCKET_OAK      = new ItemBucketNonFluid("resin","oak");
        RESIN_BUCKET_SPRUCE   = new ItemBucketNonFluid("resin","spruce");
        RESIN_BUCKET_BIRCH    = new ItemBucketNonFluid("resin","birch");
        RESIN_BUCKET_JUNGLE   = new ItemBucketNonFluid("resin","jungle");
        RESIN_BUCKET_ACACIA   = new ItemBucketNonFluid("resin","acacia");
        RESIN_BUCKET_DARK_OAK = new ItemBucketNonFluid("resin","dark_oak");

        RESIN_BUCKET = new ItemBucketNonFluid("resin");
        
        RUBBER_RESIN_BUCKET_OAK      = new ItemBucketNonFluid("rubber_resin","oak");
        RUBBER_RESIN_BUCKET_SPRUCE   = new ItemBucketNonFluid("rubber_resin","spruce");
        RUBBER_RESIN_BUCKET_BIRCH    = new ItemBucketNonFluid("rubber_resin","birch");
        RUBBER_RESIN_BUCKET_JUNGLE   = new ItemBucketNonFluid("rubber_resin","jungle");
        RUBBER_RESIN_BUCKET_ACACIA   = new ItemBucketNonFluid("rubber_resin","acacia");
        RUBBER_RESIN_BUCKET_DARK_OAK = new ItemBucketNonFluid("rubber_resin","dark_oak");

        RUBBER_RESIN_BUCKET = new ItemBucketNonFluid("rubber_resin");


        COPPER_COIL           = new ItemWireCoil(ElementDictionary.COPPER.getSymbol(),0.6, 1024,60,               ElementDictionary.COPPER.getColorPrimary());
        INSULATED_COPPER_COIL = new ItemWireCoil(ElementDictionary.COPPER.getSymbol(),0.45,1024,60,true, ElementDictionary.COPPER.getColorPrimary());



        ALUMINIUM = new ItemElementGroup(new Aluminium());
        COPPER    = new ItemElementGroup(new Copper());
        GOLD      = new ItemElementGroup(new Gold());
        IRIDIUM   = new ItemElementGroup(new Iridium());
        IRON      = new ItemElementGroup(new Iron());
        LEAD      = new ItemElementGroup(new Lead());
        SILVER    = new ItemElementGroup(new Silver());
        THORIUM   = new ItemElementGroup(new Thorium());
        TIN       = new ItemElementGroup(new Tin());
        TITANIUM  = new ItemElementGroup(new Titanium());
        TUNGSTEN  = new ItemElementGroup(new Tungsten());
        URANIUM   = new ItemUraniumGroup(new Uranium());
        ZINC      = new ItemElementGroup(new Zinc());

        CARBON    = new ItemElementGroup(new Carbon());
        SILICON   = new ItemElementGroup(new Silicon());

        BRASS     = new ItemElementGroup(new Brass());
        BRONZE    = new ItemElementGroup(new Bronze());
        STEEL     = new ItemElementGroup(new Steel());
    }

    /**
     * Register all items
     */
    public static void register()
    {
        registerItems(TEST,AMBER_RAW,AMBER_CUT);

        registerItems(DEVILMASK,SUNGLASSES);


        registerItems(BUCKET_OAK,      BUCKET_SPRUCE,      BUCKET_BIRCH,      BUCKET_JUNGLE,      BUCKET_ACACIA,      BUCKET_DARK_OAK);
        registerItems(WATER_BUCKET_OAK,WATER_BUCKET_SPRUCE,WATER_BUCKET_BIRCH,WATER_BUCKET_JUNGLE,WATER_BUCKET_ACACIA,WATER_BUCKET_DARK_OAK);
        registerItems(RESIN_BUCKET_OAK,RESIN_BUCKET_SPRUCE,RESIN_BUCKET_BIRCH,RESIN_BUCKET_JUNGLE,RESIN_BUCKET_ACACIA,RESIN_BUCKET_DARK_OAK);
        registerItem(RESIN_BUCKET);
        registerItems(RUBBER_RESIN_BUCKET_OAK,RUBBER_RESIN_BUCKET_SPRUCE,RUBBER_RESIN_BUCKET_BIRCH,RUBBER_RESIN_BUCKET_JUNGLE,RUBBER_RESIN_BUCKET_ACACIA,RUBBER_RESIN_BUCKET_DARK_OAK);
        registerItems(RUBBER_RESIN_BUCKET);

        registerItems(COPPER_COIL, INSULATED_COPPER_COIL);


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
     *
     * @param item Item to be registered.
     */
    protected static void registerItem(@Nonnull Item item)
    {
        RegistrationHandler.items.add(item);
    }

    /**
     * Register an item
     *
     * @param items Item(s) to be registered.
     */
    protected static void registerItems(@Nonnull Item... items)
    {
        for (Item item : items) {
            if (item != null) {
                RegistrationHandler.items.add(item);
            }
        }
    }


    /**
     * Register group of blocks
     *
     * @param group ItemElementGroup of items to be registered.
     */

    protected static void registerElementItems(@Nonnull ItemElementGroup group)
    {
        registerItems(group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE, group.GEAR, group.ROD, group.CHUNK);
        //registerBlocks(group.BLOCK_SOLID);
    }

}
