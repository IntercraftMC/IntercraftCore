package net.intercraft.intercraftcore.item.group;

import net.minecraft.item.Item;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class ItemColoredGroup<T extends Item>
{
    public final T WHITE, ORANGE, MAGENTA, LIGHT_BLUE, YELLOW, LIME, PINK, GRAY, LIGHT_GRAY, CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK;

    /**
     * ItemColoredGroup Constructor
     * Create 16 coloured variants of T with a String in the beginning and int at the end.
     *
     * @param name Generic name of the items. Needs %s to be replaced with.
     * @param param Additional parameters for the item.
     */
    public ItemColoredGroup(String name,Object...param)
    {
        if (!name.contains("%s")) throw new IllegalArgumentException("Needs \"%s\" in the name!");
        Constructor<T> c;
        try {
            c = createConstructor();
        } catch (NoSuchMethodException e) {
            c = null;
        }

        WHITE = createItem(format(name,ColorsVanilla.WHITE),c,param,ColorsVanilla.WHITE.tint);
        ORANGE = createItem(format(name,ColorsVanilla.ORANGE),c,param,ColorsVanilla.ORANGE.tint);
        MAGENTA = createItem(format(name,ColorsVanilla.MAGENTA),c,param,ColorsVanilla.MAGENTA.tint);
        LIGHT_BLUE = createItem(format(name,ColorsVanilla.LIGHT_BLUE),c,param,ColorsVanilla.LIGHT_BLUE.tint);
        YELLOW = createItem(format(name,ColorsVanilla.YELLOW),c,param,ColorsVanilla.YELLOW.tint);
        LIME = createItem(format(name,ColorsVanilla.LIME),c,param,ColorsVanilla.LIME.tint);
        PINK = createItem(format(name,ColorsVanilla.PINK),c,param,ColorsVanilla.PINK.tint);
        GRAY = createItem(format(name,ColorsVanilla.GRAY),c,param,ColorsVanilla.GRAY.tint);
        LIGHT_GRAY = createItem(format(name,ColorsVanilla.LIGHT_GRAY),c,param,ColorsVanilla.LIGHT_GRAY.tint);
        CYAN = createItem(format(name,ColorsVanilla.CYAN),c,param,ColorsVanilla.CYAN.tint);
        PURPLE = createItem(format(name,ColorsVanilla.PURPLE),c,param,ColorsVanilla.PURPLE.tint);
        BLUE = createItem(format(name,ColorsVanilla.BLUE),c,param,ColorsVanilla.BLUE.tint);
        BROWN = createItem(format(name,ColorsVanilla.BROWN),c,param,ColorsVanilla.BROWN.tint);
        GREEN = createItem(format(name,ColorsVanilla.GREEN),c,param,ColorsVanilla.GREEN.tint);
        RED = createItem(format(name,ColorsVanilla.RED),c,param,ColorsVanilla.RED.tint);
        BLACK = createItem(format(name,ColorsVanilla.BLACK),c,param,ColorsVanilla.BLACK.tint);
    }


    protected abstract Constructor<T> createConstructor() throws NoSuchMethodException;
    protected T createItem(String name, Constructor<T> constructor, Object[] param, int tint)
    {
        Object[] args = ArrayUtils.add(param,0,name);
        try {
            return constructor.newInstance(ArrayUtils.add(args,tint));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println(e.getClass().getSimpleName()+" Error on initializing "+name+" :(");
            e.printStackTrace();
        }
        return null;
    }

    private static String format(String str, ColorsVanilla color)
    {
        return String.format(str,color.name);
    }

    private enum ColorsVanilla
    {
        WHITE("white",0xfffff),
        ORANGE("orange",0xffa500),
        MAGENTA("magenta",0xFF00FF),
        LIGHT_BLUE("light_blue",0x8B8BFF),
        YELLOW("yellow",0xf0f05b),
        LIME("lime",0x00FF00),
        PINK("pink",0xFFC0CB),
        GRAY("gray",0x8d8b8b),
        LIGHT_GRAY("light_gray",0x989898),
        CYAN("cyan",0x00ffff),
        PURPLE("purple",0xb163a3),
        BLUE("blue",0x7f93ff),
        BROWN("brown",0x8B4513),
        GREEN("green",0x65A565),
        RED("red",0xfe0f2e),
        BLACK("black",0x3c3a3a);

        private final String name;
        private final int tint;

        ColorsVanilla(String name,int tint)
        {
            this.name = name;
            this.tint = tint;
        }
    }
}
