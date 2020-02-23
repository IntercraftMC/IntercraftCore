package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.enumProperties.BlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.LIT;

public class BlockRedstoneBeacon extends TorchBlock
{
    public BlockRedstoneBeacon()
    {
        super(Block.Properties.create(Material.REDSTONE_LIGHT).hardnessAndResistance(0).doesNotBlockMovement());

        setRegistryName("redstone_beacon");

        setDefaultState(getDefaultState().with(BlockProperties.RS_OUTPUT_STRENGTH,15).with(LIT, true));
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(BlockProperties.RS_OUTPUT_STRENGTH);
        builder.add(LIT);
    }


    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side)
    {
        return true;
    }

    @Override
    public boolean canProvidePower(BlockState state)
    {
        return true;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side)
    {
        return side == Direction.DOWN ? this.getWeakPower(blockState, blockAccess, pos, side) : 0;
    }

    private boolean shouldBeOff(World worldIn, BlockPos pos)
    {
        return worldIn.isSidePowered(pos.down(), Direction.DOWN);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
        boolean s = this.shouldBeOff(worldIn,pos);

        if (state.get(LIT)) {
            if (s) {
                worldIn.setBlockState(pos,state.with(LIT,false));
            }
        } else if (!s) {
            worldIn.setBlockState(pos,state.with(LIT,true));
        }

        super.tick(state, worldIn, pos, random);

    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side)
    {
        return blockState.get(LIT) && Direction.UP != side ? blockState.get(BlockProperties.RS_OUTPUT_STRENGTH) : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT)) {
            double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
            double d1 = (double)pos.getY() + 0.7D + (rand.nextDouble() - 0.5D) * 0.2D;
            double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
            worldIn.addParticle(RedstoneParticleData.REDSTONE_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

}
