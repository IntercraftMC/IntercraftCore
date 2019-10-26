package net.intercraft.intercraftcore.element;

import net.intercraft.intercraftcore.ElementDictionary;
import net.intercraft.intercraftcore.api.ElementComposition;

public class Element
{
    /**
     * Element Type Bit flags
     */
    public static final int INGOT      = 0x1;
    public static final int NUGGET     = 0x2;
    public static final int DUST       = 0x4;
    public static final int DUST_SMALL = 0x8;
    public static final int PLATE      = 0x10;
    public static final int GEAR       = 0x20;
    public static final int ROD        = 0x40;
    public static final int CHUNK      = 0x80;

    public static final int BLOCK      = 0x100;
    public static final int FRAME      = 0x200;
    public static final int ORE        = 0x400;

    /**
     * Element information
     */
    public final String               name;
    public final String               symbol;
    public final int                  tintPrim;
    public final int                  tintSec;
    public final ElementComposition[] composition;

    /**
     * Byte forms of the group
     */
        public int forms = INGOT | NUGGET | DUST | DUST_SMALL | PLATE | GEAR | ROD | CHUNK | BLOCK | FRAME | ORE;

    /**
     * Element Constructor
     *
     * @param name Element name
     * @param dictionary   Element dictionary entry
     */
    public Element(String name, ElementDictionary dictionary)
    {
        this.name = name;
        this.symbol = dictionary.getSymbol();
        this.tintPrim = dictionary.getColorPrimary();
        this.tintSec = dictionary.getColorSecondary();
        this.composition = dictionary.getComposition();
    }

    /**
     * Element Constructor with type exclusions
     *
     * @param name         Element name
     * @param dictionary   Element dictionary entry
     * @param excludeForms Bit flags for group item/block forms to exclude
     */
    //public Element(String name, String symbol, int tintPrim, ElementComposition[] composition, int excludeForms)
    public Element(String name, ElementDictionary dictionary, int excludeForms)
    {
        this.name = name;
        this.symbol = dictionary.getSymbol();
        this.tintPrim = dictionary.getColorPrimary();
        this.tintSec = dictionary.getColorSecondary();
        this.composition = dictionary.getComposition();

        this.forms &= ~excludeForms;
    }
}
