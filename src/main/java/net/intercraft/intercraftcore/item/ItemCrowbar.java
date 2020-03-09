package net.intercraft.intercraftcore.item;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;

import java.util.Set;

public class ItemCrowbar extends ToolItem
{

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.IRON_DOOR,Blocks.IRON_TRAPDOOR);

    private final int tint;

    public ItemCrowbar(String name, short maxDamage, int tint)
    {
        super(3,-2.6f, ItemTier.DIAMOND,EFFECTIVE_ON,new Item.Properties().maxDamage(maxDamage).group(ItemGroup.TOOLS));
        this.tint = tint;

        setRegistryName(name);
    }

    public int getTint()
    {
        return tint;
    }
}
