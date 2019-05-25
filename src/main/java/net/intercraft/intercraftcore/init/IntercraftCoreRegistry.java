package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.elements.*;
import net.intercraft.intercraftcore.init.groups.ItemGroupResources;
import net.intercraft.intercraftcore.item.ItemTest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class IntercraftCoreRegistry {

    /*
    * Items
    * */
    public static final Item
    ITEM_TEST,

    COPPER,
    LEAD,
    SILVER,
    TIN,
    TUNGSTEN,
    URANIUM;

    /*
    * Potion effects
    * */
    public static final Potion
    RADIATION;

    public static final ItemGroup
    RESOURCES;



    protected static final List<Item> items;
    protected static final List<Potion> potions;
    protected static final List<ItemGroup> groups;

    static {

        groups = Arrays.asList(
                RESOURCES = new ItemGroupResources("resources")
        );

        items = Arrays.asList(

            ITEM_TEST = new ItemTest((new Item.Properties()).group(ItemGroup.REDSTONE)),

                COPPER = new Copper(),
                LEAD = new Lead(),
                SILVER = new Silver(),
                TIN = new Tin(),
                TUNGSTEN = new Tungsten(),
                URANIUM = new Uranium()
        );

        potions = Arrays.asList(
                RADIATION = new PotionRadiationSickness(true,1)
        );


    }


    public static void register(final RegistryEvent.Register event) {
        Type generic = event.getGenericType();

        if (generic == Item.class) {
            event.getRegistry().registerAll((Item[]) items.toArray());
        } else if (generic == Potion.class) {
            event.getRegistry().registerAll((Potion[]) potions.toArray());
        }

    }
}
