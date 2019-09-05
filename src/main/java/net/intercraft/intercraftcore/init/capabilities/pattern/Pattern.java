package net.intercraft.intercraftcore.init.capabilities.pattern;

import net.intercraft.intercraftcore.init.capabilities.pattern.patterns.PatternTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class Pattern implements IPattern
{

    private List<PatternTracker> patterns;

    public static final long timeTicks = 20 * 60 * 30;

    public Pattern()
    {
        patterns = new ArrayList<>();
    }

    @Override
    public void tick(Chunk chunk)
    {

        if (chunk.getWorld().getGameTime() % 150L == 0L) {
            System.out.println(String.format("Chunk [%s %s] is checking ..",chunk.getPos().x,chunk.getPos().z));
            checkFor(PatternTypes.CLAY, chunk);
        }
    }

    //@Override
    private void checkFor(PatternCreator type, Chunk chunk)
    {

        // TODO	look for the pattern, then add it to the list.


        int posX = chunk.getPos().x << 4;
        int posZ = chunk.getPos().z << 4;


        for (int x=0;x<15;x++) {
            for (int z=0;z<15;z++) {

                int posY = chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR,posX+x,posZ+z);
                BlockPos pos = new BlockPos(posX,posY,posZ);

                boolean found = type.checkPattern(chunk.getWorld(),pos);

                if (found) System.out.println(String.format("Found pattern at x=%s y=%s z=%s",pos.getX(),pos.getY(),pos.getZ()));

            }
        }


        /*for (int x=0;x<15;x++) {
            for (int z=0;z<15;z++) {

                PatternTypes.CLAY.checkPattern(chunk.getWorld(), );


                //chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, x, z)
            }
        }*/
    }

    @Override
    public PatternTracker[] getPatterns()
    {
        return patterns.stream().toArray(PatternTracker[]::new);
    }
}
