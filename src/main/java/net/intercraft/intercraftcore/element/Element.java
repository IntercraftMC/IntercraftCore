package net.intercraft.intercraftcore.element;

public class Element
{
    /**
     * Element Type Bit flags
     */
    public static final byte INGOT      = 0x01;
    public static final byte NUGGET     = 0x02;
    public static final byte DUST       = 0x04;
    public static final byte DUST_SMALL = 0x08;
    public static final byte PLATE      = 0x10;

    /**
     * Element information
     */
    public final String name;
    public final String symbol;
    public final int    tint;

    /**
     * Item forms of the group
     */
    public byte forms = INGOT | NUGGET | DUST | DUST_SMALL | PLATE;

    /**
     * Element Constructor
     *
     * @param name Element name
     * @param tint Element tint color
     */
    public Element(String name, String symbol, int tint)
    {
        this.name = name;
        this.symbol = symbol;
        this.tint = tint;
    }

    /**
     * Element construct with type exclusions
     *
     * @param name         Element name
     * @param tint         Element tint color
     * @param excludeForms Bit flags for group item forms to exclude
     */
    public Element(String name, String symbol, int tint, byte excludeForms)
    {
        this.name = name;
        this.symbol = symbol;
        this.tint = tint;
        this.forms &= ~excludeForms;
    }
}
