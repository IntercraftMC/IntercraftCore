package net.intercraft.intercraftcore.item.elements;

import net.intercraft.intercraftcore.init.IntercraftCoreRegistry;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class ElementItemBase extends Item implements IItemColor {

    protected int tint;
    /*
    * Should register an dust (+ tiny), ingot (+ nugget), plate.
    * Is going to be a extension of this class to make code shorter.
    * */

    public ElementItemBase(String name, String oredict, int tint) {
        super(new Item.Properties().group(IntercraftCoreRegistry.RESOURCES));

        this.tint = tint;
        setRegistryName(name+"_ingot");




    }

    @Override
    public int getColor(ItemStack itemStack, int tint) {

        return Color.GREEN.getRGB();

    }
}