package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderBaseTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChunkloaderRedstone extends BlockChunkloader
{
    public BlockChunkloaderRedstone()
    {
        super("chunkloader_redstone");

        setDefaultState(getDefaultState().with(BlockProperties.ACTIVE,false));

    }


    @Override
    public boolean autoActivate()
    {
        return false;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {

        if (!worldIn.isRemote) {

            ChunkLoaderBaseTileEntity tile = (ChunkLoaderBaseTileEntity) worldIn.getTileEntity(pos);

            if (!tile.getCanLoad()) {
                BlockPos dub = getDuplicate(worldIn,pos);
                if (dub != null) {
                    worldIn.setBlockState(pos,state.with(BlockProperties.ACTIVE,false));
                    return;
                }
            }

            boolean flag = state.get(BlockProperties.ACTIVE);
            if (flag != worldIn.isBlockPowered(pos))
                worldIn.setBlockState(pos,state.cycle(BlockProperties.ACTIVE),2);
        }
    }
}
