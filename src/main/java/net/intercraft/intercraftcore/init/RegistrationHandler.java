package net.intercraft.intercraftcore.init;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.stats.StatType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class RegistrationHandler
{

    protected static final List<EntityType>     entities       = new LinkedList<>();
    protected static final List<Block>          blocks         = new LinkedList<>();
    protected static final List<Block>          fluidBlocks    = new LinkedList<>();
    protected static final List<Item>           itemBlocks     = new LinkedList<>();
    protected static final List<Item>           items          = new LinkedList<>();
    protected static final List<ContainerType>  containerTypes = new LinkedList<>();
    protected static final List<Effect>         effects        = new LinkedList<>();
    protected static final List<TileEntityType> tileentities   = new LinkedList<>();
    protected static final List<Enchantment>    enchantments   = new LinkedList<>();

    protected static final List<StatType<?>>    stats          = new LinkedList<>();
    //protected static final List<ParticleType<?>> particles     = new LinkedList<>();


    public static void register(final RegistryEvent.Register event)
    {
        Type generic = event.getGenericType();

        if (generic == EntityType.class) {
            registerEntities(event);
        } else if (generic == Block.class) {
            registerBlocks(event);
        } else if (generic == Item.class) {
            registerItems(event);
        } else if (generic == Effect.class) {
            registerPotions(event);
        } else if (generic == TileEntityType.class) {
            registerTileEntities(event);
        } else if (generic == Enchantment.class) {
            registerEnchantments(event);
        } else if (generic == ContainerType.class) {
            registerContainerTypes(event);
        } else if (generic == StatType.class) {
            //registerStats(event);
        } else if (generic == ParticleType.class) {
            //registerParticles(event);
        }


    }

    protected static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        IntercraftEntities.register();
        entities.forEach(entityType -> event.getRegistry().register(entityType));
        System.out.println("Entity registration done.");
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

    protected static void registerEnchantments(final RegistryEvent.Register<Enchantment> event)
    {
        IntercraftEnchantments.register();
        enchantments.forEach(enchantment -> event.getRegistry().register(enchantment));
        System.out.println("Enchantment registration done.");
    }

    protected static void registerContainerTypes(final RegistryEvent.Register<ContainerType<?>> event)
    {
        IntercraftContainerTypes.register();
        containerTypes.forEach(containerType -> event.getRegistry().register(containerType));
        System.out.println("Container registration done.");
    }

    protected static void registerStats(final RegistryEvent.Register<StatType<?>> event)
    {
        IntercraftStats.register();
        stats.forEach(stat -> event.getRegistry().register(stat));
        System.out.println("Stats registration done.");
    }

    protected static void registerParticles(final RegistryEvent.Register<ParticleType<?>> event)
    {
        /*IntercraftParticles.register();
        particles.forEach(particle -> event.getRegistry().register(particle));
        System.out.println("Particle registration done.");*/
    }
}