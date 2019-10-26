package net.intercraft.intercraftcore.element.nonmetals;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.element.Element;

public class Carbon extends Element
{
    public Carbon()
    {
        super("carbon", ElementDictionary.CARBON, PLATE | GEAR | CHUNK | FRAME | ORE);
    }
}
