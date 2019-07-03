package net.intercraft.intercraftcore.ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockHardOre extends Block implements IBlockColor, IItemColor {

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
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInt("density",state.get(BlockProperties.DENSITY));
        ItemStack stack = new ItemStack(this);
        stack.setTag(nbt);
        return stack;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, @Nullable EntityLivingBase placer, ItemStack stack) {
        try {
            int d = stack.getTag().getInt("density");
            worldIn.setBlockState(pos,state.with(BlockProperties.DENSITY,d));
        } catch (NullPointerException e) {
            System.out.println(String.format("Default hardness placement is %d.",3));
            worldIn.setBlockState(pos,state.with(BlockProperties.DENSITY,3));
        }
    }

    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        return this.drop;
    }
    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        int toRet = state.get(BlockProperties.DENSITY)+1;

        return Math.max(toRet,1);

    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        new EntityItem(worldIn,pos.getX(),pos.getY(),pos.getZ(), new ItemStack(this.drop));
    }


    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(BlockProperties.DENSITY);
    }


    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public int getColor(IBlockState p_getColor_1, IWorldReaderBase p_getColor_2, BlockPos p_getColor_3_, int p_getColor_4_) {
        return this.tint;
    }

    public int getColor(ItemStack stack, int tint) {
        return this.tint;
    }


}
