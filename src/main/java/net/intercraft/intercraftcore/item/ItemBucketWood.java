package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.api.Util;
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
    //private final int tint = Util.bleach(FluidType.WATER.getTint(),0.4f);

    public ItemBucketWood(String type)
    {
        super(() -> Fluids.EMPTY, new Item.Properties().group(ItemGroup.MISC).maxStackSize(16));
        setRegistryName(type+"_bucket");
        this.bucketEmpty = null;
        /*float[] hsv = Util.rgb2hsv(Util.hex2rgb(FluidType.WATER.getTint()));
        //hsv[1] += bleachAmount;

        tint = Util.rgb2hex(Util.hsv2rgb(hsv));*/

    }

    public ItemBucketWood(FlowingFluid fluid, String type, Item empty)
    {
        super(() -> fluid, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
        setRegistryName(fluid.getRegistryName().getPath()+"_"+type+"_bucket");
        this.bucketEmpty = empty;
        /*float[] hsv = Util.rgb2hsv(Util.hex2rgb(FluidType.WATER.getTint()));
        //hsv[1] += bleachAmount;

        tint = Util.rgb2hex(Util.hsv2rgb(hsv));*/
    }


    public int getTint()
    {
        return FluidType.WATER.getTint();
    }



    @Override
    protected ItemStack emptyBucket(ItemStack stack, PlayerEntity playerEntity)
    {
        return playerEntity.isCreative() ? stack : bucketEmpty != null ? new ItemStack(bucketEmpty) : stack;
    }
}
