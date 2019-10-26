package net.intercraft.intercraftcore.api.sorters;

import net.minecraft.item.ItemStack;

import java.util.Comparator;

public class AlphabetSorter implements Comparator
{
    @Override
    public int compare(Object o1, Object o2)
    {

        String stack1 = ((ItemStack) o1).getTranslationKey();
        String stack2 = ((ItemStack) o2).getTranslationKey();

        return stack1.compareTo(stack2);
    }
}
