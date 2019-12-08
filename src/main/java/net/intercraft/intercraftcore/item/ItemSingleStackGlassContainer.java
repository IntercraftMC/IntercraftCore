package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.init.IntercraftItems;
import net.intercraft.intercraftcore.tileentity.SingleStackGlassContainerItemRender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemSingleStackGlassContainer extends ItemSingleStackContainer
{
    public ItemSingleStackGlassContainer(String name, float isolation, int tint)
    {
        super(new Item.Properties()/*.setTEISR(() -> () -> SingleStackGlassContainerItemRender.INSTANCE)*/,name,isolation,tint);
    }

    public ItemSingleStackGlassContainer(String name, float isolation)
    {
        this(name,isolation,-1);
    }

    @Override
    protected void openContainer(PlayerEntity playerIn)
    {
        super.openContainer(playerIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return super.initCapabilities(stack, nbt);
    }

    @Override
    public boolean hasFluid() // Will render a fluid later.
    {
        return true;
    }

    private enum AllowedBuckets
    {

        WATER("water",FluidType.WATER.getTint(),Items.WATER_BUCKET,
                IntercraftItems.WATER_BUCKET_OAK, IntercraftItems.WATER_BUCKET_SPRUCE,
                IntercraftItems.WATER_BUCKET_BIRCH, IntercraftItems.WATER_BUCKET_JUNGLE,
                IntercraftItems.WATER_BUCKET_ACACIA, IntercraftItems.WATER_BUCKET_DARK_OAK),

        MILK("milk",FluidType.MILK.getTint(),Items.MILK_BUCKET),

        RESIN("resin",FluidType.RESIN.getTint(),IntercraftItems.RESIN_BUCKET,
                IntercraftItems.RESIN_BUCKET_OAK, IntercraftItems.RESIN_BUCKET_SPRUCE,
                IntercraftItems.RESIN_BUCKET_BIRCH, IntercraftItems.RESIN_BUCKET_JUNGLE,
                IntercraftItems.RESIN_BUCKET_ACACIA, IntercraftItems.RESIN_BUCKET_DARK_OAK),

        RUBBER_RESIN("rubber_resin",FluidType.RUBBER_RESIN.getTint(),IntercraftItems.RUBBER_RESIN_BUCKET,
                IntercraftItems.RUBBER_RESIN_BUCKET_OAK, IntercraftItems.RUBBER_RESIN_BUCKET_SPRUCE,
                IntercraftItems.RUBBER_RESIN_BUCKET_BIRCH, IntercraftItems.RUBBER_RESIN_BUCKET_JUNGLE,
                IntercraftItems.RUBBER_RESIN_BUCKET_ACACIA, IntercraftItems.RUBBER_RESIN_BUCKET_DARK_OAK);

        private final Item[] buckets;
        private final String name;
        private final int tint;

        AllowedBuckets(String name,int tint,Item... buckets)
        {
            this.name = name;
            this.buckets = buckets;
            this.tint = tint;
        }

        public String getName()
        {
            return name;
        }

        public int getTint()
        {
            return tint;
        }

        public Item[] getBuckets()
        {
            return buckets;
        }

        public Item getBucket(int index)
        {
            return buckets[index];
        }
    }
}