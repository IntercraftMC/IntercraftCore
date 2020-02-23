package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.api.enumProperties.BlockProperties;
import net.intercraft.intercraftcore.element.Element;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockHardOre extends Block
{

    private final int tint;
    private final float hardness;

    public BlockHardOre(Element element, String suffix)
    {
        super(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(element.hardness,6.0F));

        setRegistryName(element.symbol+"_ore");

        setDefaultState(getDefaultState().with(BlockProperties.DENSITY,IntercraftCore.defDensity));

        hardness = element.hardness;
        tint = element.tintSec;


    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        try {
            int d = stack.getTag().getInt("intercraftcore:density");
            worldIn.setBlockState(pos,state.with(BlockProperties.DENSITY,d));
        } catch (NullPointerException e) {
            //System.out.println(String.format("Default hardness placement is %d.", IntercraftCore.defDensity));
            //worldIn.setBlockState(pos,state.with(BlockProperties.DENSITY,IntercraftCore.defDensity));
        }
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(BlockProperties.DENSITY);
    }

    @Override
    public net.minecraftforge.common.ToolType getHarvestTool(BlockState state)
    {
        return ToolType.PICKAXE;
    }

    @Override
    public float getBlockHardness(BlockState blockState, IBlockReader worldIn, BlockPos pos)
    {
        int density = blockState.get(BlockProperties.DENSITY);

        return (density + 0.9F) * this.blockHardness;
    }


    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public float getHardness()
    {
        return hardness;
    }

    public int getTint()
    {
        return tint;
    }


}
