package net.intercraft.intercraftcore.ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHardOre extends Block {

    private Item drop;
    private int tint;


    public BlockHardOre(final String name, final float hardness, final float resistance, Item drop, int tint) {
        super(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(hardness,resistance));

        setRegistryName(name+"_ore");
        this.drop = drop;
        this.tint = tint;
    }


    public int getTint() {
        return this.tint;
    }

    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(this);
    }

    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        return this.drop;
    }
    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        int toRet = state.get(BlockProperties.DENSITY)+1;

        return Math.max(toRet,1);

    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {

    }


    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(BlockProperties.DENSITY);
    }


    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }


}
