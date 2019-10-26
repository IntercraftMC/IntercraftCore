package net.intercraft.intercraftcore.element.alloys;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.element.Element;

public class Steel extends Element
{
    public Steel()
    {
        super("steel", ElementDictionary.STEEL, CHUNK | ORE);
    }
}