package net.intercraft.intercraftcore.ore.elements;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.ore.ElementComposition;
import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.ore.ItemHardChunk;

public class ItemHardChunkLead extends ItemHardChunk
{
    public ItemHardChunkLead()
    {
        super(new Properties().group(IntercraftItemGroups.RESOURCES),ElementDictionary.LEAD.getSymbol(),ElementDictionary.LEAD.getColorSecondary(),new ElementComposition[] {
                new ElementComposition(ElementDictionary.LEAD,0.85),
                new ElementComposition(ElementDictionary.SILVER,0.15)
        });
    }
}
