package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.api.ElementComposition;

public class Element
{
    /**
     * Element Type Bit flags
     */
    public static final short
            INGOT      = 0x1,
            NUGGET     = 0x2,
            DUST       = 0x4,
            DUST_SMALL = 0x8,
            PLATE      = 0x10,
            GEAR       = 0x20,
            ROD        = 0x40,
            CHUNK      = 0x80,

            BLOCK      = 0x100,
            FRAME      = 0x200,
            ORE        = 0x400;
            //LIQUID     = 0x800;

    /**
     * Element information
     */
    public final String name,     symbol;
    public final int    tintPrim, tintSec;
    public final ElementComposition[] composition;

    /**
     * Byte forms of the group
     */
        public short forms = INGOT | NUGGET | DUST | DUST_SMALL | PLATE | GEAR | ROD | CHUNK | BLOCK | FRAME | ORE;

    /**
     * Element Constructor
     *
     * @param name       Element name.
     * @param dictionary Element dictionary entry.
     */
    public Element(String name, ElementDictionary dictionary)
    {
        this.name        = name;
        this.symbol      = dictionary.getSymbol();
        this.tintPrim    = dictionary.getColorPrimary();
        this.tintSec     = dictionary.getColorSecondary();
        this.composition = dictionary.getComposition();
    }

    /**
     * Element Constructor w. type exclusions
     *
     * @param name         Element name.
     * @param dictionary   Element dictionary entry.
     * @param excludeForms Bit flags for group item/block forms to exclude.
     */
    public Element(String name, ElementDictionary dictionary, int excludeForms)
    {
        this(name,dictionary);
        forms &= ~excludeForms;
    }
}
