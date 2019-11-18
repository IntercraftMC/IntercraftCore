package net.intercraft.intercraftcore.block.group;

import net.intercraft.intercraftcore.block.BlockSolidElement;
import net.intercraft.intercraftcore.block.BlockFrameElement;
import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.block.BlockHardOre;
import net.minecraft.block.Block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockElementGroup
{
    /**
     * The generic block type
     */
    protected final Class<BlockSolidElement> blockSolidElementClass;
    protected final Class<BlockHardOre> blockHardOreClass;
    protected final Class<BlockFrameElement> blockFrameElementClass;

    /**
     * Keep a reference to the element
     */
    public final Element element;

    /**
     * Element Block Forms
     */
    public final Block BLOCK;
    public final Block FRAME;
    public final Block ORE;

    /**
     * Create a block group for the given element.
     *
     * @param element Element.
     */
    public BlockElementGroup(Element element)
    {
        this.element           = element;
        blockSolidElementClass = BlockSolidElement.class;
        blockFrameElementClass = BlockFrameElement.class;
        blockHardOreClass      = BlockHardOre.class;


        BLOCK = createBlock(Element.BLOCK, "block");
        FRAME = createBlock(Element.FRAME, "frame");
        ORE   = createBlock(Element.ORE,   "ore");
    }

    /**
     * Create an element block form.
     *
     * @param form Element type.
     * @param suffix Element name.
     */
    protected Block createBlock(int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor;

                switch (form) {
                    case Element.FRAME:
                        constructor = createConstructor(blockFrameElementClass);
                        break;

                    case Element.ORE:
                        constructor = createConstructor(blockHardOreClass);
                        break;

                    default:
                        constructor = createConstructor(blockSolidElementClass);
                }

                return (Block) constructor.newInstance(new Object[] { element, suffix });
            }
            catch (InstantiationException e) {
                printError(e);
            }
            catch (IllegalAccessException e) {
                printError(e);
            }
            catch (InvocationTargetException e) {
                printError(e);
            }
        }
        return null;
    }

    protected Constructor<?> createConstructor(Class<?> clazz)
    {

        try {
            return clazz.getConstructor(Element.class, String.class);
        }
        catch (NoSuchMethodException e) {
            printError(e);
        }

        return null;
    }

    protected void printError(Exception e)
    {
        System.out.println(String.format("[%s]: {%s}", e.getClass().getSimpleName(), e));

    }
}
