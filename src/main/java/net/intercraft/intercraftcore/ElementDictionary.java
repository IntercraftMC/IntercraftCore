package net.intercraft.intercraftcore;

public enum ElementDictionary
{

    /**
    * Pure metal elements
    */

    ALUMINIUM("al",0x9daaae,0xdfe3ee),
    COPPER(   "cu",0xc1810a,0x57b988),
    GOLD(     "au",0xe7e74c,0xe7e74c),
    IRIDIUM(  "ir",0xE8E9EE,0xE8E9EE),
    IRON(     "fe",0xb6a8a8,0x927c7c),
    LEAD(     "pb",0x534368,0x534368),
    LITHIUM(  "li",0xdfe3ee,0xdfe3ee),
    MERCURY(  "hg",0xe8e9ee,0xe5715c),
    SILVER(   "ag",0xffffff,0xffffff),
    THORIUM(  "th",0x333,   0x333),
    TIN(      "sn",0x9daaae,0xdfe3ee),
    TITANIUM( "ti",0xE8E9EE,0xE8E9EE),
    TUNGSTEN( "w", 0x1d2630,0x1d2630),
    URANIUM(  "u", 0x077a07,0xb8c12f),
    ZINC(     "zn",0xdfe3ee,0xdfe3ee),


    /**
     * Minerals
     */

    CARBON( "c", 0x000000,0x000000),
    SILICON("si",0x000000,0x000000),



    /**
     * Alloys
     */

    BRASS( "cuzn",0xc8a23c,0xc8a23c),
    BRONZE("cusn",0xffa23c,0xffa23c),
    STEEL( "fec",0x6b6d74,0x6b6d74);

    private final String symbol;
    private final int colorPrimary, colorSecondary;


    ElementDictionary(String symbol, int colorPrimary, int colorSecondary)
    {
        this.symbol = symbol;
        this.colorPrimary = colorPrimary;
        this.colorSecondary = colorSecondary;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public int getColorPrimary()
    {
        return this.colorPrimary;
    }

    public int getColorSecondary()
    {
        return this.colorSecondary;
    }
}
