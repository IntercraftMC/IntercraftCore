package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderTimerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
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
            ChunkLoaderTimerTileEntity tile = state.getBlock().hasTileEntity(state) ? (ChunkLoaderTimerTileEntity) worldIn.getTileEntity(pos) : null;
            if (tile == null) return false;


            ItemStack stack = player.getHeldItem(handIn);

            if (stack.getItem() == Items.BONE) {
                tile.setDuration(tile.getDuration()+100);
            } else if (stack.getItem() == Items.STICK) {
                //worldIn.setBlockState(pos,state.with(BlockProperties.ACTIVE,!state.get(BlockProperties.ACTIVE)));
                tile.setCanLoad(!tile.canLoad);
            } else if (stack.isEmpty()) {
                player.sendMessage(new StringTextComponent(String.format("Duration is %s ticks",tile.getDuration())));
            }

               return false;
        } else return true;
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new ChunkLoaderTimerTileEntity();
    }
}
