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

    public static final Block CABLECASE;
    public static final Block TREETAP;
    public static final Block REDSTONEBEACON;
    public static final Block AUTOCRAFTINGTABLE;
    public static final Block DRAIN;


    public static final Block CHUNKLOADER;
    public static final Block CHUNKLOADER_REDSTONE;
    public static final Block CHUNKLOADER_TIMER;


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
            ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC,
            BRASS, BRONZE, STEEL;


    static {

        CABLECASE = new BlockCableCase();
        TREETAP = new BlockTreeTap();
        REDSTONEBEACON = new BlockRedstoneBeacon();
        AUTOCRAFTINGTABLE = new BlockAutoCraftingTable();
        DRAIN = new BlockDrain();


        GRAVELSUBSTITUTE = new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)).setRegistryName("gravel");
        SANDSUBSTITUTE = new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)).setRegistryName("sand");

        VANILLA_STONE_PRESSUREPLATE = new BlockPressurePlate("stone_pressure_plate", PressurePlateBlock.Sensitivity.MOBS, Block.Properties.create(Material.ROCK).doesNotBlockMovement().hardnessAndResistance(0.5F));
        VANILLA_OAK_PRESSUREPLATE = new BlockPressurePlate("oak_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_SPRUCE_PRESSUREPLATE = new BlockPressurePlate("spruce_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_BIRCH_PRESSUREPLATE = new BlockPressurePlate("birch_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_JUNGLE_PRESSUREPLATE = new BlockPressurePlate("jungle_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_ACACIA_PRESSUREPLATE = new BlockPressurePlate("acacia_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
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
                CHUNKLOADER,CHUNKLOADER_REDSTONE,CHUNKLOADER_TIMER,
                AUTOCRAFTINGTABLE
        );
        registerBlocks(ItemGroup.REDSTONE,REDSTONEBEACON);
        registerBlocks(GRAVELSUBSTITUTE,SANDSUBSTITUTE);

        registerReplaceBlockItem(Blocks.STONE_PRESSURE_PLATE,    VANILLA_STONE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.OAK_PRESSURE_PLATE,      VANILLA_OAK_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.SPRUCE_PRESSURE_PLATE,   VANILLA_SPRUCE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.BIRCH_PRESSURE_PLATE,    VANILLA_BIRCH_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.JUNGLE_PRESSURE_PLATE,   VANILLA_JUNGLE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.ACACIA_PRESSURE_PLATE,   VANILLA_ACACIA_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.DARK_OAK_PRESSURE_PLATE, VANILLA_DARKOAK_PRESSUREPLATE);

        //registerBlocks(VANILLA_BUBBLE_COLUMN);

        registerElementsBlocks(ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        registerElementsBlocks(BRASS, BRONZE, STEEL);
    }

    /**
     * Register a block(s)
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
     * Register a block(s) with no ItemBlock.
     *
     * @param blocks block(s) to be registered.
     */

    protected static void registerBlocks(@Nonnull Block...blocks)
    {
        registerBlocks(null,blocks);
    }

    /**
     * Register a block with a desired block item.
     *
     * @param block block to be registered.
     * @param blockItem block item to be registered with.
     */
    /*protected static void registerBlockWithBlockItem(@Nonnull Block block, @Nonnull BlockItem blockItem)
    {
        if (block != null)
            RegistrationHandler.blocks.add(block);
        if (blockItem != null)
            RegistrationHandler.itemBlocks.add(blockItem);

    }*/


    /**
     * Register group of blocks
     *
     * @param group BlockElementGroup of blocks to be registered.
     */

    protected static void registerElementBlocks(@Nonnull BlockElementGroup group)
    {
        registerBlocks(IntercraftItemGroups.RESOURCES,group.BLOCK,group.FRAME);
        registerOreBlocks(group.ORE);
    }

    /**
     * Register group of blocks
     *
     * @param groups BlockElementGroup(s) of blocks to be registered.
     */

    protected static void registerElementsBlocks(@Nonnull BlockElementGroup...groups)
    {
        for (BlockElementGroup group : groups)
            registerElementBlocks(group);
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
            if (fluid != null)
                RegistrationHandler.fluidBlocks.add(fluid);
    }

    protected static void registerOreBlocks(@Nonnull Block...blocks)
    {

        for (Block block : blocks) {

            if (block != null) {
                RegistrationHandler.blocks.add(block);
                RegistrationHandler.itemBlocks.add(new ItemBlockHardOre(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
            }
        }
    }
}