package net.intercraft.intercraftcore.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBucketNonFluid extends Item
{
    private final int tint;

    public ItemBucketNonFluid(String name, int tint)
    {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));

        this.tint = tint;
        setRegistryName(name+"_bucket");
    }

    public ItemBucketNonFluid(String name,String type, int tint)
    {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));

        this.tint = tint;
        setRegistryName(name+"_"+type+"_bucket");
    }

    public int getTint()
    {
        return tint;
    }
}