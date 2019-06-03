package net.intercraft.intercraftcore.item.group;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.item.ItemUranium;
import net.minecraft.item.Item;

public class ItemUraniumGroup extends ItemElementGroup
{
    public ItemUraniumGroup(Element element)
    {
        super(element);
    }

    @Override
    protected Item createItem(byte form, String suffix)
    {
        return new ItemUranium(element, suffix);
    }
}
