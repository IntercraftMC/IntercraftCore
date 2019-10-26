package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBlockHardOre extends BlockItem
{
    public ItemBlockHardOre(Block block, Item.Properties properties)
    {
        super(block,properties.group(IntercraftItemGroups.RESOURCES));

        addPropertyOverride(new ResourceLocation("intercraftcore:density"),(itemStack, worldIn, entityLivingBase) -> {

            try {
                return itemStack.getTag().getInt("intercraftcore:density");
            } catch (NullPointerException e) {
                return IntercraftCore.defDensity;
            }
        });
    }


    @Override
    public String getTranslationKey(ItemStack stack)
    {
        try {
            int d = stack.getTag().getInt("intercraftcore:density");
            return super.getTranslationKey()+"."+d;

        } catch (NullPointerException e) {
            return super.getTranslationKey();
        }
    }


}
