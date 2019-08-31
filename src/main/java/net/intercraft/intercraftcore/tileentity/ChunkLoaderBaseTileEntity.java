package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.Ticket;

import javax.annotation.Nonnull;

public class ChunkLoaderBaseTileEntity extends TileEntity implements ITickableTileEntity
{

    public ChunkLoaderBaseTileEntity()
    {
        super(IntercraftTileEntities.CHUNKLOADER);
    }


    public void setTicket(@Nonnull Ticket ticket)
    {

    }


    @Override
    public void tick()
    {


        if (world.isRemote) return;

        BlockState state = getBlockState();
        if (!state.get(BlockProperties.ACTIVE)) return;

        Chunk chunk = world.getChunkAt(pos);

        // Should do the chunkloading here..


    }


}
