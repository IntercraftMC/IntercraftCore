package net.intercraft.intercraftcore.wire;

import net.intercraft.intercraftcore.ElementDictionary;

public class WireElectrumInsulated12mm extends Wire
{
    public WireElectrumInsulated12mm()
    {
        super("electrum", 0.68f, ElementDictionary.ELECTRUM.getColorPrimary(),(short) 12, true);
    }
}
