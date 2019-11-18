package net.intercraft.intercraftcore.item.group;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.item.ItemUranium;
import net.minecraft.item.Item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemUraniumGroup extends ItemElementGroup
{
    public ItemUraniumGroup(Element element)
    {
        super(element);
    }

    @Override
    protected Item createItem(int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor;

                switch (form) {

                    case Element.CHUNK:
                        constructor = createConstructor(itemHardChunkClass);
                        break;

                    default:
                        constructor = createConstructor(ItemUranium.class);
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
}
