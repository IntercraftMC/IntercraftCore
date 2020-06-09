package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.*;
import net.intercraft.intercraftcore.block.cablecase.BlockCableCase;
import net.intercraft.intercraftcore.block.group.BlockElementGroup;
import net.intercraft.intercraftcore.block.group.BlockLithiumGroup;
import net.intercraft.intercraftcore.block.group.BlockUraniumGroup;
import net.intercraft.intercraftcore.element.alloys.Brass;
import net.intercraft.intercraftcore.element.alloys.Bronze;
import net.intercraft.intercraftcore.element.alloys.Steel;
import net.intercraft.intercraftcore.element.metals.*;
import net.intercraft.intercraftcore.item.ItemBlockHardOre;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IntercraftBlocks
{
    /**
     * Instantiate all blocks
     */

    public static final BlockCableCase CABLECASE;
    public static final BlockTreeTap TREETAP;
    public static final Block DRAIN;
    public static final BlockRedstoneBeacon REDSTONE_BEACON;
    //public static final BlockButtonNumber[] NUMBER_BUTTONS_STONE;
    public static final BlockButtonNumber[] NUMBER_BUTTONS_STONE;
    public static final BlockButtonNumber[] NUMBER_BUTTONS_OAK, NUMBER_BUTTONS_SPRUCE, NUMBER_BUTTONS_BIRCH, NUMBER_BUTTONS_JUNGLE, NUMBER_BUTTONS_ACACIA, NUMBER_BUTTONS_DARK_OAK;


    public static final BlockChunkloader CHUNKLOADER;
    public static final BlockChunkloaderRedstone CHUNKLOADER_REDSTONE;
    public static final BlockChunkloaderTimer CHUNKLOADER_TIMER;

    /**
    * Fluids
    */


    /**
     * Vanilla feature blocks
     */

    public static final Block GRAVELSUBSTITUTE, SANDSUBSTITUTE;
    public static final Block VANILLA_STONE_PRESSUREPLATE, VANILLA_OAK_PRESSUREPLATE, VANILLA_SPRUCE_PRESSUREPLATE, VANILLA_BIRCH_PRESSUREPLATE, VANILLA_JUNGLE_PRESSUREPLATE, VANILLA_ACACIA_PRESSUREPLATE, VANILLA_DARKOAK_PRESSUREPLATE;
    public static final Block VANILLA_BUBBLE_COLUMN;


    /**
     * Block Groups
     */

    public static final BlockElementGroup
            ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC,
            BRASS, BRONZE, STEEL;


    static {

        CABLECASE       = new BlockCableCase();
        TREETAP         = new BlockTreeTap();
        REDSTONE_BEACON = new BlockRedstoneBeacon();
        DRAIN           = new BlockDrain();

        /*NUMBER_BUTTONS_STONE = new BlockMetaGroup<BlockButtonNumber>((short) 10,"stone_number_button_%s","stone_number_button",short.class,false) {
            @Override
            protected Constructor<BlockButtonNumber> createConstructor() throws NoSuchMethodException
            {
                return BlockButtonNumber.class.getConstructor(String.class, String.class, byte.class, boolean.class);
            }
        };*/
        NUMBER_BUTTONS_STONE    = new BlockButtonNumber[10];
        NUMBER_BUTTONS_OAK      = new BlockButtonNumber[10];
        NUMBER_BUTTONS_SPRUCE   = new BlockButtonNumber[10];
        NUMBER_BUTTONS_BIRCH    = new BlockButtonNumber[10];
        NUMBER_BUTTONS_JUNGLE   = new BlockButtonNumber[10];
        NUMBER_BUTTONS_ACACIA   = new BlockButtonNumber[10];
        NUMBER_BUTTONS_DARK_OAK = new BlockButtonNumber[10];
        for (byte i=0;i<10;i++) {
            NUMBER_BUTTONS_STONE[i]    = new BlockButtonNumber("stone_number_button_"+i,"stone_number_button", i, false);
            NUMBER_BUTTONS_OAK[i]      = new BlockButtonNumber("oak_number_button_"+i,"oak_number_button", i, true);
            NUMBER_BUTTONS_SPRUCE[i]   = new BlockButtonNumber("spruce_number_button_"+i,"spruce_number_button", i, true);
            NUMBER_BUTTONS_BIRCH[i]    = new BlockButtonNumber("birch_number_button_"+i,"birch_number_button", i, true);
            NUMBER_BUTTONS_JUNGLE[i]   = new BlockButtonNumber("jungle_number_button_"+i,"jungle_number_button", i, true);
            NUMBER_BUTTONS_ACACIA[i]   = new BlockButtonNumber("acacia_number_button_"+i,"acacia_number_button", i, true);
            NUMBER_BUTTONS_DARK_OAK[i] = new BlockButtonNumber("dark_oak_number_button_"+i,"dark_oak_number_button", i, true);
        }


        GRAVELSUBSTITUTE = new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)).setRegistryName("gravel");
        SANDSUBSTITUTE = new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)).setRegistryName("sand");

        VANILLA_STONE_PRESSUREPLATE   = new BlockPressurePlate("stone_pressure_plate", PressurePlateBlock.Sensitivity.MOBS, Block.Properties.create(Material.ROCK).doesNotBlockMovement().hardnessAndResistance(0.5F));
        VANILLA_OAK_PRESSUREPLATE     = new BlockPressurePlate("oak_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_SPRUCE_PRESSUREPLATE  = new BlockPressurePlate("spruce_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_BIRCH_PRESSUREPLATE   = new BlockPressurePlate("birch_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_JUNGLE_PRESSUREPLATE  = new BlockPressurePlate("jungle_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_ACACIA_PRESSUREPLATE  = new BlockPressurePlate("acacia_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_DARKOAK_PRESSUREPLATE = new BlockPressurePlate("dark_oak_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));

        VANILLA_BUBBLE_COLUMN = new BlockBubbleColumn(new ResourceLocation("minecraft","bubble_column").toString(),Block.Properties.create(Material.BUBBLE_COLUMN).doesNotBlockMovement().noDrops());

        CHUNKLOADER = new BlockChunkloader("chunkloader");
        CHUNKLOADER_REDSTONE = new BlockChunkloaderRedstone();
        CHUNKLOADER_TIMER = new BlockChunkloaderTimer();


        ALUMINIUM = new BlockElementGroup(new Aluminium());
        COPPER    = new BlockElementGroup(new Copper());
        GOLD      = new BlockElementGroup(new Gold());
        IRIDIUM   = new BlockElementGroup(new Iridium());
        IRON      = new BlockElementGroup(new Iron());
        LEAD      = new BlockElementGroup(new Lead());
        LITHIUM   = new BlockLithiumGroup(new Lithium());
        NICKEL    = new BlockElementGroup(new Nickel());
        SILVER    = new BlockElementGroup(new Silver());
        THORIUM   = new BlockElementGroup(new Thorium());
        TIN       = new BlockElementGroup(new Tin());
        TITANIUM  = new BlockElementGroup(new Titanium());
        TUNGSTEN  = new BlockElementGroup(new Tungsten());
        URANIUM   = new BlockUraniumGroup(new Uranium());
        ZINC      = new BlockElementGroup(new Zinc());

        BRASS  = new BlockElementGroup(new Brass());
        BRONZE = new BlockElementGroup(new Bronze());
        STEEL  = new BlockElementGroup(new Steel());

    }

    /**
     * Register all blocks
     */

    public static void register()
    {
        registerBlocks(IntercraftItemGroups.WIRING,CABLECASE);
        registerBlocks(ItemGroup.TOOLS,TREETAP,DRAIN);
        registerBlocks(IntercraftItemGroups.MACHINE,
                CHUNKLOADER,CHUNKLOADER_REDSTONE,CHUNKLOADER_TIMER
        );
        registerBlocks(ItemGroup.REDSTONE, REDSTONE_BEACON);
        registerMetaBlocks(ItemGroup.REDSTONE, 1, NUMBER_BUTTONS_STONE);
        registerMetaBlocks(ItemGroup.REDSTONE, 1, NUMBER_BUTTONS_OAK, NUMBER_BUTTONS_SPRUCE, NUMBER_BUTTONS_BIRCH, NUMBER_BUTTONS_JUNGLE, NUMBER_BUTTONS_ACACIA, NUMBER_BUTTONS_DARK_OAK);

        registerBlocks(GRAVELSUBSTITUTE,SANDSUBSTITUTE);

        registerReplaceBlockItem(Blocks.STONE_PRESSURE_PLATE,    VANILLA_STONE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.OAK_PRESSURE_PLATE,      VANILLA_OAK_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.SPRUCE_PRESSURE_PLATE,   VANILLA_SPRUCE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.BIRCH_PRESSURE_PLATE,    VANILLA_BIRCH_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.JUNGLE_PRESSURE_PLATE,   VANILLA_JUNGLE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.ACACIA_PRESSURE_PLATE,   VANILLA_ACACIA_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.DARK_OAK_PRESSURE_PLATE, VANILLA_DARKOAK_PRESSUREPLATE);

        registerBlocks(VANILLA_BUBBLE_COLUMN);

        registerElementsBlocks(ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        registerElementsBlocks(BRASS, BRONZE, STEEL);
    }

    /**
     * Register block(s)
     *
     * @param group  ItemGroup the BlockItem will reside.
     * @param blocks block(s) to be registered.
     */
    protected static void registerBlocks(@Nullable ItemGroup group, @Nonnull Block...blocks)
    {
        for (Block block : blocks) {
            if (block != null) {
                RegistrationHandler.blocks.add(block);
                if (group != null)
                    RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
            }
        }
    }

    /**
     * Register block(s) without ItemBlock
     *
     * @param blocks block(s) to be registered.
     */

    protected static void registerBlocks(@Nonnull Block...blocks)
    {
        registerBlocks(null,blocks);
    }

    /**
     * Register array group(s) of blocks
     *
     * @param group  ItemGroup BlockItem will reside.
     * @param meta   The meta item to be item grouped.
     * @param blocks Array group(s) to be registered.
     */

    protected static void registerMetaBlocks(@Nullable ItemGroup group, int meta, @Nonnull Block[]...blocks)
    {
        for (Block[] blockGroup : blocks) {
            for (short i=0;i<blockGroup.length;i++) {
                if (i == meta)
                    registerBlocks(group,blockGroup[i]);
                else
                    registerBlocks(ItemGroup.SEARCH,blockGroup[i]);
            }
        }
    }

    protected static void registerMetaBlocks(@Nullable ItemGroup group, @Nonnull Block[]...blocks)
    {
        registerMetaBlocks(group,0,blocks);
    }

    /**
     * Register element group(s) of blocks
     *
     * @param groups BlockElementGroup(s) of blocks to be registered.
     */

    protected static void registerElementsBlocks(@Nonnull BlockElementGroup...groups)
    {
        for (BlockElementGroup group : groups) {
            registerBlocks(IntercraftItemGroups.RESOURCES,group.BLOCK,group.FRAME);
            registerOreBlocks(group.ORE);
        }
    }

    /**
     * Register-replace vanilla ItemBlock placement
     *
     * @param blockR Block who's ItemBlock is being overwritten.
     * @param block  Replaced block.
     */

    protected static void registerReplaceBlockItem(@Nonnull Block blockR, @Nonnull Block block)
    {
        RegistrationHandler.blocks.add(block);

        Item blockItem = new BlockItem(block, new Item.Properties().group(blockR.asItem().getGroup())).setRegistryName(new ResourceLocation(blockR.getRegistryName().getNamespace(),block.getRegistryName().getPath()));
        RegistrationHandler.itemBlocks.add(blockItem);
    }

    /**
     * Register a fluid //TODO Actually have it register fluids.
     *
     * @param fluids fluid(s) to be registered.
     */

    protected static void registerFluidBlocks(@Nonnull Block...fluids)
    {
        for (Block fluid : fluids)
            if (fluid != null) {
                RegistrationHandler.fluidBlocks.add(fluid);
            }
    }

    protected static void registerOreBlocks(@Nonnull Block...blocks)
    {
        for (Block block : blocks)
            if (block != null) {
                RegistrationHandler.blocks.add(block);
                RegistrationHandler.itemBlocks.add(new ItemBlockHardOre(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
            }
    }
}