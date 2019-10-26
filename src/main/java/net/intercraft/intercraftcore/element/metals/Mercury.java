package net.intercraft.intercraftcore.element.metals;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.element.Element;

public class Mercury extends Element
{
    public Mercury()
    {
        super("mercury", ElementDictionary.MERCURY, INGOT | NUGGET | DUST | DUST_SMALL | PLATE | GEAR | ROD | CHUNK | BLOCK | FRAME | ORE);
    }
}
