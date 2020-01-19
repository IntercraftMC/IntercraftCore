package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_NORTH;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_SOUTH;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_EAST;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_WEST;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_UP;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_DOWN;

public class BlockCableCase extends Block
{

    private static byte[] SIDE = {0,2,2,2,14,14};

    protected static final VoxelShape[] CONNECTION_SIDES = getSides(SIDE[0],SIDE[1],SIDE[2],SIDE[3],SIDE[4],SIDE[5]);
    protected static final VoxelShape MIDDLE = Block.makeCuboidShape(2,2,2,14,14,14);
            /*CONNECTION_TOP = Block.makeCuboidShape(2,14,2,14,16,14),
            CONNECTION_BOTTOM = Block.makeCuboidShape(2,0,2,14,2,14);*/


    public BlockCableCase() {

        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3.0f,3.0f));


        setDefaultState(getDefaultState().with(CONNECTED_NORTH, false).with(CONNECTED_SOUTH, false).with(CONNECTED_EAST, false).with(CONNECTED_WEST, false).with(CONNECTED_UP, false).with(CONNECTED_DOWN, false));
        setRegistryName("cable_case");


    }

    private static VoxelShape[] getSides(short x1, short y1, short z1, short x2, short y2, short z2)
    {

        VoxelShape[] t = new VoxelShape[6];
        short[] k1 = {(short)(x1-8),y1,(short)(z1-8)};
        short[] k2 = {(short)(x2-8),y2,(short)(z2-8)};
        t[0] = Block.makeCuboidShape(k1[0]+8,k1[1],k1[2]+8,k2[0]+8,k2[1],k2[2]+8);
        for (byte i=1;i<4;i++) {
            k1 = Util.rotateY(k1[0],k1[1],k1[2],Util.TCW);
            k2 = Util.rotateY(k2[0],k2[1],k2[2],Util.TCW);
            t[i] = Block.makeCuboidShape(k1[0]+8,k1[1],k1[2]+8,k2[0]+8,k2[1],k2[2]+8);
        }
        t[4] = Block.makeCuboidShape(2,14,2,14,16,14);
        t[5] = Block.makeCuboidShape(2,0,2,14,2,14);
        return t;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!worldIn.isRemote()) {

            if (worldIn.getBlockState(facingPos).getBlock().equals(this)) {
                BlockState stateOpp = worldIn.getBlockState(facingPos);

                switch (facing) {
                    case NORTH:
                        stateIn.with(BlockProperties.CONNECTED_NORTH, true);
                        stateOpp.with(BlockProperties.CONNECTED_SOUTH, true);
                        break;
                    case SOUTH:
                        stateIn.with(BlockProperties.CONNECTED_SOUTH, true);
                        stateOpp.with(BlockProperties.CONNECTED_NORTH, true);
                        break;
                    case EAST:
                        stateIn.with(BlockProperties.CONNECTED_EAST, true);
                        stateOpp.with(BlockProperties.CONNECTED_WEST, true);
                        break;
                    case WEST:
                        stateIn.with(BlockProperties.CONNECTED_WEST, true);
                        stateOpp.with(BlockProperties.CONNECTED_EAST, true);
                        break;
                    case UP:
                        stateIn.with(BlockProperties.CONNECTED_UP, true);
                        stateOpp.with(BlockProperties.CONNECTED_DOWN, true);
                        break;
                    default:
                        stateIn.with(BlockProperties.CONNECTED_DOWN, true);
                        stateOpp.with(BlockProperties.CONNECTED_UP, true);
                }
                worldIn.setBlockState(currentPos, stateIn, 2);
                worldIn.setBlockState(facingPos, stateOpp, 2);
            } else {
                switch (facing) {
                    case NORTH:
                        stateIn.with(BlockProperties.CONNECTED_NORTH, false);
                        break;
                    case SOUTH:
                        stateIn.with(BlockProperties.CONNECTED_SOUTH, false);
                        break;
                    case EAST:
                        stateIn.with(BlockProperties.CONNECTED_EAST, false);
                        break;
                    case WEST:
                        stateIn.with(BlockProperties.CONNECTED_WEST, false);
                        break;
                    case UP:
                        stateIn.with(BlockProperties.CONNECTED_UP, false);
                        break;
                    default:
                        stateIn.with(BlockProperties.CONNECTED_DOWN, false);
                }
                worldIn.setBlockState(currentPos, stateIn, 2);
            }

        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(CONNECTED_NORTH);
        builder.add(CONNECTED_SOUTH);
        builder.add(CONNECTED_EAST);
        builder.add(CONNECTED_WEST);
        builder.add(CONNECTED_UP);
        builder.add(CONNECTED_DOWN);

        super.fillStateContainer(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        VoxelShape shape = MIDDLE;
        if (state.get(BlockProperties.CONNECTED_NORTH)) shape = VoxelShapes.or(shape,Connections.NORTH.getShape());
        if (state.get(BlockProperties.CONNECTED_SOUTH)) shape = VoxelShapes.or(shape,Connections.SOUTH.getShape());
        if (state.get(BlockProperties.CONNECTED_EAST)) shape = VoxelShapes.or(shape,Connections.EAST.getShape());
        if (state.get(BlockProperties.CONNECTED_WEST)) shape = VoxelShapes.or(shape,Connections.WEST.getShape());
        if (state.get(BlockProperties.CONNECTED_UP)) shape = VoxelShapes.or(shape,Connections.UP.getShape());
        if (state.get(BlockProperties.CONNECTED_DOWN)) shape = VoxelShapes.or(shape,Connections.DOWN.getShape());

        return VoxelShapes.or(shape);
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public static enum Connections
    {
        NORTH((byte) 1),
        SOUTH((byte) 3),
        EAST((byte) 2),
        WEST((byte) 0),
        UP((byte) 4),
        DOWN((byte) 5);

        private final byte value;

        Connections(byte value)
        {
            this.value = value;
        }
        public byte getValue()
        {
            return value;
        }
        public VoxelShape getShape()
        {
            return CONNECTION_SIDES[value];
        }
    }
}
