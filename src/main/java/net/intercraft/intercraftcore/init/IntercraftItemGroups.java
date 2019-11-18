package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.item.itemgroups.ItemGroupResources;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class IntercraftItemGroups
{
    /**
     * Resources tab
     */
    public static final ItemGroup RESOURCES = new ItemGroupResources();
    /**
    * Wiring tab
    */
    public static final ItemGroup WIRING = new ItemGroup(Reference.MODID + ".wiring")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(IntercraftItems.COPPER_COIL);
        }
    };

    /**
    * Machines Tab
    */

    public static final ItemGroup MACHINE = new ItemGroup(Reference.MODID + ".machine")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(IntercraftBlocks.CHUNKLOADER);
        }
    };

    /**
    * Vanity Tab
    */

    public static final ItemGroup VANITY = new ItemGroup(Reference.MODID + ".vanity")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(IntercraftItems.DEVILMASK);
        }
    };
}
