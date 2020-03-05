package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.item.Item;


public class ItemElement extends Item
{
    protected final Element element;
    private final String suffix;

    /**
     * A generic Element Item
     *
     * @param element The element type.
     * @param registrySuffix The type of item.
     */
    public ItemElement(Element element, String registrySuffix)
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES));
        this.element = element;
        suffix = registrySuffix;
        setRegistryName(element.symbol + "_" + registrySuffix);
    }

    public String getSuffix()
    {
        return suffix;
    }

    public int getTint() {
        return element.tintPrim;
    }
}