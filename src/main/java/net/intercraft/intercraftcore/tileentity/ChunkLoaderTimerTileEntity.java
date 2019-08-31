package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;

public class ChunkLoaderTimerTileEntity extends TileEntity implements ITickableTileEntity
{


    private long duration;

    private int seconds = 5;
    private int minutes = 0;
    private int hours = 0;
    private int days = 0;

    public ChunkLoaderTimerTileEntity()
    {
        super(IntercraftTileEntities.CHUNKLOADER_TIMER);
        duration = getResetTime();
    }




    @Override
    public void tick()
    {


        if (getWorld().isRemote) return;

        BlockState state = getBlockState();
        if (!state.get(BlockProperties.ACTIVE)) return;

        if (duration > 0) {


            // Should do the chunkloading here..

            Chunk chunk = world.getChunkAt(pos);

            setDuration(duration-1);
        } else {
            world.setBlockState(pos,getBlockState().with(BlockProperties.ACTIVE,false));
            setDuration(getResetTime());
        }
    }


    private int mult(int value, int multi)
    {
        if (value <= 1)
            return 1;
        else
            return value*multi;

    }

    public long getResetTime()
    {
        return mult(days,24) * mult(hours,60) * mult(minutes,60) * mult(minutes,60) * mult(seconds,20);
    }


    public long getDuration()
    {
        return duration;
    }

    public int getSeconds()
    {
        return seconds;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public int getHours()
    {
        return hours;
    }

    public int getDays()
    {
        return days;
    }


    public void setDuration(long value)
    {
        duration = value;
        markDirty();
    }

    public void setSeconds(int value)
    {
        seconds = value;
        markDirty();
    }

    public void setMinutes(int value)
    {
        minutes = value;
        markDirty();
    }

    public void setHours(int value)
    {
        hours = value;
        markDirty();
    }

    public void setDays(int value)
    {
        days = value;
        markDirty();
    }



    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.putLong("duration",duration);
        compound.putInt("sec",seconds);
        compound.putInt("min",minutes);
        compound.putInt("hour",hours);
        compound.putInt("day",days);

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        duration = compound.getLong("duration");
        seconds = compound.getInt("sec");
        minutes = compound.getInt("min");
        hours = compound.getInt("hour");
        days = compound.getInt("day");
    }
}
