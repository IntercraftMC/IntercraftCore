package net.intercraft.intercraftcore.init.groups;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftCoreRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupResources extends ItemGroup {


    public ItemGroupResources(String label) {
        super(Reference.MODID +"."+label);

    }


    public ItemStack createIcon() {
        return new ItemStack(IntercraftCoreRegistry.COPPER);
    }
}