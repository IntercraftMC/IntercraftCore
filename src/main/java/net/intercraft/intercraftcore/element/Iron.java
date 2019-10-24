package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;

public class Iron extends Element
{
    public Iron()
    {
        super("iron", ElementDictionary.IRON.getSymbol(), ElementDictionary.IRON.getColorPrimary(),INGOT, NUGGET, BLOCK);
    }
}