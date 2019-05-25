package net.intercraft.intercraftcore.init;

import net.minecraft.block.Block;

/**
 * All blocks are created and grouped here
 */
public class IntercraftBlocks {


    /**
     * Instantiate all blocks
     */
    static
    {

    }

    /**
     * Register all items
     */
    public static void register()
    {
    }

    /**
     * Register a block
     */
    protected static void registerBlock(Block block)
    {
        RegistrationHandler.blocks.add(block);
    }
}
