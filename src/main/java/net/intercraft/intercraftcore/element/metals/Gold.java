package net.intercraft.intercraftcore.element.metals;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.element.Element;

public class Gold extends Element
{
    public Gold()
    {
        super("gold", ElementDictionary.GOLD, INGOT | NUGGET | BLOCK);
    }
}