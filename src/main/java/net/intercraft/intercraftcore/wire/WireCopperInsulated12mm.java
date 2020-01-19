package net.intercraft.intercraftcore.wire;

import net.intercraft.intercraftcore.ElementDictionary;

public class WireCopperInsulated12mm extends Wire
{
    public WireCopperInsulated12mm()
    {
        super("copper", 0.38f, ElementDictionary.COPPER.getColorPrimary(),(short) 12, true);
    }
}
