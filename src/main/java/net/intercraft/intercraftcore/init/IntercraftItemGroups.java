package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class IntercraftItemGroups
{
    /**
     * Resources tab
     */
    public static final ItemGroup RESOURCES = new ItemGroup(Reference.MODID + ".resources")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(IntercraftItems.COPPER.INGOT);
        }
    };
    /*
    * Wiring tab
    * */
    public static final ItemGroup WIRING = new ItemGroup(Reference.MODID + ".wiring")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(Items.BEEF);
        }
    };
}
