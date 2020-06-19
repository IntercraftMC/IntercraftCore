package net.intercraft.intercraftcore.block.group;

import net.minecraft.block.Block;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class BlockMetaGroup<T extends Block>
{

    private final T[] blocks;
    private final String name;
    private final short start, end;
    private final boolean reversed;

    /**
     * Create Block Meta Array
     *
     * @param start Start value.
     * @param end   End value.
     * @param param Additional parameters for the block.
     */
    public BlockMetaGroup(short start, short end,Object...param)
    {
        if (!((String)param[nameIndex()]).contains("%s")) throw new IllegalArgumentException("Name String needs \"%s\" in the name!");

        reversed = start > end;
        final short l = reversed ? (short)(start-end) : (short)(end-start);

        Constructor<T> c;

        try {
            c = createConstructor();
        } catch (NoSuchMethodException err) {
            System.out.println("Error getting the constructor!");
            err.printStackTrace();
            c = null;
        }

        this.name   = (String) param[nameIndex()];
        this.start  = start;
        this.end    = end;
        this.blocks = (T[])Array.newInstance(c.getDeclaringClass(),l);
        for (short i=0;i<l;i++) {
            param[nameIndex()] = String.format(name,reversed ? (l-1 - i)+start : i+start);
            param = replaceClassWithObject(param,Byte.class,reversed ? (byte)(l-1 - i) : (byte)i);

            blocks[reversed ? l-1 - i : i] = createBlock(c, param);
        }

    }

    public BlockMetaGroup(short end,Object...param)
    {
        this((short)0,end,param);
    }

    protected abstract Constructor<T> createConstructor() throws NoSuchMethodException;

    /**
     * The index of the name parameter.
     */
    protected short nameIndex()
    {
        return 0;
    }
    protected T createBlock(Constructor<T> constructor, Object[] param)
    {
        try {
            return constructor.newInstance(param);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println(e.getClass().getSimpleName()+" Error on initializing");
            e.printStackTrace();
            return null;
        }
    }

    protected static <V> Object[] replaceClassWithObject(final Object[] param,Class<V> clazz,V value)
    {
        for (short i=0;i<param.length;i++)
            if (param[i].getClass().isInstance(clazz)) {
                param[i] = value;
                break;
            }
        return param;
    }

    /**
     * Get index local from start/end to end/start
     *
     * @param index Array index local to start-end/end-start.
     */
    public short getLocalIndex(short index)
    {
        if (reversed) {
            final short l = (short) Math.max(index, end);
            return (short) Math.min(l - end, start - 1);
        } else {
            final short l = (short) Math.max(index, start);
            return (short) Math.min(l - start, end - 1);
        }
    }

    public short getSize()
    {
        return (short)blocks.length;
    }

    public T[] getBlocks()
    {
        return blocks;
    }

    public T getBlock(short index)
    {
        return blocks[index];
    }

    /**
     * Get block from array local to start- end range and reversed if start > end.
     */
    public T getBlockLocal(short index)
    {
        return blocks[getLocalIndex(index)];
    }

    public String getName()
    {
        return name;
    }

    public String getName(short index)
    {
        return String.format(name,getLocalIndex(index));
    }

    public short getStart()
    {
        return start;
    }

    public short getEnd()
    {
        return end;
    }
}
