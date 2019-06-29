package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.BlockCableCase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;

public class IntercraftBlocks
{
    /**
     * Instantiate all blocks
     */

    private static final Block CABLECASE;

    static {
        CABLECASE = new BlockCableCase();
    }

    /**
     * Register all blocks
     */
    public static void register()
    {
        registerBlock(CABLECASE);
    }

    /**
     * Register a block
     */
    protected static void registerBlock(Block block)
    {
        RegistrationHandler.blocks.add(block);
        RegistrationHandler.itemBlocks.add(new ItemBlock(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
        //ItemBlock itemBlock = new ItemBlock(block,new Item.Properties());


    }
}