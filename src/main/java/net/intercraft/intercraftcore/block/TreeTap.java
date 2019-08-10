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
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

public class TreeTap extends Block
{
    public TreeTap()
    {
        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3.0f, 3.0f).doesNotBlockMovement());

        setRegistryName("treetap");
        setDefaultState(getDefaultState().with(BlockProperties.VOLUME, 0).with(FACING, Direction.NORTH).with(BlockProperties.BUCKET, BucketType.NONE).with(BlockProperties.FLUIDTYPE, FluidType.WATER));
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

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


    /*@Override
    public net.minecraftforge.common.ToolType getHarvestTool(BlockState state)
    {

    }*/

}
