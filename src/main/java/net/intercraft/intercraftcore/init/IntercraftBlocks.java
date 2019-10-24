package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.*;
import net.intercraft.intercraftcore.block.group.BlockElementGroup;
import net.intercraft.intercraftcore.element.*;
import net.intercraft.intercraftcore.ore.ItemBlockHardOre;
import net.intercraft.intercraftcore.ore.elements.BlockOreCopper;
import net.intercraft.intercraftcore.ore.elements.BlockOreLead;
import net.intercraft.intercraftcore.ore.elements.BlockOreTin;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class IntercraftBlocks
{
    /**
     * Instantiate all blocks
     */

    public static final Block CABLECASE;
    public static final Block TREETAP;
    public static final Block REDSTONEBEACON;


    public static final Block CHUNKLOADER;
    public static final Block CHUNKLOADER_REDSTONE;
    public static final Block CHUNKLOADER_TIMER;

    /**
    * Vanilla feature blocks
    */

    public static final Block GRAVELSUBSTITUTE;
    public static final Block SANDSUBSTITUTE;

    public static final Block VANILLA_STONE_PRESSUREPLATE;
    public static final Block VANILLA_OAK_PRESSUREPLATE;
    public static final Block VANILLA_SPRUCE_PRESSUREPLATE;
    public static final Block VANILLA_BIRCH_PRESSUREPLATE;
    public static final Block VANILLA_JUNGLE_PRESSUREPLATE;
    public static final Block VANILLA_ACACIA_PRESSUREPLATE;
    public static final Block VANILLA_DARKOAK_PRESSUREPLATE;

    /**
    * Block Groups
    * */

    public static final BlockElementGroup ALUMINIUM;
    public static final BlockElementGroup COPPER;
    public static final BlockElementGroup GOLD;
    public static final BlockElementGroup IRIDIUM;
    public static final BlockElementGroup IRON;
    public static final BlockElementGroup LEAD;
    public static final BlockElementGroup SILVER;
    public static final BlockElementGroup THORIUM;
    public static final BlockElementGroup TIN;
    public static final BlockElementGroup TITANIUM;
    public static final BlockElementGroup TUNGSTEN;
    public static final BlockElementGroup URANIUM;
    public static final BlockElementGroup ZINC;

    public static final BlockElementGroup BRASS;
    public static final BlockElementGroup BRONZE;
    public static final BlockElementGroup STEEL;

    /**
    * Ore Blocks
    */

    public static final Block COPPERORE;
    public static final Block TINORE;
    public static final Block LEADORE;

    static {
        CABLECASE = new BlockCableCase();
        TREETAP = new BlockTreeTap();
        REDSTONEBEACON = new BlockRedstoneBeacon();

        GRAVELSUBSTITUTE = new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)).setRegistryName("gravel");
        SANDSUBSTITUTE = new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)).setRegistryName("sand");

        VANILLA_STONE_PRESSUREPLATE = new BlockPressurePlate("stone_pressure_plate", PressurePlateBlock.Sensitivity.MOBS, Block.Properties.create(Material.ROCK).doesNotBlockMovement().hardnessAndResistance(0.5F));
        VANILLA_OAK_PRESSUREPLATE = new BlockPressurePlate("oak_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_SPRUCE_PRESSUREPLATE = new BlockPressurePlate("spruce_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_BIRCH_PRESSUREPLATE = new BlockPressurePlate("birch_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_JUNGLE_PRESSUREPLATE = new BlockPressurePlate("jungle_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_ACACIA_PRESSUREPLATE = new BlockPressurePlate("acacia_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        VANILLA_DARKOAK_PRESSUREPLATE = new BlockPressurePlate("dark_oak_pressure_plate", PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));

        CHUNKLOADER = new BlockChunkloader("chunkloader");
        CHUNKLOADER_REDSTONE = new BlockChunkloaderRedstone();
        CHUNKLOADER_TIMER = new BlockChunkloaderTimer();

        /*ALUMINIUM = new BlockElementGroup(Elements.ALUMINIUM);
        COPPER = new BlockElementGroup(Elements.COPPER);
        GOLD = new BlockElementGroup(Elements.GOLD);
        IRIDIUM = new BlockElementGroup(Elements.IRIDIUM);
        IRON = new BlockElementGroup(Elements.IRON);
        LEAD = new BlockElementGroup(Elements.LEAD);
        SILVER = new BlockElementGroup(Elements.SILVER);
        THORIUM = new BlockElementGroup(Elements.THORIUM);
        TIN = new BlockElementGroup(Elements.TIN);
        TITANIUM = new BlockElementGroup(Elements.TITANIUM);
        TUNGSTEN = new BlockElementGroup(Elements.TUNGSTEN);
        URANIUM = new BlockElementGroup(Elements.URANIUM);
        ZINC = new BlockElementGroup(Elements.ZINC);

        BRASS = new BlockElementGroup(Elements.BRASS);
        BRONZE = new BlockElementGroup(Elements.BRONZE);
        STEEL = new BlockElementGroup(Elements.STEEL);*/

        ALUMINIUM = new BlockElementGroup(new Aluminium());
        COPPER = new BlockElementGroup(new Copper());
        GOLD = new BlockElementGroup(new Gold());
        IRIDIUM = new BlockElementGroup(new Iridium());
        IRON = new BlockElementGroup(new Iron());
        LEAD = new BlockElementGroup(new Lead());
        SILVER = new BlockElementGroup(new Silver());
        THORIUM = new BlockElementGroup(new Thorium());
        TIN = new BlockElementGroup(new Tin());
        TITANIUM = new BlockElementGroup(new Titanium());
        TUNGSTEN = new BlockElementGroup(new Tungsten());
        URANIUM = new BlockElementGroup(new Uranium());
        ZINC = new BlockElementGroup(new Zinc());

        BRASS = new BlockElementGroup(new Brass());
        BRONZE = new BlockElementGroup(new Bronze());
        STEEL = new BlockElementGroup(new Steel());

        COPPERORE = new BlockOreCopper();
        TINORE = new BlockOreTin();
        LEADORE = new BlockOreLead();

    }

    /**
     * Register all blocks
     */

    public static void register()
    {
        registerBlocks(IntercraftItemGroups.WIRING,true,CABLECASE);
        registerBlocks(ItemGroup.TOOLS,true,TREETAP);
        registerBlocks(IntercraftItemGroups.MACHINE,true,CHUNKLOADER,CHUNKLOADER_REDSTONE,CHUNKLOADER_TIMER);
        registerBlocks(ItemGroup.REDSTONE,true,REDSTONEBEACON);
        registerBlocks(null,false, GRAVELSUBSTITUTE,SANDSUBSTITUTE);

        registerReplaceBlockItem(Blocks.STONE_PRESSURE_PLATE, VANILLA_STONE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.OAK_PRESSURE_PLATE, VANILLA_OAK_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.SPRUCE_PRESSURE_PLATE, VANILLA_SPRUCE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.BIRCH_PRESSURE_PLATE, VANILLA_BIRCH_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.JUNGLE_PRESSURE_PLATE, VANILLA_JUNGLE_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.ACACIA_PRESSURE_PLATE, VANILLA_ACACIA_PRESSUREPLATE);
        registerReplaceBlockItem(Blocks.DARK_OAK_PRESSURE_PLATE, VANILLA_DARKOAK_PRESSUREPLATE);

        //registerOre(COPPERORE);

        registerElementBlocks(ALUMINIUM);
        registerElementBlocks(COPPER);
        registerElementBlocks(GOLD);
        registerElementBlocks(IRIDIUM);
        registerElementBlocks(IRON);
        registerElementBlocks(LEAD);
        registerElementBlocks(SILVER);
        registerElementBlocks(THORIUM);
        registerElementBlocks(TIN);
        registerElementBlocks(TITANIUM);
        registerElementBlocks(TUNGSTEN);
        registerElementBlocks(URANIUM);
        registerElementBlocks(ZINC);

        registerElementBlocks(BRASS);
        registerElementBlocks(BRONZE);
        registerElementBlocks(STEEL);

        registerOreBlocks(COPPERORE,TINORE,LEADORE);
    }

    /**
     * Register a block(s)
     */
    protected static void registerBlocks(ItemGroup group, final boolean item,Block...blocks)
    {

        for (Block block : blocks) {
            if (block != null) {
                RegistrationHandler.blocks.add(block);
                if (item)
                    RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
            }
        }
    }

    protected static void registerReplaceBlockItem(Block blockR, Block block)
    {

        RegistrationHandler.blocks.add(block);

        Item blockItem = new BlockItem(block, new Item.Properties().group(blockR.asItem().getGroup())).setRegistryName(new ResourceLocation(blockR.getRegistryName().getNamespace(),block.getRegistryName().getPath()));

        RegistrationHandler.itemBlocks.add(blockItem);
    }

    protected static void registerOreBlocks(Block...blocks)
    {

        for (Block block : blocks) {
            RegistrationHandler.blocks.add(block);
            RegistrationHandler.itemBlocks.add(new ItemBlockHardOre(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
        }
    }

    protected static void registerElementBlocks(BlockElementGroup group)
    {
        registerBlocks(IntercraftItemGroups.RESOURCES,true,group.BLOCK,group.FRAME);
    }

}