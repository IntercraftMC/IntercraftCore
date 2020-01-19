package net.intercraft.intercraftcore.wire;

import net.intercraft.intercraftcore.ElementDictionary;

public class WireElectrum12mm extends Wire
{
    public WireElectrum12mm()
    {
        super("electrum", 0.65f, ElementDictionary.ELECTRUM.getColorPrimary(),(short) 12);
    }
}
