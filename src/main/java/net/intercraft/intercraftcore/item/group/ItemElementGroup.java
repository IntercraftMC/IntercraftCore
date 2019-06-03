package net.intercraft.intercraftcore.item.group;

import net.intercraft.intercraftcore.element.Element;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemElementGroup
{
    public final Element element;

    /**
     * Element Item Forms
     */
    public final Item INGOT;
    public final Item NUGGET;
    public final Item DUST;
    public final Item DUST_SMALL;
    public final Item PLATE;

    /**
     * Create an item group for the given element
     *
     * @param element
     */
    public ItemElementGroup(Element element)
    {
        this.element = element;
        INGOT = hasForm(element, Element.INGOT) ? new ItemElement(element, "ingot") : null;
        NUGGET = hasForm(element, Element.INGOT) ? new ItemElement(element, "nugget") : null;
        DUST = hasForm(element, Element.INGOT) ? new ItemElement(element, "dust") : null;
        DUST_SMALL = hasForm(element, Element.INGOT) ? new ItemElement(element, "dustsmall") : null;
        PLATE = hasForm(element, Element.INGOT) ? new ItemElement(element, "plate") : null;
    }

    /**
     * Create an element item form
     *
     * @param element
     * @param form
     */
    protected boolean hasForm(Element element, byte form)
    {
        return (element.forms & form) == form;
    }
}
