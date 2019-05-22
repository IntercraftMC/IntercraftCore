package net.intercraft.intercraftcore.elements;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ElementBase extends Item {
    /*
    * Should register an ore (hard), dust (+ tiny), ingot (+ nugget), block, plate, frame.
    * Is going to be a extension of this class to make code shorter.
    * */

    public ElementBase(String name, String oredict, int tint) {
        super(new Item.Properties().group(ItemGroup.REDSTONE));

        setRegistryName(name);


    }
}