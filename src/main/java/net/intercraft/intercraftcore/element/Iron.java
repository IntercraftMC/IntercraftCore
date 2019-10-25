package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;

public class Iron extends Element
{
    public Iron()
    {
        super("iron", ElementDictionary.IRON, INGOT | NUGGET | BLOCK);
    }
}