package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.intercraft.intercraftcore.networking.messages.MessageTreeTap;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.PacketDistributor;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class TreeTapTileEntity extends TileEntity implements ITickableTileEntity
{

    //private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

    public int volume = 0;
    public boolean canFill = false;
    public FluidType fluidType = FluidType.NONE;

    public static final int maxVolume = 1000;

    public TreeTapTileEntity()
    {
        super(IntercraftTileEntities.TREETAP);
    }


    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }

    @Override
    public void tick()
    {

        if (getWorld().isRemote) return;

        if (fluidType == FluidType.NONE) return;
        if (canFill) {
            if (volume < maxVolume) {


                if (getWorld().getGameTime() % (fluidType.getViscosity()) == 0) {
                    //setVolume(volume + 1);
                    add(1);

                    //world.addParticle(ParticleTypes.DRIPPING_WATER,pos.getX(),pos.getY()+0.5,pos.getZ(),0,-1,0);

                    //addParticle(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
                    //playSound(@Nullable PlayerEntity player, BlockPos pos, SoundEvent soundIn, SoundCategory category, float volume, float pitch)
                }

            }
        }
    }


    public void add(int v)
    {

        /*Runnable run = () -> setVolume(volume + v);
        executor.schedule(run,118,TimeUnit.MILLISECONDS);*/
        setVolume(volume + v);

        double x = this.pos.getX()+0.5,y = this.pos.getY()+0.43,z = this.pos.getZ()+0.5;

        switch (this.getBlockState().get(HORIZONTAL_FACING)) {

            case WEST:
                x += 0.26;
                break;

            case EAST:
                x -= 0.26;
                break;

            case SOUTH:
                z -= 0.26;
                break;

            default: case NORTH:
                z += 0.26;

        }

        IParticleData particle = (IParticleData) fluidType.getParticle();


        if (Minecraft.getInstance().world != null)
            Minecraft.getInstance().world.addParticle(particle,x,y,z,0,-1,0);
    }


    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        super.onDataPacket(net,pkt);
        CompoundNBT compound = pkt.getNbtCompound();
        volume = compound.getInt("volume");
        //canFill = compound.getBoolean("can_fill");
        fluidType = FluidType.valueOf(compound.getString("fluid_type").toUpperCase());
        //world.notifyBlockUpdate(pos,world.getBlockState(pos),world.getBlockState(pos),2);
    }

    /*@Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putInt("volume", volume);
        compound.putBoolean("can_fill",canFill);
        compound.putString("fluid_type",fluidType.getSymbol());

        return new SUpdateTileEntityPacket(pos, 1, compound);
    }*/

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putInt("volume", volume);
        //compound.putBoolean("can_fill",canFill);
        compound.putString("fluid_type",fluidType.getName());
        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound)
    {
        volume = compound.getInt("volume");
        //canFill = compound.getBoolean("can_fill");
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

    public void setVolume(int volume)
    {
        this.volume = volume;
        markDirty();
        IntercraftCore.NETWORK.send(PacketDistributor.TRACKING_CHUNK.with(() -> (Chunk) this.getTileEntity().getWorld().getChunk(this.pos)),new MessageTreeTap(this.getPos(),volume,fluidType));

    }

    public void setCanFill(boolean bool)
    {
        canFill = bool;
        markDirty();
    }

    public void setFluidType(FluidType fluidType)
    {
        this.fluidType = fluidType;
        markDirty();
        IntercraftCore.NETWORK.send(PacketDistributor.TRACKING_CHUNK.with(() -> (Chunk) this.getTileEntity().getWorld().getChunk(this.pos)),new MessageTreeTap(this.getPos(),volume,fluidType));
    }
}
