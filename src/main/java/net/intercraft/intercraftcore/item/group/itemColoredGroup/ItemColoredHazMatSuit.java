package net.intercraft.intercraftcore.item.group.itemColoredGroup;

import net.intercraft.intercraftcore.item.ItemHazMatSuit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.lang.reflect.Constructor;

public class ItemColoredHazMatSuit extends ItemColoredGroup<ItemHazMatSuit>
{
    public ItemColoredHazMatSuit(String slot,EquipmentSlotType slotType,float[] protectionDivision, Item.Properties properties)
    {
        super("%s_hazmat_"+slot, slotType, protectionDivision, properties);
    }

    @Override
    protected Constructor<ItemHazMatSuit> createConstructor() throws NoSuchMethodException
    {
        return ItemHazMatSuit.class.getConstructor(String.class,EquipmentSlotType.class,float[].class,Item.Properties.class,int.class);
    }
}