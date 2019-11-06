package net.intercraft.intercraftcore.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.stats.StatType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class RegistrationHandler
{

    protected static final List<Block>          blocks = new LinkedList<>();
    protected static final List<Block>          fluidBlocks = new LinkedList<>();
    protected static final List<Item>           itemBlocks = new LinkedList<>();
    protected static final List<Item>           items = new LinkedList<>();
    protected static final List<Effect>         effects = new LinkedList<>();
    protected static final List<TileEntityType> tileentities = new LinkedList<>();
    protected static final List<StatType<?>>    stats = new LinkedList<>();


    public static void register(final RegistryEvent.Register event)
    {
        Type generic = event.getGenericType();

        if (generic == Block.class) {
            registerBlocks(event);
        } else if (generic == Item.class) {
            registerItems(event);
        } else if (generic == Effect.class) {
            registerPotions(event);
        } else if (generic == TileEntityType.class) {
            registerTileEntities(event);
        } else if (generic == StatType.class) {
            registerStats(event);
        }


    }


    protected static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        IntercraftBlocks.register();
        blocks.forEach(block -> event.getRegistry().register(block));
        System.out.println("Block registration done.");
        fluidBlocks.forEach(fluidBlock -> event.getRegistry().register(fluidBlock));
        System.out.println("Fluid registration done.");
    }

    protected static void registerItems(final RegistryEvent.Register<Item> event)
    {
        IntercraftItems.register();
        items.forEach(item -> event.getRegistry().register(item));
        itemBlocks.forEach(block -> event.getRegistry().register(block));
        System.out.println("Item registration done.");
    }

    protected static void registerPotions(final RegistryEvent.Register<Effect> event)
    {
        IntercraftPotions.register();
        effects.forEach(potion -> event.getRegistry().register(potion));
        System.out.println("Potion registration done.");
    }

    protected static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        IntercraftTileEntities.register();
        tileentities.forEach(tileEntityType -> event.getRegistry().register(tileEntityType));
        System.out.println("TileEntity registration done.");
    }

    protected static void registerStats(final  RegistryEvent.Register<StatType<?>> event)
    {
        IntercraftStats.register();
        stats.forEach(statType -> event.getRegistry().register(statType));
        System.out.println("Stats registration done.");
    }
}