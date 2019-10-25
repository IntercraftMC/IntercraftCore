package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;

public class Carbon extends Element
{
    public Carbon()
    {
        super("carbon", ElementDictionary.CARBON, PLATE | GEAR | CHUNK | FRAME | ORE);
    }
}
