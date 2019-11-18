package net.intercraft.intercraftcore.block;


import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.tileentity.AutoCraftingTableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAutoCraftingTable extends Block
{
    private static final ITextComponent name = new TranslationTextComponent("container."+ Reference.MODID+".auto_crafting");

    public BlockAutoCraftingTable()
    {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f));
        setRegistryName("auto_crafting_table");
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
        return new AutoCraftingTableTileEntity();
    }

    @Override
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((i,playerInventory,playerEntity) -> new WorkbenchContainer(i, playerInventory, IWorldPosCallable.of(worldIn, pos)), name);
    }

    public int getTint()
    {
        return 0x0d86d0;
    }
}
