package net.intercraft.intercraftcore.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.Half;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.*;

public class BlockDrain extends Block implements IWaterLoggable
{
    private static final VoxelShape
            shapeUp   = Block.makeCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            shapeDown = Block.makeCuboidShape(0.0D, 0.0D,  0.0D, 16.0D, 1.0D,  16.0D);

    public BlockDrain()
    {
        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(5.0f).sound(SoundType.METAL));

        setDefaultState(getDefaultState().with(HALF, Half.BOTTOM).with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED,false));

        setRegistryName("drain");
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState state = this.getDefaultState();
        Direction direction = context.getFace();
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());

        //boolean isSneaking = !(((Boolean)context.getPlayer().isSneaking()) == null) && context.getPlayer().isSneaking();
        boolean isSneaking = context.getPlayer().isSneaking();

        if (direction.getAxis().isHorizontal()) {
            state = state.with(HORIZONTAL_FACING, isSneaking ? direction : direction.rotateY()).with(HALF, context.getHitVec().y - (double)context.getPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM);
        } else {
            state = state.with(HORIZONTAL_FACING, isSneaking ? context.getPlacementHorizontalFacing().getOpposite().rotateY() : context.getPlacementHorizontalFacing().getOpposite()).with(HALF, direction == Direction.UP ? Half.BOTTOM : Half.TOP);
        }

        return state.with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(WATERLOGGED);
        builder.add(HORIZONTAL_FACING);
        builder.add(HALF);
        //builder.add(DRAG);
    }

    public IFluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        switch (state.get(HALF)) {
            case TOP: return shapeUp;
            default:  return shapeDown;
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
