package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.element.alloys.*;
import net.intercraft.intercraftcore.element.metals.*;
import net.intercraft.intercraftcore.element.nonmetals.*;
import net.intercraft.intercraftcore.item.*;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.item.group.ItemLithiumGroup;
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
    public static final Item AMBER_RAW, AMBER_CUT;

    public static final ItemSingleStackContainer LEAD_BOX, STEEL_BOX;
    public static final ItemSingleStackGlassContainer LARGE_GLASS_JAR;
    public static final ItemSingleStackGlassContainer LARGE_RED_GLASS_JAR, LARGE_GREEN_GLASS_JAR, LARGE_BLUE_GLASS_JAR;

    /**
     * Face masks
     */

    public static final ItemMask DEVIL_MASK;
    public static final ItemGlasses SUNGLASSES;

    /**
    * Fluid Buckets
    */

    public static final Item
     /* Empty */        BUCKET_OAK, BUCKET_SPRUCE, BUCKET_BIRCH,BUCKET_JUNGLE, BUCKET_ACACIA, BUCKET_DARK_OAK,
     /* Water */        WATER_BUCKET_OAK, WATER_BUCKET_SPRUCE, WATER_BUCKET_BIRCH, WATER_BUCKET_JUNGLE, WATER_BUCKET_ACACIA, WATER_BUCKET_DARK_OAK,
     /* Resin */        RESIN_BUCKET_OAK, RESIN_BUCKET_SPRUCE, RESIN_BUCKET_BIRCH, RESIN_BUCKET_JUNGLE, RESIN_BUCKET_ACACIA, RESIN_BUCKET_DARK_OAK, RESIN_BUCKET,
     /* Rubber Resin */ RUBBER_RESIN_BUCKET_OAK, RUBBER_RESIN_BUCKET_SPRUCE, RUBBER_RESIN_BUCKET_BIRCH, RUBBER_RESIN_BUCKET_JUNGLE, RUBBER_RESIN_BUCKET_ACACIA, RUBBER_RESIN_BUCKET_DARK_OAK, RUBBER_RESIN_BUCKET;

    /**
     * Coils
     */

    public static final ItemWireCoil COPPER_COIL, INSULATED_COPPER_COIL;


    /**
     * Item Groups
     */

    public static final ItemElementGroup
     /* Metals */   ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC,
     /* Minerals */ CARBON, SILICON,
     /* Alloys */   BRASS, BRONZE, STEEL;



    /**
     * Instantiate all items
     */

    static {
        TEST      = new ItemTest((new Item.Properties()).group(ItemGroup.MISC));
        AMBER_RAW = new ItemGeneric("amber_raw",new Item.Properties().group(IntercraftItemGroups.RESOURCES));
        AMBER_CUT = new ItemGeneric("amber_cut",new Item.Properties().group(IntercraftItemGroups.RESOURCES));

        LEAD_BOX              = new ItemSingleStackContainer(new Item.Properties(),"lead_box",0.8f,ElementDictionary.LEAD.getColorPrimary());
        STEEL_BOX             = new ItemSingleStackContainer(new Item.Properties(),"steel_box",0.15f,ElementDictionary.STEEL.getColorPrimary());
        LARGE_GLASS_JAR       = new ItemSingleStackGlassContainer("large_glass_jar",0.01f);
        LARGE_RED_GLASS_JAR   = new ItemSingleStackGlassContainer("large_red_glass_jar",0.01f, 0xfe0f2e);
        LARGE_GREEN_GLASS_JAR = new ItemSingleStackGlassContainer("large_green_glass_jar",0.01f, 0x65A565);
        LARGE_BLUE_GLASS_JAR  = new ItemSingleStackGlassContainer("large_blue_glass_jar",0.01f, 0x7f93ff);

        DEVIL_MASK = new ItemMask(   "devil_mask",  new ResourceLocation(Reference.MODID,"textures/masks/devil_mask.png"));
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

        RESIN_BUCKET_OAK      = new ItemBucketNonFluid("resin","oak", FluidType.RESIN.getTint());
        RESIN_BUCKET_SPRUCE   = new ItemBucketNonFluid("resin","spruce", FluidType.RESIN.getTint());
        RESIN_BUCKET_BIRCH    = new ItemBucketNonFluid("resin","birch", FluidType.RESIN.getTint());
        RESIN_BUCKET_JUNGLE   = new ItemBucketNonFluid("resin","jungle", FluidType.RESIN.getTint());
        RESIN_BUCKET_ACACIA   = new ItemBucketNonFluid("resin","acacia", FluidType.RESIN.getTint());
        RESIN_BUCKET_DARK_OAK = new ItemBucketNonFluid("resin","dark_oak", FluidType.RESIN.getTint());

        RUBBER_RESIN_BUCKET_OAK      = new ItemBucketNonFluid("rubber_resin","oak", FluidType.RUBBER_RESIN.getTint());
        RUBBER_RESIN_BUCKET_SPRUCE   = new ItemBucketNonFluid("rubber_resin","spruce", FluidType.RUBBER_RESIN.getTint());
        RUBBER_RESIN_BUCKET_BIRCH    = new ItemBucketNonFluid("rubber_resin","birch", FluidType.RUBBER_RESIN.getTint());
        RUBBER_RESIN_BUCKET_JUNGLE   = new ItemBucketNonFluid("rubber_resin","jungle", FluidType.RUBBER_RESIN.getTint());
        RUBBER_RESIN_BUCKET_ACACIA   = new ItemBucketNonFluid("rubber_resin","acacia", FluidType.RUBBER_RESIN.getTint());
        RUBBER_RESIN_BUCKET_DARK_OAK = new ItemBucketNonFluid("rubber_resin","dark_oak", FluidType.RUBBER_RESIN.getTint());

        RESIN_BUCKET = new ItemBucketNonFluid("resin", FluidType.RESIN.getTint());
        RUBBER_RESIN_BUCKET = new ItemBucketNonFluid("rubber_resin", FluidType.RUBBER_RESIN.getTint());


        COPPER_COIL           = new ItemWireCoil(ElementDictionary.COPPER.getSymbol(),0.6, 1024,60,               ElementDictionary.COPPER.getColorPrimary());
        INSULATED_COPPER_COIL = new ItemWireCoil(ElementDictionary.COPPER.getSymbol(),0.45,1024,60,true, ElementDictionary.COPPER.getColorPrimary());



        ALUMINIUM = new ItemElementGroup(new Aluminium());
        COPPER    = new ItemElementGroup(new Copper());
        GOLD      = new ItemElementGroup(new Gold());
        IRIDIUM   = new ItemElementGroup(new Iridium());
        IRON      = new ItemElementGroup(new Iron());
        LEAD      = new ItemElementGroup(new Lead());
        LITHIUM   = new ItemLithiumGroup(new Lithium());
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

        registerItems(LEAD_BOX,STEEL_BOX,LARGE_GLASS_JAR,LARGE_RED_GLASS_JAR,LARGE_GREEN_GLASS_JAR,LARGE_BLUE_GLASS_JAR);

        registerItems(DEVIL_MASK,SUNGLASSES);


        registerItems(BUCKET_OAK, BUCKET_SPRUCE, BUCKET_BIRCH, BUCKET_JUNGLE, BUCKET_ACACIA, BUCKET_DARK_OAK);
        registerItems(WATER_BUCKET_OAK, WATER_BUCKET_SPRUCE, WATER_BUCKET_BIRCH, WATER_BUCKET_JUNGLE, WATER_BUCKET_ACACIA, WATER_BUCKET_DARK_OAK);
        registerItems(RESIN_BUCKET_OAK, RESIN_BUCKET_SPRUCE, RESIN_BUCKET_BIRCH, RESIN_BUCKET_JUNGLE, RESIN_BUCKET_ACACIA, RESIN_BUCKET_DARK_OAK);
        registerItem(RESIN_BUCKET);
        registerItems(RUBBER_RESIN_BUCKET_OAK, RUBBER_RESIN_BUCKET_SPRUCE, RUBBER_RESIN_BUCKET_BIRCH, RUBBER_RESIN_BUCKET_JUNGLE, RUBBER_RESIN_BUCKET_ACACIA, RUBBER_RESIN_BUCKET_DARK_OAK);
        registerItems(RUBBER_RESIN_BUCKET);

        registerItems(COPPER_COIL, INSULATED_COPPER_COIL);

        registerElementsItems(ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        registerElementsItems(CARBON, SILICON);
        registerElementsItems(BRASS, BRONZE, STEEL);
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
     * Register group of items
     *
     * @param group ItemElementGroup of items to be registered.
     */

    protected static void registerElementItems(@Nonnull ItemElementGroup group)
    {
        registerItems(group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE, group.GEAR, group.ROD, group.CHUNK);
    }

    /**
     * Register group of items
     *
     * @param groups ItemElementGroup(s) of items to be registered.
     */

    protected static void registerElementsItems(@Nonnull ItemElementGroup...groups)
    {
        for (ItemElementGroup group : groups)
            registerElementItems(group);
    }
}