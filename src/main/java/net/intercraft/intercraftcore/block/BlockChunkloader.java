package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderBaseTileEntity;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderTimerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockChunkloader extends Block
{



    public BlockChunkloader(final String name)
    {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL));

        setRegistryName(name);

        setDefaultState(getDefaultState().with(BlockProperties.ACTIVE,true));
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {

        if (!worldIn.isRemote) {

            for (BlockPos tePos : worldIn.getChunk(pos).getTileEntitiesPos()) {

                if (pos.getX() == tePos.getX() && pos.getY() == tePos.getY() && pos.getZ() == tePos.getZ()) continue;

                TileEntity tile = worldIn.getTileEntity(tePos);
                if (tile == null) continue;
                if (tile instanceof ChunkLoaderBaseTileEntity || tile instanceof ChunkLoaderTimerTileEntity) {
                    worldIn.setBlockState(pos, state.with(BlockProperties.ACTIVE, false));
                    placer.sendMessage(new TranslationTextComponent("chunkloading.intercraftcore.dublicate", tePos.getX(), tePos.getY(), tePos.getZ()));
                    return;
                }
            }
        }

    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(BlockProperties.ACTIVE);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new ChunkLoaderBaseTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext)
    {
        return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    }


}
