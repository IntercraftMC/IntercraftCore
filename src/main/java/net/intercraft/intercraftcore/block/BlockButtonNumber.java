package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.UtilBlocks;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class BlockButtonNumber extends AbstractButtonBlock
{

    private final String name, translateKey;
    private final byte integer, signalHigh;
    private final boolean isWooden;

    private static final VoxelShape[]
            SHAPES, SHAPES_PRESSED,
            SHAPES_FLOOR, SHAPES_FLOOR_PRESSED,
            SHAPES_ROOF, SHAPES_ROOF_PRESSED;

    static {

        final double[][]
                full    = UtilBlocks.generateDirectionShapesValues(5,2,0,11,13,2),
                pressed = UtilBlocks.generateDirectionShapesValues(5,2,0,11,13,1);

        SHAPES         = UtilBlocks.assembleShapes(full);
        SHAPES_PRESSED = UtilBlocks.assembleShapes(pressed);

        SHAPES_FLOOR         = UtilBlocks.generateDirectionShapes(UtilBlocks.UP | UtilBlocks.DOWN, full[UtilBlocks.Connections.DOWN.getValue()]);
        SHAPES_FLOOR_PRESSED = UtilBlocks.generateDirectionShapes(UtilBlocks.UP | UtilBlocks.DOWN, pressed[UtilBlocks.Connections.DOWN.getValue()]);

        SHAPES_ROOF         = UtilBlocks.generateDirectionShapes(UtilBlocks.UP | UtilBlocks.DOWN, full[UtilBlocks.Connections.UP.getValue()]);
        SHAPES_ROOF_PRESSED = UtilBlocks.generateDirectionShapes(UtilBlocks.UP | UtilBlocks.DOWN, pressed[UtilBlocks.Connections.UP.getValue()]);

    }

    public BlockButtonNumber(final String name, final String translateKey, final byte integer, final boolean isWooden)
    {
        super(isWooden,Block.Properties.create(isWooden ? Material.WOOD : Material.ROCK).hardnessAndResistance(0.5f).sound(isWooden ? SoundType.WOOD : SoundType.STONE).doesNotBlockMovement());
        if (integer < 0) throw new IllegalArgumentException("Can't be a negative value!");
        //if (!name.contains("%s")) throw new IllegalArgumentException("Needs '%s' in the name!");

        this.name          = String.format(name,integer);
        this.translateKey  = translateKey;
        this.integer       = integer;
        this.signalHigh    = (byte)(Math.min(14,integer)+1);
        this.isWooden      = isWooden;

        setRegistryName(this.name);
    }

    public String getName()
    {
        return name;
    }

    public byte getNumber()
    {
        return integer;
    }

    public boolean isWooden()
    {
        return isWooden;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        Direction direction = state.get(HORIZONTAL_FACING);
        final boolean powered = state.get(POWERED);

        final VoxelShape[] SHAPE, SHAPE_PRESSED;

        switch(state.get(FACE)) {
            case WALL:
                direction = direction.getOpposite();
                SHAPE = SHAPES;
                SHAPE_PRESSED = SHAPES_PRESSED;
                break;
            case CEILING:
                direction = direction.getOpposite();
                SHAPE = SHAPES_ROOF;
                SHAPE_PRESSED = SHAPES_ROOF_PRESSED;
                break;
            default: // FLOOR
                SHAPE = SHAPES_FLOOR;
                SHAPE_PRESSED = SHAPES_FLOOR_PRESSED;
        }

        switch (direction) {
            case EAST:
                return powered ? SHAPE_PRESSED[UtilBlocks.Connections.EAST.getValue()] : SHAPE[UtilBlocks.Connections.EAST.getValue()];
            case WEST:
                return powered ? SHAPE_PRESSED[UtilBlocks.Connections.WEST.getValue()] : SHAPE[UtilBlocks.Connections.WEST.getValue()];
            case SOUTH:
                return powered ? SHAPE_PRESSED[UtilBlocks.Connections.SOUTH.getValue()] : SHAPE[UtilBlocks.Connections.SOUTH.getValue()];
            default: // NORTH:
                return powered ? SHAPE_PRESSED[UtilBlocks.Connections.NORTH.getValue()] : SHAPE[UtilBlocks.Connections.NORTH.getValue()];
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getTranslationKey()
    {
        final String k = super.getTranslationKey();
        return I18n.format(k.substring(0,k.indexOf(name))+translateKey,new TranslationTextComponent("subtitle."+Reference.MODID+".numbers."+integer).getFormattedText());
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isPushed)
    {
        return isPushed ? isWooden ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : isWooden ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side)
    {
        return blockState.get(POWERED) ? signalHigh : 0;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side)
    {
        return blockState.get(POWERED) && getFacing(blockState) == side ? signalHigh : 0;
    }
}
