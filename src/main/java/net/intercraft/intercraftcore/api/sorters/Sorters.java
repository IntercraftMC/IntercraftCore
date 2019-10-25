package net.intercraft.intercraftcore.api.sorters;

import net.minecraft.item.ItemStack;

import java.util.Comparator;

public class Sorters
{
    public static Comparator<ItemStack> alphabetSorter = new AlphabetSorter();
}
