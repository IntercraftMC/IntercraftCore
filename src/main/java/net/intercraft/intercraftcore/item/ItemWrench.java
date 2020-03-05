package net.intercraft.intercraftcore.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemWrench extends Item
{
    public ItemWrench()
    {
        super(new Item.Properties().group(ItemGroup.TOOLS));

        setRegistryName("wrench");
    }
}