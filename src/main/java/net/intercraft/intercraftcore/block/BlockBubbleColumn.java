package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class BlockBubbleColumn extends BubbleColumnBlock
{
    public static final ResourceLocation transparentID = new ResourceLocation(Reference.MODID,"bubble_column_transparent_blocks");

    public BlockBubbleColumn(String name, Block.Properties properties)
    {
        super(properties);
        setRegistryName(name);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();

        //(BlockTags.getCollection().getOrCreate(transparentID).contains(block) && getWaterLogged(worldIn.getBlockState(pos.down())))

        return canTransfer(worldIn.getBlockState(pos.down())) || block == IntercraftBlocks.DRAIN || block == IntercraftBlocks.VANILLA_BUBBLE_COLUMN || block == Blocks.SOUL_SAND || block == Blocks.MAGMA_BLOCK;

    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        /*BlockState blockState = worldIn.getBlockState(pos.down());
        boolean drag;

        if (canTransfer(blockState)) {

            BlockPos p = getNextValidDragPosDown(worldIn,pos.down());

            if (p == null)
                return;
            else
                drag = getDrag(worldIn,p);
            System.out.println(String.format("Block under it has drag %s",drag));



        } else
            drag = getDrag(worldIn,pos.down());

        System.out.println(String.format("Drag is %s",drag));
        placeBubbleColumn(worldIn, pos.up(), drag);*/
        placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn, pos.down()));
    }


    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {

        /*BlockState blockState = worldIn.getBlockState(pos.up());

        if (canTransfer(blockState)) {

            BlockPos p = getNextValidDragPosUp(worldIn,pos);

            if (p != null)
                placeBubbleColumn(worldIn, p, getDrag(worldIn, pos));

        } else
            placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn,pos));*/
        placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn, pos));
    }

    public static void placeBubbleColumn(IWorld world, BlockPos pos, boolean drag) {
        if (canHoldBubbleColumn(world, pos)) {
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.WATER)
                world.setBlockState(pos,IntercraftBlocks.VANILLA_BUBBLE_COLUMN.getDefaultState().with(DRAG,drag),2);
            else
                world.setBlockState(pos, state.with(DRAG,drag), 2);
        }

    }

    public static boolean canHoldBubbleColumn(IWorld world, BlockPos pos)
    {
        IFluidState ifluidstate = world.getFluidState(pos);
        BlockState state = world.getBlockState(pos);
        return (state.getBlock() == Blocks.WATER && ifluidstate.getLevel() >= 8 && ifluidstate.isSource()) || (state.getBlock() == IntercraftBlocks.DRAIN && getWaterLogged(state));
    }


    public static BlockPos getNextValidDragPosUp(IBlockReader reader, BlockPos pos)
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
    }

    public static boolean getDrag(IBlockReader blockReader, BlockPos pos)
    {
        BlockState blockstate = blockReader.getBlockState(pos);
        Block block = blockstate.getBlock();
        if (block == IntercraftBlocks.DRAIN || block == IntercraftBlocks.VANILLA_BUBBLE_COLUMN || block == Blocks.BUBBLE_COLUMN) {
            return blockstate.get(DRAG);
        } else {
            return block != Blocks.SOUL_SAND;
        }

        /*} else if (canTransfer(block,blockstate)) {

            Block bottomBlock = blockReader.getBlockState(pos).getBlock();
            int y = 0;
            while (bottomBlock != Blocks.BUBBLE_COLUMN && pos.getY()-y > 0) {
                if (bottomBlock == Blocks.SOUL_SAND || bottomBlock == Blocks.MAGMA_BLOCK)
                    return block != Blocks.SOUL_SAND;
                y++;
                bottomBlock = blockReader.getBlockState(pos.down(y)).getBlock();
            }
            BlockPos newPos = new BlockPos(pos.getX(),pos.getY()-y,pos.getZ());
            System.out.println(newPos.toString());
            System.out.println(blockReader.getBlockState(newPos).get(DRAG));
            return blockReader.getBlockState(newPos).get(DRAG);

        */
    }

    public static boolean canTransfer(BlockState state)
    {
        return (BlockTags.getCollection().getOrCreate(transparentID).contains(state.getBlock()) && getWaterLogged(state));
    }

    private static boolean getWaterLogged(BlockState state)
    {
        return state.has(WATERLOGGED) ? state.get(WATERLOGGED) : false;
    }
}
