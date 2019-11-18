package net.intercraft.intercraftcore.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBucketNonFluid extends Item
{
    public ItemBucketNonFluid(String name)
    {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));

        setRegistryName(name+"_bucket");
    }

    public ItemBucketNonFluid(String name,String type)
    {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));

        setRegistryName(name+"_"+type+"_bucket");
    }
}