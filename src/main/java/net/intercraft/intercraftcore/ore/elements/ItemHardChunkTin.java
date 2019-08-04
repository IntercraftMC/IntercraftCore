package net.intercraft.intercraftcore.ore.elements;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.ore.ElementComposition;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.minecraft.item.Item;

public class ItemHardChunkTin extends ItemHardChunk
{
    public ItemHardChunkTin()
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES),"sn",0xb8b8b8,new ElementComposition[] {
                new ElementComposition("sn",1)
        });
    }
}
