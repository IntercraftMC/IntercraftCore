package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ChunkLoaderTimerTileEntity extends ChunkLoaderBaseTileEntity implements INameable
{
    private long duration;

    private int // Default countdown values.
            seconds = 30,
            minutes = 4,
            hours   = 0,
            days    = 0;

    public ChunkLoaderTimerTileEntity()
    {
        super(IntercraftTileEntities.CHUNKLOADER_TIMER);
        duration = getResetTime();
    }


    @Override
    public void tick()
    {

        super.tick();

        if (canLoad) {

            if (world.isRemote) return;

            BlockState state = getBlockState();
            if (!state.get(BlockProperties.ACTIVE)) return;

            if (duration > 0) {

                setDuration(duration-1);
            } else {
                world.setBlockState(pos,getBlockState().with(BlockProperties.ACTIVE,false));
                //setCanLoad(false);
                setDuration(getResetTime());
            }

        } else {
            if (getBlockState().get(BlockProperties.ACTIVE)) {
                world.setBlockState(pos, getBlockState().with(BlockProperties.ACTIVE, false));
                //setCanLoad(false);
            }
        }
    }


    private int m(int value, int multi)
    {
        return value <= 1 ? 1 : value*multi;
    }

    private long getResetTime()
    {
        return m(days,24) * m(hours,60) * m(minutes,60) * m(seconds,60) * 20;
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

        //compound.putBoolean("canLoad",canLoad);
        compound.putLong("duration",duration);
        /*compound.putInt("sec",seconds);
        compound.putInt("min",minutes);
        compound.putInt("hour",hours);
        compound.putInt("day",days);*/

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);

        //canLoad = compound.getBoolean("canLoad");
        duration = compound.getLong("duration");
        /*seconds = compound.getInt("sec");
        minutes = compound.getInt("min");
        hours = compound.getInt("hour");
        days = compound.getInt("day");*/
    }

    @Override
    public ITextComponent getName()
    {
        return new TranslationTextComponent(Reference.MODID+".container.chunkloader_timer");
    }

    /*@Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        super.onDataPacket(net,pkt);
        CompoundNBT compound = pkt.getNbtCompound();

        duration = compound.getLong("duration");
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putLong("duration",duration);

        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound)
    {

        duration = compound.getLong("duration");
        super.handleUpdateTag(compound);
    }*/





    /*@Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        //compound.putBoolean("canLoad",canLoad);
        compound.putLong("duration",duration);
        compound.putInt("sec",seconds);
        compound.putInt("min",minutes);
        compound.putInt("hour",hours);
        compound.putInt("day",days);
        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound)
    {
        //canLoad = compound.getBoolean("canLoad");
        duration = compound.getLong("duration");
        seconds = compound.getInt("sec");
        minutes = compound.getInt("min");
        hours = compound.getInt("hour");
        days = compound.getInt("day");
        super.handleUpdateTag(compound);
    }


    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT compound = new CompoundNBT();
        //Write your data into the nbtTag

        compound.putBoolean("canLoad",canLoad);
        compound.putLong("duration",duration);
        compound.putInt("sec",seconds);
        compound.putInt("min",minutes);
        compound.putInt("hour",hours);
        compound.putInt("day",days);

        return new SUpdateTileEntityPacket(getPos(), 1, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        CompoundNBT compound = pkt.getNbtCompound();

        canLoad = compound.getBoolean("canLoad");
        duration = compound.getLong("duration");
        seconds = compound.getInt("sec");
        minutes = compound.getInt("min");
        hours = compound.getInt("hour");
        days = compound.getInt("day");

        //Handle your Data
    }*/


}
