package net.intercraft.intercraftcore.ore.elements;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.ore.ElementComposition;
import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.minecraft.item.Item;

public class ItemHardChunkTin extends ItemHardChunk
{
    public ItemHardChunkTin()
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES),ElementDictionary.TIN.getSymbol(),ElementDictionary.TIN.getColorSecondary(),new ElementComposition[] {
                new ElementComposition(ElementDictionary.TIN,1)
        });
    }
}
