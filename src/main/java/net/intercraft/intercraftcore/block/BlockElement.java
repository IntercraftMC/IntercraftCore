package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.element.Element;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

public class BlockElement extends Block
{

    protected Element element;

    public BlockElement(Element element, String registrySuffix)
    {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL));
        this.element = element;
        setRegistryName(element.symbol + "_" + registrySuffix);

    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.SOLID;
    }

    public int getTint() {
        return element.tint;
    }
}
