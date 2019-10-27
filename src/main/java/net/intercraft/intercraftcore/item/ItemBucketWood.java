package net.intercraft.intercraftcore.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemBucketWood extends BucketItem
{

    private final Item bucketEmpty;

    public ItemBucketWood(String type)
    {
        super(() -> Fluids.EMPTY, new Item.Properties().group(ItemGroup.MISC).maxStackSize(16));
        setRegistryName(type+"_bucket");
        this.bucketEmpty = this;
    }

    public ItemBucketWood(FlowingFluid fluid, String type, Item empty)
    {
        super(() -> fluid, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
        setRegistryName(fluid.getRegistryName().getPath()+"_"+type+"_bucket");
        this.bucketEmpty = empty;
    }


    @Override
    protected ItemStack emptyBucket(ItemStack stack, PlayerEntity playerEntity)
    {
        return playerEntity.abilities.isCreativeMode ? new ItemStack(bucketEmpty) : stack;
    }
}
