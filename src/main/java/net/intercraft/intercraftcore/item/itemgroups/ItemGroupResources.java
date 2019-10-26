package net.intercraft.intercraftcore.item.itemgroups;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.sorters.Sorters;
import net.intercraft.intercraftcore.init.IntercraftItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemGroupResources extends ItemGroup
{

    public ItemGroupResources()
    {
        super(Reference.MODID + ".resources");
    }

    @Override
    public void fill(NonNullList<ItemStack> items)
    {

        super.fill(items);

        items.sort(Sorters.alphabetSorter);
        items.sort(Sorters.typeSorter);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(IntercraftItems.COPPER.INGOT);
    }
}
