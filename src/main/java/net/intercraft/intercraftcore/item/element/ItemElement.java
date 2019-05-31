package net.intercraft.intercraftcore.item.element;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;

/*
 * @TODO
 * Should register an dust (+ tiny), ingot (+ nugget), plate.
 * Is going to be a extension of this class to make code shorter.
 */
public class ItemElement extends Item
{
    private int tint;

    public ItemElement(String name, String oredict, int tint)
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES));

        this.tint = tint;
        setRegistryName(name);
    }

    public int getColor()
    {
        return tint;
    }
}