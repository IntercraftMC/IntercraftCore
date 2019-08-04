package net.intercraft.intercraftcore.ore.elements;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.ore.ElementComposition;
import net.intercraft.intercraftcore.ore.ItemHardChunk;

public class ItemHardChunkLead extends ItemHardChunk
{
    public ItemHardChunkLead()
    {
        super(new Properties().group(IntercraftItemGroups.RESOURCES),"pb",0x534368,new ElementComposition[] {
                new ElementComposition("pb",0.85),
                new ElementComposition("ag",0.15)
        });
    }
}
