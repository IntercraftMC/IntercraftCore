package net.intercraft.intercraftcore.block.group;

import net.intercraft.intercraftcore.block.BlockFrameUranium;
import net.intercraft.intercraftcore.block.BlockSolidUranium;
import net.intercraft.intercraftcore.element.Element;
import net.minecraft.block.Block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockUraniumGroup extends BlockElementGroup
{


    public BlockUraniumGroup(Element element)
    {
        super(element);
    }

    /**
     * Create an element block form
     *
     * @param form the element type.
     * @param suffix the element name.
     */

    @Override
    protected Block createBlock(int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                Constructor<?> constructor;

                switch (form) {
                    case Element.FRAME:
                        constructor = createConstructor(BlockFrameUranium.class);
                        break;

                    case Element.ORE:
                        constructor = createConstructor(blockHardOreClass);
                        break;

                    default:
                        constructor = createConstructor(BlockSolidUranium.class);
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
}
