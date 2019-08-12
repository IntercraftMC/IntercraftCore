package net.intercraft.intercraftcore.tileentity;


import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TreeTapTileEntity extends TileEntity implements ITickableTileEntity
{

    private int volume = 0;
    private boolean canFill = false;

    public TreeTapTileEntity()
    {
        super(IntercraftTileEntities.TREETAP);
    }


    @Override
    public void tick()
    {
        if (canFill) {
            volume++;
            markDirty();
        }
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.putInt("volume", volume);
        compound.putBoolean("can_fill",canFill);

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        volume = compound.getInt("volume");
        canFill = compound.getBoolean("can_fill");
    }


    public int getVolume()
    {
        return volume;
    }

    public boolean getCanFill()
    {
        return canFill;
    }

    public void setVolume(int value)
    {
        volume = value;
    }

    public void setCanFill(boolean bool)
    {
        canFill = bool;
    }
}
