package net.intercraft.intercraftcore.item;

import net.minecraft.item.Item;

public class ItemGeneric extends Item
{
    public ItemGeneric(String name, Item.Properties properties)
    {
        super(properties);
        setRegistryName(name);
    }
}
