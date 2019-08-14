package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.BucketType;
import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.init.IntercraftItems;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BlockTreeTap extends Block
{

    //                                                                    double x1,       double y1,       double z1,        double x2,        double y2,         double z2
    protected static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(10.0D, 1.0D, 5.0D, 16.0D, 11.0D, 11.0D);
    protected static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(0.0D, 1.0D, 5.0D, 6.0D, 11.0D, 11.0D);
    protected static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(5.0D, 1.0D, 10.0D, 11.0D, 11.0D, 16.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(5.0D, 1.0D, 0.0D, 11.0D, 11.0D, 6.0D);


    public BlockTreeTap()
    {
        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL));

        setRegistryName("treetap");
        setDefaultState(getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH).with(BlockProperties.BUCKET, BucketType.NONE));
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
        builder.add(BlockProperties.BUCKET);

    }




    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TreeTapTileEntity tile = state.getBlock().hasTileEntity(state) ? (TreeTapTileEntity) worldIn.getTileEntity(pos) : null;
        if (tile == null) return false;

         ItemStack stack = player.getHeldItem(handIn);

        //player.sendMessage(new StringTextComponent(String.format("Can fill: %s has volume: %s is type: %s and viscosity: %s.",tile.getCanFill(), tile.getVolume(), tile.getFluidType().getName(), tile.getFluidType().getViscosity())));

         if (!worldIn.isRemote) {

             if (player.isCreative())
                if (stack.getItem() == Items.STICK)
                    player.sendMessage(new StringTextComponent(String.format("Can fill: %s has volume: %s is type: %s and viscosity: %s.",tile.getCanFill(), tile.getVolume(), tile.getFluidType().getName(), tile.getFluidType().getViscosity())));
                else if (stack.getItem() == Items.BONE) {
                    tile.setCanFill(!tile.getCanFill());
                    if (player.getHeldItemOffhand().getItem() == Items.WATER_BUCKET)
                        tile.setFluidType(FluidType.WATER);
                    else if (player.getHeldItemOffhand().getItem() == IntercraftItems.BUCKETRESIN)
                        tile.setFluidType(FluidType.RESIN);
                }


             if (stack.getItem() == Items.BUCKET) {

                 if (state.get(BlockProperties.BUCKET) != BucketType.NONE) return false;


                 worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET,BucketType.METALIRON));
                 tile.setFluidType(FluidType.NONE);
                 tile.setVolume(0);

                 if (!player.isCreative())
                     stack.shrink(1);
                 return true;

             }  else if (stack.getItem() == Items.WATER_BUCKET) {

                 if (state.get(BlockProperties.BUCKET) != BucketType.NONE) return false;

                 worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET, BucketType.METALIRON));
                 tile.setFluidType(FluidType.WATER);
                 tile.setVolume(TreeTapTileEntity.maxVolume);
                 if (!player.isCreative())
                     stack.shrink(1);
                 return true;


             } else if (stack.getItem() == IntercraftItems.BUCKETRESIN) {

                 if (state.get(BlockProperties.BUCKET) != BucketType.NONE) return false;

                 worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET, BucketType.METALIRON));
                 tile.setFluidType(FluidType.RESIN);
                 tile.setVolume(TreeTapTileEntity.maxVolume);
                 if (!player.isCreative())
                     stack.shrink(1);

                 return true;

             }  else if (stack.isEmpty()) {

                 if (state.get(BlockProperties.BUCKET) == BucketType.NONE) return false;

                 boolean force = tile.getVolume() >= TreeTapTileEntity.maxVolume;

                 if (force || player.isSneaking()) {

                     Item item;

                     switch (tile.getFluidType()) {
                         case RESIN:
                             item = IntercraftItems.BUCKETRESIN;
                             break;
                         case WATER:
                             item = Items.WATER_BUCKET;
                             break;
                        default:
                            item = Items.BUCKET;
                     }

                     worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET, BucketType.NONE));
                     spawnAsEntity(worldIn, pos, new ItemStack(item));

                     tile.setVolume(0);
                     tile.setFluidType(FluidType.NONE);

                     return true;
                 }

             }
                return false;
         } else return true;

    }


    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.with(HORIZONTAL_FACING, direction.getOpposite());
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.offset(state.get(HORIZONTAL_FACING).getOpposite())).getBlock();
        return block.isIn(BlockTags.LOGS);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        return facing == stateIn.get(HORIZONTAL_FACING).getOpposite() && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext)
    {
        switch (state.get(HORIZONTAL_FACING)) {
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


    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TreeTapTileEntity();
    }

    @Override
    public net.minecraftforge.common.ToolType getHarvestTool(BlockState state)
    {
        return ToolType.PICKAXE;
    }

}
