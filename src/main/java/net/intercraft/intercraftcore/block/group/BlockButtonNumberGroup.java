package net.intercraft.intercraftcore.block.group;

import net.intercraft.intercraftcore.block.BlockButtonNumber;

import java.lang.reflect.Constructor;

public class BlockButtonNumberGroup extends BlockMetaGroup<BlockButtonNumber>
{
    public BlockButtonNumberGroup(final String name, final String translateKey, final boolean isWooden)
    {
        super((short) 10,name,translateKey,Byte.class,isWooden);
    }

    @Override
    protected Constructor<BlockButtonNumber> createConstructor() throws NoSuchMethodException
    {
        return BlockButtonNumber.class.getConstructor(String.class, String.class, byte.class, boolean.class);
    }
}
