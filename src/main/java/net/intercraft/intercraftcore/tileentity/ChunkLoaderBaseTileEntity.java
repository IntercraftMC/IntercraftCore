package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.networking.MessageChunkLoader;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.PacketDistributor;

public class ChunkLoaderBaseTileEntity extends TileEntity implements ITickableTileEntity
{

    public boolean canLoad = true;


    public ChunkLoaderBaseTileEntity(TileEntityType type)
    {
        super(type);
    }



    @Override
    public void tick()
    {

        if (world.isRemote) return;

        if (canLoad) {


            if (!getBlockState().get(BlockProperties.ACTIVE)) return;

            Chunk chunk = world.getChunkAt(pos);

            load(chunk);


        } else {
            if (getBlockState().get(BlockProperties.ACTIVE))
                world.setBlockState(pos, getBlockState().with(BlockProperties.ACTIVE, false));

        }

    }


    public void load(Chunk chunk)
    {
        // Should do the chunkloading here..

    }


    public boolean getCanLoad()
    {
        return canLoad;
    }


    public void setCanLoad(boolean value)
    {
        BlockState stateOld = getBlockState();
        canLoad = value;
        if (getBlockState().get(BlockProperties.ACTIVE) != value) {
            world.setBlockState(pos, getBlockState().with(BlockProperties.ACTIVE, value));
            world.notifyBlockUpdate(pos, stateOld, getBlockState(), 1);
        }

        IntercraftCore.NETWORK.send(PacketDistributor.TRACKING_CHUNK.with(() -> (Chunk) this.getTileEntity().getWorld().getChunk(pos)), new MessageChunkLoader(pos, value));
        markDirty();

        //try {
            //world.setBlockState(pos, getBlockState().with(BlockProperties.ACTIVE, value));
        /*} catch (NullPointerException err) {
            for (StackTraceElement stackTraceElement : err.getStackTrace())
                System.out.println(String.format("Could not find %s",stackTraceElement.getClassName()));
        }*/
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {

        compound.putBoolean("can_load", canLoad);

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);

        canLoad = compound.getBoolean("can_load");
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        super.onDataPacket(net,pkt);
        CompoundNBT compound = pkt.getNbtCompound();

        canLoad = compound.getBoolean("can_load");
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putBoolean("can_load", canLoad);

        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound)
    {

        canLoad = compound.getBoolean("can_load");
        super.handleUpdateTag(compound);
    }

    /*@Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound.putBoolean("can_load",canLoad);
        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound)
    {
        canLoad = compound.getBoolean("can_load");
        super.handleUpdateTag(compound);
    }*/


}
