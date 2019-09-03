package net.intercraft.intercraftcore.init.capabilities.pattern.patterns;

import net.intercraft.intercraftcore.init.capabilities.pattern.PatternCreator;
import net.minecraft.block.Blocks;

public class Clay extends PatternCreator
{
    public Clay()
    {
        super(2,2,2,
                Blocks.DIRT,Blocks.DIRT,
                Blocks.DIRT,Blocks.GRAVEL,

                Blocks.WATER,Blocks.WATER,
                Blocks.WATER,Blocks.WATER
        );
    }
}
