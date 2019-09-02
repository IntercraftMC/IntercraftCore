package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;

public class ChunkLoaderBaseTileEntity extends TileEntity implements ITickableTileEntity
{

    private boolean canLoad = true;


    public ChunkLoaderBaseTileEntity()
    {
        super(IntercraftTileEntities.CHUNKLOADER);
    }



    @Override
    public void tick()
    {

        if (canLoad) {

            if (world.isRemote) return;

            BlockState state = getBlockState();
            if (!state.get(BlockProperties.ACTIVE)) return;

            Chunk chunk = world.getChunkAt(pos);

            // Should do the chunkloading here..



        } else {
            if (getBlockState().get(BlockProperties.ACTIVE))
                world.setBlockState(pos, getBlockState().with(BlockProperties.ACTIVE, false));

        }

    }


    public boolean getCanLoad()
    {
        return canLoad;
    }


    public void setCanLoad(boolean value)
    {
        canLoad = value;
        markDirty();
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {

        compound.putBoolean("can_load",canLoad);

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);

        canLoad = compound.getBoolean("can_load");
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
