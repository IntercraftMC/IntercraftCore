package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class BlockBubbleColumn extends BubbleColumnBlock
{
    private static final ResourceLocation transparentID = new ResourceLocation(Reference.MODID,"bubble_column_transparent_blocks");

    public BlockBubbleColumn(String name, Block.Properties properties)
    {
        super(properties);
        setRegistryName(name);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return canTransfer(worldIn.getBlockState(pos.down())) || block == this || block == Blocks.SOUL_SAND || block == Blocks.MAGMA_BLOCK;

    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        //placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn, pos.down()));
        BlockPos posUp = nextWaterPosUp(worldIn,pos.up()), posDown = nextWaterPosDown(worldIn,pos.down());
        //if (posUp != null && posDown != null)
            placeBubbleColumn(worldIn, posUp, getDrag(worldIn, posDown));
    }


    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
        //placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn, pos));
        BlockPos posUp = nextWaterPosUp(worldIn,pos.up());
        //if (posUp != null)
            placeBubbleColumn(worldIn, posUp, getDrag(worldIn, pos));
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            return Blocks.WATER.getDefaultState();
        } else {
            if (facing == Direction.DOWN) {
                worldIn.setBlockState(currentPos, getDefaultState().with(DRAG, getDrag(worldIn, nextWaterPosDown(worldIn,facingPos))), 2);
            } else if (facing == Direction.UP && facingState.getBlock() != this && canHoldBubbleColumn(worldIn, nextWaterPosUp(worldIn,facingPos))) {
                worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, this.tickRate(worldIn));
            }

            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    /*public static void placeBubbleColumn(IWorld world, BlockPos pos, boolean drag) {
        if (canHoldBubbleColumn(world, pos)) {
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.WATER)
                world.setBlockState(pos,IntercraftBlocks.VANILLA_BUBBLE_COLUMN.getDefaultState().with(DRAG,drag),2);
            else
                world.setBlockState(pos, state.with(DRAG,drag), 2);
        }

    }*/

    public static void placeBubbleColumn(IWorld world, BlockPos pos, boolean drag)
    {
        if (canHoldBubbleColumn(world, pos)) {
            world.setBlockState(pos, IntercraftBlocks.VANILLA_BUBBLE_COLUMN.getDefaultState().with(DRAG, drag), 2);
        }

    }

    private static boolean getDrag(IBlockReader blockReader, BlockPos pos)
    {
        BlockState state = blockReader.getBlockState(pos);
        if (state.has(DRAG))
            return state.get(DRAG);
        else
            return state.getBlock() != Blocks.SOUL_SAND;
    }

    private static BlockPos nextWaterPosUp(IWorld world, BlockPos start)
    {
        for (short y=(short)start.getY();y<world.getHeight();y++) {
            final BlockPos e = new BlockPos(start.getX(),y,start.getZ());
            if (canHoldBubbleColumn(world,e))
                return e;
            else if (!canTransfer(world.getBlockState(e)))
                return start;
        }

        return start;
    }

    private static BlockPos nextWaterPosDown(IWorld world, BlockPos start)
    {
        for (short y=(short)start.getY();y>0;y--) {
            final BlockPos e = new BlockPos(start.getX(),y,start.getZ());
            if (canHoldBubbleColumn(world,e))
                return e;
            else if (!canTransfer(world.getBlockState(e)))
                return start;
        }
        return start;
    }

    private static boolean canTransfer(BlockState state)
    {
        return (BlockTags.getCollection().getOrCreate(transparentID).contains(state.getBlock()) && getWaterLogged(state));
    }

    private static boolean getWaterLogged(BlockState state)
    {
        return state.has(WATERLOGGED) ? state.get(WATERLOGGED) : false;
    }

    @OnlyIn(Dist.CLIENT)
    private static void addBubbles(World worldIn, BlockPos pos, boolean drag, Random rand)
    {
        final double d0 = (double)pos.getX(), d1 = (double)pos.getY(), d2 = (double)pos.getZ();
        if (drag) {
            worldIn.addOptionalParticle(ParticleTypes.CURRENT_DOWN, d0 + 0.5D, d1 + 0.8D, d2, 0.0D, 0.0D, 0.0D);
            if (rand.nextInt(200) == 0)
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
        } else {
            worldIn.addOptionalParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
            worldIn.addOptionalParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + (double)rand.nextFloat(), d1 + (double)rand.nextFloat(), d2 + (double)rand.nextFloat(), 0.0D, 0.04D, 0.0D);
            if (rand.nextInt(200) == 0)
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
        }

    }

    /*public static BlockPos getNextValidDragPosUp(IBlockReader reader, BlockPos pos)
    {
        BlockPos p;
        int y = 0;
        while (reader.getHeight()>=pos.getY()+y) {//for (int y=1;0<pos.getY()-y;y++) {
            p = pos.up(y);
            Block block = reader.getBlockState(p).getBlock();
            if (
                    block == IntercraftBlocks.VANILLA_BUBBLE_COLUMN
            ) {
                System.out.println("Up: "+block.getRegistryName().getPath());
                System.out.println(p.toString());
                return p;
            }

            y++;

        }

        return null;
    }

    public static BlockPos getNextValidDragPosDown(IBlockReader reader, BlockPos pos)
    {
        BlockPos p;
        int y = 0;
        while (0<pos.getY()-y) {//for (int y=1;0<pos.getY()-y;y++) {
            p = pos.down(y);
            Block block = reader.getBlockState(p).getBlock();
            if (
                    block == IntercraftBlocks.VANILLA_BUBBLE_COLUMN ||
                    block == Blocks.SOUL_SAND ||
                    block == Blocks.MAGMA_BLOCK
            ) {
                System.out.println("Down: "+block.getRegistryName().getPath());
                System.out.println(p.toString());
                return p;
            }

            y++;

        }

        return null;
    }*/
}
