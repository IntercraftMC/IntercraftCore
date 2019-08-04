package net.intercraft.intercraftcore.ore.elements;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.ore.ElementComposition;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.minecraft.item.Item;

public class ItemHardChunkCopper extends ItemHardChunk
{
    public ItemHardChunkCopper()
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES),"cu",0x57b988,new ElementComposition[] {
                new ElementComposition("cu",0.95),
                new ElementComposition("au",0.05)
        });
    }
}
