package net.intercraft.intercraftcore.item;

import net.minecraft.item.Item;

public class ItemTest extends Item {

    public ItemTest(Item.Properties builder) {
        super(builder);
        setRegistryName("test_item");
    }
}
