package net.intercraft.intercraftcore.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBucketResin extends Item
{
    public ItemBucketResin()
    {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));

        setRegistryName("resin_bucket");
    }

    public ItemBucketResin(String type)
    {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));

        setRegistryName("resin_"+type+"_bucket");
    }
}
