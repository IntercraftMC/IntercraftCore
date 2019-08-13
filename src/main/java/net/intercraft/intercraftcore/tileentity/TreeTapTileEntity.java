package net.intercraft.intercraftcore.tileentity;


import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TreeTapTileEntity extends TileEntity implements ITickableTileEntity
{

    private int volume = 0;
    private boolean canFill = false;
    private FluidType fluidType = FluidType.NONE;

    public TreeTapTileEntity()
    {
        super(IntercraftTileEntities.TREETAP);
    }


    @Override
    public void tick()
    {
        if (canFill) {
            if (volume < 1000) {
                volume++;
                markDirty();
            }
        }
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.putInt("volume", volume);
        compound.putBoolean("can_fill",canFill);
        compound.putString("fluid_type",fluidType.toString());

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        volume = compound.getInt("volume");
        canFill = compound.getBoolean("can_fill");
        fluidType = FluidType.valueOf(compound.getString("fluid_type").toUpperCase());

    }


    public int getVolume()
    {
        return volume;
    }

    public boolean getCanFill()
    {
        return canFill;
    }

    public FluidType getFluidType()
    {
        return this.fluidType;
    }

    public void setVolume(int value)
    {
        volume = value;
        markDirty();
    }

    public void setCanFill(boolean bool)
    {
        canFill = bool;
        markDirty();
    }

    public void setFluidType(FluidType type)
    {
        fluidType = type;
        markDirty();
    }
}
