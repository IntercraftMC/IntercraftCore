package net.intercraft.intercraftcore.element.metals;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.element.Element;

public class Iron extends Element
{
    public Iron()
    {
        super("iron", ElementDictionary.IRON, INGOT | NUGGET | BLOCK);
    }
}