package net.intercraft.intercraftcore.block.cablecase;

import net.intercraft.intercraftcore.api.UtilBlocks;
import net.intercraft.intercraftcore.api.enumProperties.BlockProperties;
import net.intercraft.intercraftcore.api.enumProperties.CableCaseFaces;
import net.intercraft.intercraftcore.item.ItemElement;
import net.intercraft.intercraftcore.tileentity.CableCaseTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.intercraft.intercraftcore.api.enumProperties.BlockProperties.CONNECTED_NORTH;
import static net.intercraft.intercraftcore.api.enumProperties.BlockProperties.CONNECTED_SOUTH;
import static net.intercraft.intercraftcore.api.enumProperties.BlockProperties.CONNECTED_EAST;
import static net.intercraft.intercraftcore.api.enumProperties.BlockProperties.CONNECTED_WEST;
import static net.intercraft.intercraftcore.api.enumProperties.BlockProperties.CONNECTED_UP;
import static net.intercraft.intercraftcore.api.enumProperties.BlockProperties.CONNECTED_DOWN;

public class BlockCableCase extends Block
{

    public static final VoxelShape[]
            CONNECTION_SIDES = UtilBlocks.generateDirectionShapes(0,2,2,2,14,14),
            MODULE_SIDES     = UtilBlocks.generateDirectionShapes(1,0,0,2,16,16);
    public static final VoxelShape MIDDLE = Block.makeCuboidShape(2,2,2,14,14,14);


    private static final ResourceLocation
            crowbars = new ResourceLocation("forge","crowbars"),
            wrenches = new ResourceLocation("forge","wrenches");

