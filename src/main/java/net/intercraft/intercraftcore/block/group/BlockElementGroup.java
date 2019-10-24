package net.intercraft.intercraftcore.block.group;

import net.intercraft.intercraftcore.block.BlockElement;
import net.intercraft.intercraftcore.block.BlockFrame;
import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.ore.BlockHardOre;
import net.minecraft.block.Block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockElementGroup
{
    /**
     * The generic block type
     */
    protected final Class<BlockElement> blockElementClass;
    protected final Class<BlockHardOre> blockHardOreClass;
    protected final Class<BlockFrame> blockFrame;

    /**
     * Keep a reference to the element
     */
    public final Element element;

    /**
     * Element Block Forms
     */
    public final Block BLOCK;
    public final Block FRAME;

    /**
     * Create an block group for the given element
     *
     * @param element
     */
    public BlockElementGroup(Element element)
    {
        this.element = element;
        this.blockElementClass = BlockElement.class;
        this.blockHardOreClass = BlockHardOre.class;
        this.blockFrame = BlockFrame.class;

        BLOCK = createBlock(Element.BLOCK, "block");
        FRAME = createBlock(Element.FRAME, "frame");
    }

    /**
     * Create an element item form
     *
     * @param form
     * @param suffix
     */
    protected Block createBlock(byte form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor;// = blockElementClass.getConstructor(Element.class, String.class);

                switch (form) {
                    case Element.FRAME:
                        constructor = blockFrame.getConstructor(Element.class, String.class);
                        break;

                    default:
                        constructor = blockElementClass.getConstructor(Element.class, String.class);
                }

                return (Block) constructor.newInstance(new Object[] { element, suffix });
            }
            catch (NoSuchMethodException e) {}
            catch (InstantiationException e) {}
            catch (IllegalAccessException e) {}
            catch (InvocationTargetException e) {}
        }
        return null;
    }
}
