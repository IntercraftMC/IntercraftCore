package net.intercraft.intercraftcore.ore.elements;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.ore.BlockHardOre;

public class BlockOreCopper extends BlockHardOre
{
    public BlockOreCopper()
    {
        super(ElementDictionary.COPPER.getSymbol(),1.5F,6.0F,ElementDictionary.COPPER.getColorSecondary());
    }
}
