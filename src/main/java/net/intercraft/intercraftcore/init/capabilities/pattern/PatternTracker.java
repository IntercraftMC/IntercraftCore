package net.intercraft.intercraftcore.init.capabilities.pattern;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class PatternTracker
{

    private final BlockPos pos;
    private final Direction direction;


    public PatternTracker(BlockPos pos, @Nullable Direction direction)
    {
        this.pos = pos;
        this.direction = direction != null ? direction : Direction.NORTH;
    }


    public BlockPos getPosition()
    {
        return pos;
    }

    public Direction getDirection()
    {
        return direction;
    }
}
