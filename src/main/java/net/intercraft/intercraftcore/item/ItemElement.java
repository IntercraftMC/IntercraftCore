package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.item.Item;


public class ItemElement extends Item
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

    public int getTint() {
        return element.tintPrim;
    }
}