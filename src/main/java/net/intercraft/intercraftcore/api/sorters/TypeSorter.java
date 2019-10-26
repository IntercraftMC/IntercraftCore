package net.intercraft.intercraftcore.api.sorters;

import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Comparator;

public class TypeSorter implements Comparator
{

    private final String[] bottom = new String[] {
            "block",
            "frame",
            "ore"
    };

    @Override
    public int compare(Object o1, Object o2)
    {

        String stack1 = ((ItemStack) o1).getTranslationKey();
        String stack2 = ((ItemStack) o2).getTranslationKey();




        String type1 = stack1.substring(stack1.indexOf('_')+1);
        String type2 = stack2.substring(stack2.indexOf('_')+1);


        if (Arrays.stream(bottom).anyMatch(type1::equals) && !(Arrays.stream(bottom).anyMatch(type2::equals))) {
            return 1;
        } else if (!(Arrays.stream(bottom).anyMatch(type1::equals)) && Arrays.stream(bottom).anyMatch(type2::equals)) {
            return -1;
        }

        return type1.compareTo(type2);
    }
}
