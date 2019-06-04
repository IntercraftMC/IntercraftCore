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

    public static final Block CABLECASE;

    static {
        CABLECASE = new BlockCableCase();
    }

    /**
     * Register all blocks
     */
    public static void register()
    {
        registerBlock(CABLECASE, IntercraftItemGroups.WIRING);
    }

    /**
     * Register a block
     */
    protected static void registerBlock(Block block, ItemGroup group)
    {
        RegistrationHandler.blocks.add(block);
        Item.Properties properties = new Item.Properties().group(group);
        ItemBlock itemBlock = new ItemBlock(block,properties);
        RegistrationHandler.itemBlocks.add(itemBlock);
    }
}
