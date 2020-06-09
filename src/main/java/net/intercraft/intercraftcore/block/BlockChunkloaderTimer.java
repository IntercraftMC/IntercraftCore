package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.UtilBlocks;
import net.intercraft.intercraftcore.api.enumProperties.BlockProperties;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderTimerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockChunkloaderTimer extends BlockChunkloader
{
    public BlockChunkloaderTimer()
    {
        super("chunkloader_timer");

        setDefaultState(getDefaultState().with(BlockProperties.ACTIVE,false));

    }

    @Override
    public boolean autoActivate()
    {
        return false;
    }


    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {

        /*if (worldIn.isRemote)
        player.sendMessage(new StringTextComponent(((ChunkLoaderTimerTileEntity) worldIn.getTileEntity(pos)).getCanLoad()+""));*/


        if (!worldIn.isRemote) {

            // Open Gui.
            ChunkLoaderTimerTileEntity tile = UtilBlocks.getTileEntityThrowable(ChunkLoaderTimerTileEntity.class,worldIn,state,pos);

            player.openContainer(tile);



            if (player.isCreative()) {

                ItemStack stack = player.getHeldItem(handIn);

                if (stack.getItem() == Items.BONE) {
                    tile.setDuration(tile.getDuration() + 100);
                } else if (stack.getItem() == Items.STICK) {
                    //worldIn.setBlockState(pos,state.with(BlockProperties.ACTIVE,!state.get(BlockProperties.ACTIVE)));
                    tile.setCanLoad(!tile.canLoad);
                }
            }


               return true;
        } else return true;
    }


    /*@Nullable
    @Override
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof ChunkLoaderTimerTileEntity) {
            ITextComponent itextcomponent = ((INameable)tileentity).getDisplayName();
            return new ContainerScreenChunkLoaderTimer((p_220147_2_, p_220147_3_, p_220147_4_) -> {
                return new ContainerChunkLoaderTimer(p_220147_2_, p_220147_3_, IWorldPosCallable.of(worldIn, pos));
            }, itextcomponent);
        } else {
            return null;
        }

    }*/

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new ChunkLoaderTimerTileEntity();
    }
}
