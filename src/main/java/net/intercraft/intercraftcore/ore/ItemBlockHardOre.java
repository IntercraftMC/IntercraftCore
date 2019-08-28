package net.intercraft.intercraftcore.ore;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class ItemBlockHardOre extends BlockItem
{
    public ItemBlockHardOre(Block block, Item.Properties properties)
    {
        super(block,properties.group(IntercraftItemGroups.RESOURCES));


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
