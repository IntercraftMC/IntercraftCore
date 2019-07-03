package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.BlockCableCase;
import net.intercraft.intercraftcore.ore.BlockOreCopper;
import net.intercraft.intercraftcore.ore.BlockOreTin;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;

public class IntercraftBlocks
{
    /**
     * Instantiate all block
     */

    public static final Block CABLECASE;
    public static final Block COPPERORE;
    public static final Block TINORE;

    static {
        CABLECASE = new BlockCableCase();
        COPPERORE = new BlockOreCopper();
        TINORE = new BlockOreTin();
    }

    /**
     * Register all block
     */
    public static void register()
    {
        registerBlock(CABLECASE, IntercraftItemGroups.WIRING);
        registerBlock(COPPERORE,IntercraftItemGroups.RESOURCES);
        registerBlock(TINORE,IntercraftItemGroups.RESOURCES);
    }

    /**
     * Register a block
     */
    protected static void registerBlock(Block block, ItemGroup group)
    {
        RegistrationHandler.blocks.add(block);
        RegistrationHandler.itemBlocks.add(new ItemBlock(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
        //ItemBlock itemBlock = new ItemBlock(block,new Item.Properties());


    }
}