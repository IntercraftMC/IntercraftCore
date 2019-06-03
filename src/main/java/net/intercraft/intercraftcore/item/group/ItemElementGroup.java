package net.intercraft.intercraftcore.item.group;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.item.ItemElement;
import net.minecraft.item.Item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemElementGroup
{
    /**
     * The generic item type
     */
    protected final Class<ItemElement> itemElementClass;

    /**
     * Keep a reference to the element
     */
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
        this.itemElementClass = ItemElement.class;
        INGOT = createItem(Element.INGOT, "ingot");
        NUGGET = createItem(Element.NUGGET, "nugget");
        DUST = createItem(Element.DUST, "dust");
        DUST_SMALL = createItem(Element.DUST_SMALL, "dustsmall");
        PLATE = createItem(Element.PLATE, "plate");
    }

    /**
     * Create an element item form
     *
     * @param form
     * @param suffix
     */
    protected Item createItem(byte form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor = itemElementClass.getConstructor(Element.class, String.class);
                return (Item) constructor.newInstance(new Object[] { element, suffix });
            }
            catch (NoSuchMethodException e) {}
            catch (InstantiationException e) {}
            catch (IllegalAccessException e) {}
            catch (InvocationTargetException e) {}
        }
        return null;
    }
}
