package net.intercraft.intercraftcore.ore;

import net.intercraft.intercraftcore.IntercraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.LootTableProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraftforge.event.LootTableLoadEvent;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockHardOre extends Block {

    private Item drop;
    private int tint;

    public final int dropMultiplier = 2;


    public BlockHardOre(final String name, final float hardness, final float resistance, Item drop, int tint) {
        super(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(hardness,resistance));

        setRegistryName(name+"_ore");
        this.drop = drop;
        this.tint = tint;



    }

    /*protected ItemStack getSilkTouchDrop(BlockState state) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("density",state.get(BlockProperties.DENSITY));
        ItemStack stack = new ItemStack(this);
        stack.setTag(nbt);
        return stack;
    }*/

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        try {
            int d = stack.getTag().getInt("density");
            worldIn.setBlockState(pos,state.with(BlockProperties.DENSITY,d));
        } catch (NullPointerException e) {
            System.out.println(String.format("Default hardness placement is %d.", IntercraftCore.defDensity));
            worldIn.setBlockState(pos,state.with(BlockProperties.DENSITY,IntercraftCore.defDensity));
        }
    }

    /*public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos, int fortune) {
        return this.drop;
    }
    public int getItemsToDropCount(BlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        int toRet = (state.get(BlockProperties.DENSITY)+1)*dropMultiplier;

        return Math.max(toRet,dropMultiplier);

    }*/

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        new ItemEntity(worldIn,pos.getX(),pos.getY(),pos.getZ(), new ItemStack(this.drop,dropMultiplier));
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(BlockProperties.DENSITY);
    }




    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    /*@Override
    public String getTranslationKey() {

    }*/

    public int getTint()
    {
        return this.tint;
    }


}
