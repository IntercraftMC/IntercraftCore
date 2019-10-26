package net.intercraft.intercraftcore.element.nonmetals;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.element.Element;

public class Silicon extends Element
{
    public Silicon()
    {
        super("silicon", ElementDictionary.SILICON, INGOT | NUGGET | PLATE | GEAR | CHUNK | FRAME | ORE);
    }
}