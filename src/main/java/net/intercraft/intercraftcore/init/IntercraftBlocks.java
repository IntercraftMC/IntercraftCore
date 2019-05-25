package net.intercraft.intercraftcore.init;

import net.minecraft.block.Block;

public class IntercraftBlocks
{
    /**
     * Instantiate all blocks
     */
    static {

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
