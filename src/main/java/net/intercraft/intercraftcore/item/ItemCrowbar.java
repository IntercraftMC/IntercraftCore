package net.intercraft.intercraftcore.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;

public class ItemCrowbar extends AxeItem
{
    private final int tint;

    public ItemCrowbar(String name, short maxDamage, int tint)
    {
        super(ItemTier.IRON,3,-2.6f,new Item.Properties().maxDamage(maxDamage).group(ItemGroup.TOOLS));
        this.tint = tint;

        setRegistryName(name);
    }

    public int getTint()
    {
        return tint;
    }
}
