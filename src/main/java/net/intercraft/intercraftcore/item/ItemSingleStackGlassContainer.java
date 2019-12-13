package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.init.IntercraftItems;
import net.intercraft.intercraftcore.init.capabilities.fluid_container.FluidContainerStorage;
import net.intercraft.intercraftcore.init.capabilities.fluid_container.IFluidContainer;
import net.intercraft.intercraftcore.init.capabilities.stackContainer.StackFluidContainerProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSingleStackGlassContainer extends ItemSingleStackContainer
{

    public static final short maxVolume = 1000;

    public ItemSingleStackGlassContainer(Item.Properties properties,String name, float isolation, int tint)
    {
        super(properties,name,isolation,tint);
    }

    public ItemSingleStackGlassContainer(Item.Properties properties, String name, float isolation)
    {
        this(properties,name,isolation,-1);
    }

    @Override
    protected void openContainer(PlayerEntity playerIn)
    {
        super.openContainer(playerIn);
    }

    /*@Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        //if (FluidContainerStorage.FLUID_CONTAINER_CAPABILITY == null || CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == null) return null;
        return new StackFluidContainerProvider((short) 1,maxVolume);
    }


    public static IFluidContainer getFluidContainer(ItemStack stack) // Does not work as @initCapabilities is called before capabilities are registered.
    {
        return stack.getCapability(FluidContainerStorage.FLUID_CONTAINER_CAPABILITY).orElseThrow(NullPointerException::new);
    }*/



    /*@Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        //if (FluidContainerStorage.FLUID_CONTAINER_CAPABILITY == null || CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == null) return null;
        return new StackFluidContainerProvider((short) 1,maxVolume,stack);
    }

    public static IFluidHandler getFluidContainer(ItemStack stack) // Does not work as @initCapabilities is called before capabilities are registered.
    {
        //return stack.getCapability(FluidContainerStorage.FLUID_CONTAINER_CAPABILITY).orElseThrow(NullPointerException::new);
        return stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        //tooltip.add(new StringTextComponent(getFluidContainer(stack).getFluidInTank(0).getTranslationKey()));
        //tooltip.add(new StringTextComponent(getFluidContainer(stack).getFluid().getName()));
    }*/

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