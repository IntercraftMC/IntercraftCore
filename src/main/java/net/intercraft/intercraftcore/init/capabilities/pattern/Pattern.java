package net.intercraft.intercraftcore.init.capabilities.pattern;

import net.intercraft.intercraftcore.init.capabilities.pattern.patterns.PatternTypes;
import net.intercraft.intercraftcore.init.capabilities.pattern.patterns.Patterns;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class Pattern implements IPattern
{

    private List<PatternCreator> patterns;
    private Chunk chunk;


    public static final long timeTicks = 20 * 60 * 30;

    public Pattern(Chunk chunk)
    {
        patterns = new ArrayList<>();
        this.chunk = chunk;
    }


    @Override
    public void checkFor(PatternCreator patternCreator)
    {

        for (PatternTypes pattern : PatternTypes.values()) {

        }



        /*for (int x=0;x<15;x++) {
            for (int z=0;z<15;z++) {

                Patterns.CLAY.checkPattern(chunk.getWorld(), );


                //chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, x, z)
            }
        }*/
    }

    @Override
    public PatternCreator[] getPatterns()
    {
        return patterns.toArray(new PatternCreator[patterns.size()]);
    }
}
