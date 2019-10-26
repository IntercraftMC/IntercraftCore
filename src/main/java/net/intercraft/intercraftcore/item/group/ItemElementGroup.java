package net.intercraft.intercraftcore.item.group;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.item.ItemElement;
import net.intercraft.intercraftcore.item.ItemHardChunk;
import net.minecraft.item.Item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemElementGroup
{
    /**
     * The generic item type
     */
    protected final Class<ItemElement> itemElementClass;
    protected final Class<ItemHardChunk> itemHardChunkClass;

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
    public final Item GEAR;
    public final Item ROD;
    public final Item CHUNK;

    /**
     * Create an item group for the given element
     *
     * @param element
     */
    public ItemElementGroup(Element element)
    {
        this.element = element;
        this.itemElementClass = ItemElement.class;
        this.itemHardChunkClass = ItemHardChunk.class;


        INGOT = createItem(Element.INGOT, "ingot");
        NUGGET = createItem(Element.NUGGET, "nugget");
        DUST = createItem(Element.DUST, "dust");
        DUST_SMALL = createItem(Element.DUST_SMALL, "dustsmall");
        PLATE = createItem(Element.PLATE, "plate");
        GEAR = createItem(Element.GEAR, "gear");
        ROD = createItem(Element.ROD, "rod");
        CHUNK = createItem(Element.CHUNK, "chunk");
    }

    /**
     * Create an element item form
     *
     * @param form
     * @param suffix
     */
    protected Item createItem(int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor;// = itemElementClass.getConstructor(Element.class, String.class);

                switch (form) {

                    case Element.CHUNK:
                        constructor = createConstructor(itemHardChunkClass);
                        break;

                    default:
                        constructor = createConstructor(itemElementClass);
                }

                return (Item) constructor.newInstance(new Object[] { element, suffix });
            }
            catch (InstantiationException e) {
                printError(e);
            }
            catch (IllegalAccessException e) {
                printError(e);
            }
            catch (InvocationTargetException e) {
                printError(e);
            }
        }
        return null;
    }

    protected Constructor<?> createConstructor(Class<?> clazz)
    {

        try {
            return clazz.getConstructor(Element.class, String.class);
        }
        catch (NoSuchMethodException e) {
            printError(e);
        }

        return null;
    }

    protected void printError(Exception e)
    {
        System.out.println(String.format("[%s]: {%s}", e.getClass().getSimpleName(), e));

    }


}
