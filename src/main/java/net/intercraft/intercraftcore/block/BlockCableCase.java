package net.intercraft.intercraftcore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

public class BlockCableCase extends Block {
    //private Item item;

    public BlockCableCase() {

        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(3.0f,3.0f));


        setRegistryName("cable_case");

        //item = new ItemBlock(this,new Item.Properties().group(IntercraftItemGroups.WIRING)).setRegistryName(this.getRegistryName());

    }
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
