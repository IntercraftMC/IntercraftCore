package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.UtilBlocks;
import net.intercraft.intercraftcore.api.enumProperties.BlockProperties;
import net.intercraft.intercraftcore.api.enumProperties.BucketType;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BlockTreeTap extends Block
{
    /*protected static final VoxelShape
            SHAPE_WEST,//  = Block.makeCuboidShape(10.0D,1.0D, 5.0D,  16.0D, 11.0D, 11.0D),
            SHAPE_EAST,//  = Block.makeCuboidShape(0.0D, 1.0D, 5.0D,  6.0D,  11.0D, 11.0D),
            SHAPE_NORTH,// = Block.makeCuboidShape(5.0D, 1.0D, 10.0D, 11.0D, 11.0D, 16.0D),
            SHAPE_SOUTH;// = Block.makeCuboidShape(5.0D, 1.0D, 0.0D,  11.0D, 11.0D, 6.0D);*/
    protected static final VoxelShape[] SHAPES = UtilBlocks.generateDirectionShapes(UtilBlocks.UP | UtilBlocks.DOWN,0,1,5,6,11,11);

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
        if (tile == null) throw new NullPointerException("Could not find TreeTapTileEntity!");

         ItemStack stack = player.getHeldItem(handIn);
         BucketType bucket = state.get(BlockProperties.BUCKET);

        if (player.isCreative() && stack.getItem() == Items.STICK) { // Debugging.
            UtilBlocks.isSyncedTest(String.format("Can fill: %s has volume: %s is type: %s and viscosity: %s.", tile.getCanFill(), tile.getVolume(), tile.getFluidType().getName(), tile.getFluidType().getViscosity()),player);
            return true;
        }
        if (!worldIn.isRemote) {
             if (player.isCreative()) { // Debugging.
                 if (stack.getItem() == Items.BONE) {
                     if (player.getHeldItemOffhand().getItem() == Items.WATER_BUCKET) {
                         tile.setFluidType(FluidType.WATER);
                         return true;
                     }
                     else if (player.getHeldItemOffhand().getItem() == IntercraftItems.RESIN_BUCKET) {
                         tile.setFluidType(FluidType.RESIN);
                         return true;
                     }
                     else if (player.getHeldItemOffhand().getItem() == IntercraftItems.RUBBER_RESIN_BUCKET) {
                         tile.setFluidType(FluidType.RUBBER_RESIN);
                         return true;
                     }
                     tile.setCanFill(!tile.getCanFill());
                     return true;
                 }
             }

             for (BucketEnum bucketType : BucketEnum.values()) {
                 if (stack.getItem().equals(bucketType.getEmpty())) {
                     if (bucket != BucketType.NONE) return false;
                     worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET,bucketType.type));
                     tile.setFluidType(FluidType.NONE);
                     if (!player.isCreative())
                         stack.shrink(1);
                     return true;

                 } else if (stack.getItem().equals(bucketType.getWater())) {
                     if (bucket != BucketType.NONE) return false;
                     worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET,bucketType.type));
                     tile.setVolume(TreeTapTileEntity.maxVolume);
                     tile.setFluidType(FluidType.WATER);
                     if (!player.isCreative())
                         stack.shrink(1);
                     return true;

                 } else if (stack.getItem().equals(bucketType.getResin())) {
                     if (bucket != BucketType.NONE) return false;
                     worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET,bucketType.type));
                     tile.setVolume(TreeTapTileEntity.maxVolume);
                     tile.setFluidType(FluidType.RESIN);
                     if (!player.isCreative())
                         stack.shrink(1);
                     return true;

                 } else if (stack.getItem().equals(bucketType.getRubberResin())) {
                     if (bucket != BucketType.NONE) return false;
                     worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET,bucketType.type));
                     tile.setVolume(TreeTapTileEntity.maxVolume);
                     tile.setFluidType(FluidType.RUBBER_RESIN);
                     if (!player.isCreative())
                         stack.shrink(1);
                     return true;

                 }
             }

             if (stack.isEmpty()) {
                 if (bucket == BucketType.NONE) return false;

                 final boolean needForce = tile.volume >= TreeTapTileEntity.maxVolume;

                 if (needForce || tile.volume == 0 || player.isSneaking()) {
                     Item item = IntercraftItems.BUCKET_OAK;
                     for (BucketEnum bucketType : BucketEnum.values()) {
                         if (bucketType.type == bucket) {
                             if (needForce) {
                                 switch (tile.fluidType) {
                                     case WATER:
                                         item = bucketType.water;
                                         break;
                                     case RESIN:
                                         item = bucketType.resin;
                                         break;
                                     case RUBBER_RESIN:
                                         item = bucketType.rubberResin;
                                         break;
                                     default:
                                         item = bucketType.empty;
                                 }
                                 break;
                             } else {
                                 item = bucketType.empty;
                                 break;
                             }
                         }
                     }
                     worldIn.setBlockState(pos,state.with(BlockProperties.BUCKET,BucketType.NONE));
                     spawnAsEntity(worldIn,pos,new ItemStack(item));

                     tile.setVolume(0);
                     tile.setFluidType(FluidType.NONE);
                     return true;
                 }
             }
         }
         return true;
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
                return SHAPES[UtilBlocks.Connections.WEST.getOpposite().getValue()];

            case EAST:
                return SHAPES[UtilBlocks.Connections.EAST.getOpposite().getValue()];

            case SOUTH:
                return SHAPES[UtilBlocks.Connections.SOUTH.getOpposite().getValue()];

            default:
                return SHAPES[UtilBlocks.Connections.NORTH.getOpposite().getValue()];
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
    return BlockRenderLayer.CUTOUT;
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
    public ToolType getHarvestTool(BlockState state)
    {
        return ToolType.PICKAXE;
    }

    public enum BucketEnum
    {
        /**
         * Wooden buckets
         */
        WOODOAK(    BucketType.WOODOAK,    IntercraftItems.BUCKET_OAK,     IntercraftItems.WATER_BUCKET_OAK,     IntercraftItems.RESIN_BUCKET_OAK,     IntercraftItems.RUBBER_RESIN_BUCKET_OAK),
        WOODSPRUCE( BucketType.WOODSPRUCE, IntercraftItems.BUCKET_SPRUCE,  IntercraftItems.WATER_BUCKET_SPRUCE,  IntercraftItems.RESIN_BUCKET_SPRUCE,  IntercraftItems.RUBBER_RESIN_BUCKET_SPRUCE),
        WOODBIRCH(  BucketType.WOODBIRCH,  IntercraftItems.BUCKET_BIRCH,   IntercraftItems.WATER_BUCKET_BIRCH,   IntercraftItems.RESIN_BUCKET_BIRCH,   IntercraftItems.RUBBER_RESIN_BUCKET_BIRCH),
        WOODJUNGLE( BucketType.WOODJUNGLE, IntercraftItems.BUCKET_JUNGLE,  IntercraftItems.WATER_BUCKET_JUNGLE,  IntercraftItems.RESIN_BUCKET_JUNGLE,  IntercraftItems.RUBBER_RESIN_BUCKET_JUNGLE),
        WOODACACIA( BucketType.WOODACACIA, IntercraftItems.BUCKET_ACACIA,  IntercraftItems.WATER_BUCKET_ACACIA,  IntercraftItems.RESIN_BUCKET_ACACIA,  IntercraftItems.RUBBER_RESIN_BUCKET_ACACIA),
        WOODDARKOAK(BucketType.WOODDARKOAK,IntercraftItems.BUCKET_DARK_OAK,IntercraftItems.WATER_BUCKET_DARK_OAK,IntercraftItems.RESIN_BUCKET_DARK_OAK,IntercraftItems.RUBBER_RESIN_BUCKET_DARK_OAK),

        /**
         * Metal buckets
         */

        METALIRON(BucketType.METALIRON,Items.BUCKET,Items.WATER_BUCKET,IntercraftItems.RESIN_BUCKET,IntercraftItems.RUBBER_RESIN_BUCKET);

        private final BucketType type;
        private final Item empty,water,resin, rubberResin;

        /**
         * BucketEnum Constructor
         *
         * @param type  The BlockState type
         * @param empty The base bucket item
         * @param water The water bucket item
         * @param resin The resin bucket item
         * @param rubberResin The rubber resin bucket item
         */

        BucketEnum(BucketType type, Item empty, Item water, Item resin, Item rubberResin)
        {
            this.type        = type;
            this.empty       = empty;
            this.water       = water;
            this.resin       = resin;
            this.rubberResin = rubberResin;
        }

        public Item getEmpty()
        {
            return empty;
        }

        public Item getWater()
        {
            return water;
        }

        public Item getResin()
        {
            return resin;
        }

        public Item getRubberResin()
        {
            return rubberResin;
        }
    }
}
