package net.intercraft.intercraftcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;


public class BlockPressurePlate extends PressurePlateBlock implements IWaterLoggable
{
    public BlockPressurePlate(String name ,PressurePlateBlock.Sensitivity sensitivity, Block.Properties properties)
    {
        //super(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.create(Material.ROCK).doesNotBlockMovement().hardnessAndResistance(0.5F));
        super(sensitivity,properties);

        //setRegistryName(new ResourceLocation("minecraft","stone_pressure_plate"));
        setRegistryName(name);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {

        BlockPos pos = context.getPos();

        IFluidState fluidState = context.getWorld().getFluidState(pos);

        if (fluidState.getFluid() == Fluids.WATER)
            return super.getStateForPlacement(context).with(WATERLOGGED, true);
        else
            return super.getStateForPlacement(context).with(WATERLOGGED, false);

    }

    public IFluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn)
    {
        return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn)
    {
        return IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }


    @Override
    public String getTranslationKey()
    {
        return "block.minecraft." + getRegistryName().getPath();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(WATERLOGGED);
    }
}
