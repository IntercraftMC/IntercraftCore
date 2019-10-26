package net.intercraft.intercraftcore.block.group;

import net.intercraft.intercraftcore.block.BlockElement;
import net.intercraft.intercraftcore.block.BlockFrame;
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
    protected final Class<BlockElement> blockElementClass;
    protected final Class<BlockHardOre> blockHardOreClass;
    protected final Class<BlockFrame> blockFrameClass;

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
     * Create an block group for the given element
     *
     * @param element
     */
    public BlockElementGroup(Element element)
    {
        this.element = element;
        this.blockElementClass = BlockElement.class;
        this.blockFrameClass   = BlockFrame.class;
        this.blockHardOreClass = BlockHardOre.class;


        BLOCK = createBlock(Element.BLOCK, "block");
        FRAME = createBlock(Element.FRAME, "frame");
        ORE   = createBlock(Element.ORE,   "ore");
    }

    /**
     * Create an element item form
     *
     * @param form
     * @param suffix
     */
    protected Block createBlock(int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor;// = blockElementClass.getConstructor(Element.class, String.class);

                switch (form) {
                    case Element.FRAME:
                        constructor = blockFrameClass.getConstructor(Element.class, String.class);
                        break;

                    case Element.ORE:
                        constructor = blockHardOreClass.getConstructor(Element.class, String.class);
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
