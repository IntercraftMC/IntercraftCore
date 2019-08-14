package net.intercraft.intercraftcore.tileentity;


import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class TreeTapTileEntity extends TileEntity implements ITickableTileEntity
{

    private int volume = 0;
    private boolean canFill = false;
    private FluidType fluidType = FluidType.NONE;

    public static final int maxVolume = 1000;

    public TreeTapTileEntity()
    {
        super(IntercraftTileEntities.TREETAP);
    }


    @Override
    public void tick()
    {
        if (fluidType == FluidType.NONE) return;
        if (canFill) {
            if (volume < maxVolume) {


                if (getWorld().getGameTime() % fluidType.getViscosity() == 0) {
                    setVolume(volume + 1);

                    //world.addParticle(ParticleTypes.DRIPPING_WATER,pos.getX(),pos.getY()+0.5,pos.getZ(),0,-1,0);

                    //addParticle(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
                    //playSound(@Nullable PlayerEntity player, BlockPos pos, SoundEvent soundIn, SoundCategory category, float volume, float pitch)
                }

            }
        }
    }


    /*@Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        CompoundNBT compound = pkt.getNbtCompound();
        volume = compound.getInt("volume");
        canFill = compound.getBoolean("can_fill");
        fluidType = FluidType.valueOf(compound.getString("fluid_type").toUpperCase());
        world.notifyBlockUpdate(pos,world.getBlockState(pos),world.getBlockState(pos),2);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putInt("volume", volume);
        compound.putBoolean("can_fill",canFill);
        compound.putString("fluid_type",fluidType.getName());

        return new SUpdateTileEntityPacket(pos, 1, compound);
    }*/

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putInt("volume", volume);
        compound.putBoolean("can_fill",canFill);
        compound.putString("fluid_type",fluidType.getName());
        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound)
    {
        volume = compound.getInt("volume");
        canFill = compound.getBoolean("can_fill");
        fluidType = FluidType.valueOf(compound.getString("fluid_type").toUpperCase());
        super.handleUpdateTag(compound);
    }



    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.putInt("volume", volume);
        compound.putBoolean("can_fill",canFill);
        compound.putString("fluid_type",fluidType.getName());

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
