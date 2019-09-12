package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;

public class Gold extends Element
{
    public Gold()
    {
        super("gold", ElementDictionary.GOLD.getSymbol(), ElementDictionary.GOLD.getColorPrimary(),INGOT, NUGGET);
    }
}