    public BlockCableCase() {

        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3.0f));


        setDefaultState(getDefaultState().with(CONNECTED_NORTH, CableCaseFaces.NONE).with(CONNECTED_SOUTH, CableCaseFaces.NONE).with(CONNECTED_EAST, CableCaseFaces.NONE).with(CONNECTED_WEST, CableCaseFaces.NONE).with(CONNECTED_UP, CableCaseFaces.NONE).with(CONNECTED_DOWN, CableCaseFaces.NONE));
        setRegistryName("cable_case");


    }

    private static CableCaseTileEntity getTileEntity(BlockState state, IWorld worldIn, BlockPos pos)
    {
        CableCaseTileEntity te = state.getBlock().hasTileEntity(state) ? (CableCaseTileEntity)worldIn.getTileEntity(pos) : null;
        if (te == null) throw new NullPointerException(String.format("Could not find CableCaseTileEntity at [%d %d %d]!",pos.getX(),pos.getY(),pos.getZ()));
        else return te;
    }

    private static boolean canConnect(Direction facing, final BlockState stateIn, final CableCaseTileEntity te, final BlockState stateOpp, final CableCaseTileEntity teOpp)
    {
        final BlockState[] s = {stateIn,stateOpp};
        final CableCaseTileEntity[] t = {te,teOpp};
        for (byte i=0;i<2;i++) {
            final EnumProperty<CableCaseFaces> f = getPropertyFromDirection(facing);
            if (t[i].getPlate(getConnectionFromDirection(facing).getValue()) != null || s[i].get(f) == CableCaseFaces.MODULE) return false;
            facing = facing.getOpposite();
        }
        return true;
    }

    public static EnumProperty<CableCaseFaces> getPropertyFromDirection(Direction direction)
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

    public static EnumProperty<CableCaseFaces> getPropertyFromConnection(UtilBlocks.Connections connection)
    {
        switch (connection) {
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

    public static UtilBlocks.Connections getConnectionFromDirection(Direction direction)
    {
        switch (direction) {
            case SOUTH:
                return UtilBlocks.Connections.SOUTH;
            case EAST:
                return UtilBlocks.Connections.EAST;
            case WEST:
                return UtilBlocks.Connections.WEST;
            case UP:
                return UtilBlocks.Connections.UP;
            case DOWN:
                return UtilBlocks.Connections.DOWN;
            default:
                return UtilBlocks.Connections.NORTH;
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (player.getHeldItem(handIn).getItem().equals(this.getItem(worldIn,pos,state).getItem())) return false;

        Direction facing = hit.getFace();
        ItemStack stack = player.getHeldItem(handIn);
        CableCaseTileEntity te = getTileEntity(state,worldIn,pos);

        if (player.isCreative()) {
            if (player.getHeldItem(handIn).isEmpty()) {
                UtilBlocks.isSyncedTest(String.format("Facing: %s Item: %s", facing.getName(), te.getPlate(getConnectionFromDirection(facing).getValue())),player);
            }
        }


        if (!worldIn.isRemote()) {

            if (state.get(getPropertyFromDirection(facing)) == CableCaseFaces.NONE) {
                if (ItemTags.getCollection().getOrCreate(crowbars).contains(stack.getItem())) {
                    final byte p = getConnectionFromDirection(facing).getValue();
                    if (te.getPlate(p) != null) {
                        spawnAsEntity(worldIn, pos, new ItemStack(te.getPlate(p)));
                        te.setPlate(null, p);
                        if (!player.isCreative() && stack.getItem().isDamageable())
                            stack.damageItem(1, player, entity -> entity.sendBreakAnimation(handIn));
                    }
                } else if (player.getHeldItem(handIn).getItem() != Items.AIR && ItemTags.getCollection().getOrCreate(wrenches).contains(player.getHeldItemOffhand().getItem())) {
                    Item it = player.getHeldItem(handIn).getItem();
                    boolean valid = false;

                    if (it instanceof ItemElement)if(((ItemElement)it).getSuffix().equals("plate"))valid = true;



                    if (valid) {
                        te.setPlate(stack.getItem(), getConnectionFromDirection(facing).getValue());
                        if (!player.isCreative())
                            stack.shrink(1);
                    }
                }
            }


            if (player.isCreative()) {
                if (player.getHeldItem(handIn).getItem() == Items.STICK) {
                    switch (state.get(getPropertyFromDirection(facing))) {
                        case NONE:
                            worldIn.setBlockState(pos, state.with(getPropertyFromDirection(facing),CableCaseFaces.CONNECTED));
                            break;
                        case CONNECTED:
                            worldIn.setBlockState(pos, state.with(getPropertyFromDirection(facing),CableCaseFaces.NONE));
                    }
                }
            }



        }
        return true;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!worldIn.isRemote()) {

            if (worldIn.getBlockState(facingPos).getBlock().equals(this)) {
                BlockState stateOpp = worldIn.getBlockState(facingPos);

                CableCaseTileEntity te = getTileEntity(stateIn,worldIn,currentPos);
                CableCaseTileEntity teOpp = getTileEntity(stateOpp,worldIn,facingPos);

                if (canConnect(facing,stateIn,te,stateOpp,teOpp)) {
                    worldIn.setBlockState(currentPos, stateIn.with(getPropertyFromDirection(facing),CableCaseFaces.CONNECTED), 2);
                    worldIn.setBlockState(facingPos, stateOpp.with(getPropertyFromDirection(facing.getOpposite()),CableCaseFaces.CONNECTED), 2);
                }
            } else if (stateIn.get(getPropertyFromDirection(facing)) == CableCaseFaces.CONNECTED) {
                worldIn.setBlockState(currentPos, stateIn.with(getPropertyFromDirection(facing),CableCaseFaces.NONE), 2);
            }

        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(CONNECTED_NORTH,CONNECTED_SOUTH,CONNECTED_EAST,CONNECTED_WEST,CONNECTED_UP,CONNECTED_DOWN);
        super.fillStateContainer(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        VoxelShape shape = MIDDLE;
        for (UtilBlocks.Connections c : UtilBlocks.Connections.values()) {
            switch (state.get(getPropertyFromConnection(c)).getName()) {
                case "connected":
                    shape = VoxelShapes.or(shape, CONNECTION_SIDES[c.getValue()]);
                    break;
                case "module":
                    shape = VoxelShapes.or(shape, MODULE_SIDES[c.getValue()]);
            }
        }
        return shape;
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
}
