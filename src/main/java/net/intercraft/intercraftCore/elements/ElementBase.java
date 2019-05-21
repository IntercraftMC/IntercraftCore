package net.intercraft.intercraftCore.elements;

import net.intercraft.intercraftCore.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ElementBase {
    /*
    * Should register an ore (hard), dust (+ tiny), ingot (+ nugget), block, plate, frame.
    * Is going to be a extension of this class to make code shorter.
    * */
    private String name,oredict;
    private int tint;

    public ElementBase(String name, String oredict, int tint) {
        super();
        this.name = name;
        this.oredict = oredict;
        this.tint = tint;

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

    }
}