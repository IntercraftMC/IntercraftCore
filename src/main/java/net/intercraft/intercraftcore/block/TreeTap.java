package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.BucketType;
import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

public class TreeTap extends Block
{

    //                                                                    double x1,       double y1,       double z1,        double x2,        double y2,         double z2
    protected static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(10.0D, 1.0D, 5.0D, 16.0D, 10.0D, 11.0D);
    protected static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(0.0D, 1.0D, 5.0D, 6.0D, 10.0D, 11.0D);
    protected static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(5.0D, 1.0D, 10.0D, 11.0D, 10.0D, 16.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(5.0D, 1.0D, 0.0D, 11.0D, 10.0D, 6.0D);


    public TreeTap()
    {
        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3.0f, 3.0f));//.doesNotBlockMovement()

        setRegistryName("treetap");
        setDefaultState(getDefaultState().with(BlockProperties.VOLUME, 0).with(FACING, Direction.NORTH).with(BlockProperties.BUCKET, BucketType.NONE).with(BlockProperties.FLUIDTYPE, FluidType.NONE));
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        builder.add(BlockProperties.VOLUME);
        builder.add(BlockProperties.FLUIDTYPE);
        builder.add(BlockProperties.BUCKET);

    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }


    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (worldIn.isRemote()) {
            if (player.isSneaking()) {


                return true;
            } else return false;
        } else
            return true;
    }

    /*@Override
    public boolean isValidPosition(BlockState state, IWorldReader worldReader, BlockPos pos)
    {
        return func_220055_a(worldReader, pos.down(), Direction.UP);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos, BlockPos pos1)
    {
        return direction == Direction.DOWN && !this.isValidPosition(state, world, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, direction, state1, world, pos, pos1);
    }*/

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext)
    {
        switch (state.get(FACING)) {
            case WEST:
                return SHAPE_WEST;

            case EAST:
                return SHAPE_EAST;

            case SOUTH:
                return SHAPE_SOUTH;

            default: case NORTH:
                return SHAPE_NORTH;
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }


    /*@Override
    public net.minecraftforge.common.ToolType getHarvestTool(BlockState state)
    {

    }*/

}
