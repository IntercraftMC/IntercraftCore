package net.intercraft.intercraftcore.elements;

import net.intercraft.intercraftcore.IntercraftCore;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class ElementBase extends Item implements IItemColor {

    protected int tint;
    /*
    * Should register an ore (hard), dust (+ tiny), ingot (+ nugget), block, plate, frame.
    * Is going to be a extension of this class to make code shorter.
    * */

    public ElementBase(String name, String oredict, int tint) {
        super(new Item.Properties().group(IntercraftCore.RESOURCES));

        this.tint = tint;
        setRegistryName(name+"_ingot");




    }

    @Override
    public int getColor(ItemStack itemStack, int tint) {

        return Color.GREEN.getRGB();

    }
}