package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ItemElement extends Item implements IItemColor
{
    protected Element element;

    /**
     * A generic Element Item
     * @param element
     */
    public ItemElement(Element element, String registrySuffix)
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES));
        this.element = element;
        setRegistryName(element.symbol + "_" + registrySuffix);
    }


    public int getColor(ItemStack stack, int tint) {
        return element.tint;
    }
}