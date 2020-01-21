package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.api.BlockProperties;
import net.intercraft.intercraftcore.api.CableCaseFaces;
import net.intercraft.intercraftcore.api.Util;
import net.intercraft.intercraftcore.tileentity.CableCaseTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_NORTH;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_SOUTH;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_EAST;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_WEST;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_UP;
import static net.intercraft.intercraftcore.api.BlockProperties.CONNECTED_DOWN;

public class BlockCableCase extends Block
{

    private static final byte[] SIDE = {0,2,2,2,14,14}, SIDE_MOD = {0,0,2,2,16,16};
    public static final VoxelShape[] CONNECTION_SIDES = getSides(SIDE[0],SIDE[1],SIDE[2],SIDE[3],SIDE[4],SIDE[5]);
    public static final VoxelShape MIDDLE = Block.makeCuboidShape(2,2,2,14,14,14);


    public BlockCableCase() {

        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3.0f,3.0f));


        setDefaultState(getDefaultState().with(CONNECTED_NORTH,CableCaseFaces.NONE).with(CONNECTED_SOUTH, CableCaseFaces.NONE).with(CONNECTED_EAST, CableCaseFaces.NONE).with(CONNECTED_WEST, CableCaseFaces.NONE).with(CONNECTED_UP, CableCaseFaces.NONE).with(CONNECTED_DOWN, CableCaseFaces.NONE));
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

    private static boolean canConnect(Direction facing, final BlockState stateIn, final BlockState stateOpp)
    {
        final BlockState[] s = {stateIn,stateOpp};
        for (byte i=0;i<2;i++) {
            final EnumProperty<CableCaseFaces> f = getFace(facing);
            if (s[i].get(f) == CableCaseFaces.PLATE || s[i].get(f) == CableCaseFaces.MODULE) return false;
            facing = facing.getOpposite();
        }
        return true;
    }

    public static EnumProperty<CableCaseFaces> getFace(Direction direction)
    {
        switch (direction) {
            case SOUTH:
                return BlockProperties.CONNECTED_SOUTH;
            case EAST:
                return BlockProperties.CONNECTED_EAST;
            case WEST:
                return BlockProperties.CONNECTED_WEST;
            case UP:
                return BlockProperties.CONNECTED_UP;
            case DOWN:
                return BlockProperties.CONNECTED_DOWN;
            default:
                return BlockProperties.CONNECTED_NORTH;
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (player.getHeldItem(handIn).getItem().equals(this.getItem(worldIn,pos,state).getItem())) return false;


        if (!worldIn.isRemote()) {
            Direction facing = hit.getFace();

            System.out.println(facing);
            worldIn.setBlockState(pos,state.with(getFace(facing),CableCaseFaces.PLATE));

        }
        return true;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!worldIn.isRemote()) {

            BlockState stateOpp = worldIn.getBlockState(facingPos);

            if (worldIn.getBlockState(facingPos).getBlock().equals(this)) {
                if (canConnect(facing,stateIn,stateOpp)) {
                    worldIn.setBlockState(currentPos, stateIn.with(getFace(facing),CableCaseFaces.CONNECTED), 2);
                    worldIn.setBlockState(facingPos, stateOpp.with(getFace(facing.getOpposite()),CableCaseFaces.CONNECTED), 2);
                }
            } else {
                if (stateIn.get(getFace(facing)) == CableCaseFaces.CONNECTED)
                    worldIn.setBlockState(currentPos, stateIn.with(getFace(facing),CableCaseFaces.NONE), 2);
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
        if (state.get(BlockProperties.CONNECTED_NORTH).getName().equals("connected")) shape = VoxelShapes.or(shape,Connections.NORTH.getShape());
        if (state.get(BlockProperties.CONNECTED_SOUTH).getName().equals("connected")) shape = VoxelShapes.or(shape,Connections.SOUTH.getShape());
        if (state.get(BlockProperties.CONNECTED_EAST).getName().equals("connected")) shape = VoxelShapes.or(shape,Connections.EAST.getShape());
        if (state.get(BlockProperties.CONNECTED_WEST).getName().equals("connected")) shape = VoxelShapes.or(shape,Connections.WEST.getShape());
        if (state.get(BlockProperties.CONNECTED_UP).getName().equals("connected")) shape = VoxelShapes.or(shape,Connections.UP.getShape());
        if (state.get(BlockProperties.CONNECTED_DOWN).getName().equals("connected")) shape = VoxelShapes.or(shape,Connections.DOWN.getShape());

        return VoxelShapes.or(shape);
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

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new CableCaseTileEntity();
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
