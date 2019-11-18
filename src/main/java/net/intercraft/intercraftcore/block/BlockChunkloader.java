package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderBaseTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import java.util.ArrayList;
import java.util.List;

public class BlockChunkloader extends Block
{

    public static final VoxelShape shape = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);


    public BlockChunkloader(final String name)
    {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL));

        setRegistryName(name);

        setDefaultState(getDefaultState().with(BlockProperties.ACTIVE,true));
    }


    public boolean autoActivate()
    {
        return true;
    }

    public static BlockPos[] scanForLoaders(World worldIn, BlockPos pos)
    {
        List<BlockPos> positions = new ArrayList<>();

        for (BlockPos tePos : worldIn.getChunk(pos).getTileEntitiesPos()) {
            TileEntity tile = worldIn.getTileEntity(tePos);
            if (tile instanceof ChunkLoaderBaseTileEntity)
                positions.add(tePos);
        }

        return positions.toArray(new BlockPos[positions.size()]);

    }

    public static BlockPos getDuplicate(World worldIn, BlockPos pos)
    {
        for (BlockPos tePos : scanForLoaders(worldIn,pos)) {
            if (pos.getX() == tePos.getX() && pos.getY() == tePos.getY() && pos.getZ() == tePos.getZ()) continue;
            TileEntity tile = worldIn.getTileEntity(tePos);
            if (tile == null) continue;
            return tePos;
        }
        return null;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {

        if (!worldIn.isRemote) {


            BlockPos dub = getDuplicate(worldIn,pos);

            if (dub != null) {
                placer.sendMessage(new TranslationTextComponent("chunkloading."+Reference.MODID+".dublicate",dub.getX(),dub.getY(),dub.getZ()));
                //worldIn.setBlockState(pos,state.with(BlockProperties.ACTIVE,false));

                TileEntity tile = worldIn.getTileEntity(pos);

                if (tile instanceof ChunkLoaderBaseTileEntity) {
                    ((ChunkLoaderBaseTileEntity) tile).setCanLoad(false);
                    worldIn.setBlockState(pos,state.with(BlockProperties.ACTIVE,false));
                }


            }
        }

        super.onBlockPlacedBy(worldIn,pos,state,placer,stack);

    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!worldIn.isRemote) {

            BlockPos[] blockPos = scanForLoaders(worldIn,pos);


            if (blockPos.length-1 < 2 && blockPos.length-1 > 0) {
                System.out.println("There is one left in this chunk!");

                TileEntity tile = worldIn.getTileEntity(blockPos[0]);

                for (int i=0;i<blockPos.length;i++) {
                    if (!(pos.getX() == blockPos[i].getX() && pos.getY() == blockPos[i].getY() && pos.getZ() == blockPos[i].getZ())) {
                        tile = worldIn.getTileEntity(blockPos[i]);
                        break;
                    }
                }

                if (tile instanceof ChunkLoaderBaseTileEntity) {
                    ((ChunkLoaderBaseTileEntity)tile).setCanLoad(true);
                    if (autoActivate())
                        worldIn.setBlockState(pos, state.with(BlockProperties.ACTIVE, true));
                    worldIn.notifyBlockUpdate(pos,state,state,1);


                    //System.out.println("It's a Base type.");
                }



            }

        }

        super.onBlockHarvested(worldIn,pos,state,player);

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
        return new ChunkLoaderBaseTileEntity(IntercraftTileEntities.CHUNKLOADER);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext)
    {
        return shape;
    }
}
