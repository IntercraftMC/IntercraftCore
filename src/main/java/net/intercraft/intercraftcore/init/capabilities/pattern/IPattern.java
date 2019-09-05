package net.intercraft.intercraftcore.init.capabilities.pattern;

import net.minecraft.world.chunk.Chunk;

public interface IPattern
{
    //void checkFor(PatternTypes type);

    void tick(Chunk chunk);

    PatternTracker[] getPatterns();

}
