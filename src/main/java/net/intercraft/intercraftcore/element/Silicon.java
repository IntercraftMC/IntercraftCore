package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;

public class Silicon extends Element
{
    public Silicon()
    {
        super("silicon", ElementDictionary.SILICON, INGOT | NUGGET | PLATE | GEAR | CHUNK | FRAME | ORE);
    }
}
