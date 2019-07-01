package net.intercraft.intercraftcore.ore;

import net.intercraft.intercraftcore.init.IntercraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHardOre extends Block {

    private int meta = 4;


    public BlockHardOre(final String name, final float hardness, final float resistance) {
        super(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(hardness,resistance));

        setRegistryName(name+"_ore");
        setDefaultState(this.stateContainer.getBaseState().with(BlockProperties.DENSITY, 1));
    }

    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        return IntercraftItems.TEST;
    }
    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        return Math.max(this.meta,1);
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {

    }


    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }


}
