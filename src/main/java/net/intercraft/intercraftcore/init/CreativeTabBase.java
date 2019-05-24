package net.intercraft.intercraftcore.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabBase extends ItemGroup {
    public CreativeTabBase(String label) {
        super(label);
    }


    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.COPPER);
    }
}