package net.intercraft.intercraftcore.item.group;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.item.ItemLithium;
import net.minecraft.item.Item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemLithiumGroup extends ItemElementGroup
{
    public ItemLithiumGroup(Element element)
    {
        super(element);
    }

    @Override
    protected Item createItem(int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor = createConstructor(ItemLithium.class);

                return (Item) constructor.newInstance(new Object[] { element, suffix });
            }
            catch (InstantiationException e) {
                printError(e);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                printError(e);
            }
        }
        return null;
    }
}
