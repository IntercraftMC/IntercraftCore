package net.intercraft.intercraftcore.item.element;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static Item[] create(final Item item,final boolean[] types) {
        List<Item> items = new ArrayList<>();
        for (int i=0;i<types.length;i++) {
            if (types[i]) {
                Item newItem = item;
                switch (i) {
                    case 0:
                        newItem.setRegistryName(item.getRegistryName()+"_ingot");
                        break;
                    case 1:
                        newItem.setRegistryName(item.getRegistryName()+"_nugget");
                        break;
                    case 2:
                        newItem.setRegistryName(item.getRegistryName()+"_dust");
                        break;
                    case 3:
                        newItem.setRegistryName(item.getRegistryName()+"_tinydust");
                        break;
                    case 4:
                        newItem.setRegistryName(item.getRegistryName()+"_plate");
                        break;
                    default:
                        break;
                }
                items.add(newItem);
            }
        }
        return items.toArray(new Item[items.size()]);
    }
}
