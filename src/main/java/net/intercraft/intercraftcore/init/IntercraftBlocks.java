package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.*;
import net.intercraft.intercraftcore.ore.ItemBlockHardOre;
import net.intercraft.intercraftcore.ore.elements.BlockOreCopper;
import net.intercraft.intercraftcore.ore.elements.BlockOreLead;
import net.intercraft.intercraftcore.ore.elements.BlockOreTin;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SoundType;
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
    public static final Block VANILLASTONEPRESSUREPLATE;

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
        VANILLASTONEPRESSUREPLATE = new BlockPressurePlate();

        CHUNKLOADER = new BlockChunkloader("chunkloader");
        CHUNKLOADER_REDSTONE = new BlockChunkloaderRedstone();
        CHUNKLOADER_TIMER = new BlockChunkloaderTimer();

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
        registerReplaceBlockItem(Blocks.STONE_PRESSURE_PLATE, VANILLASTONEPRESSUREPLATE);

        //registerOre(COPPERORE);

        registerOreBlocks(COPPERORE,TINORE,LEADORE);
    }

    /**
     * Register a block(s)
     */
    protected static void registerBlocks(ItemGroup group, final boolean item,Block...blocks)
    {

        for (Block block : blocks) {
            RegistrationHandler.blocks.add(block);
            if (item)
                RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
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
}