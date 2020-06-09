package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.api.ElementComposition;

public enum ElementDictionary
{

    /**
    * Pure metal elements
    */

    ALUMINIUM("al",0xd8f0ff,0x996100, new ElementComposition[] {new ElementComposition("al",0.9), new ElementComposition("ti",0.1)}),
    COPPER(   "cu",0xc1834b,0x5e9c59, new ElementComposition[] {new ElementComposition("cu",0.99),new ElementComposition("au",0.01)}),
    GOLD(     "au",0xe7e74c,0xe7e74c, 3, new ElementComposition[] {new ElementComposition("au",0.99),new ElementComposition("cu",0.01)}),
    IRIDIUM(  "ir",0xffffff,0xf0ffd8, 3, new ElementComposition[] {new ElementComposition("ir",0.55),new ElementComposition("os",0.45)}),
    IRON(     "fe",0xe5dddd,0xd5c4ac, 3, new ElementComposition[] {new ElementComposition("fe",1)}),
    LEAD(     "pb",0x664c86,0xc1c2c5, new ElementComposition[] {new ElementComposition("pb",1)}),
    LITHIUM(  "li",0xdfe3ee,0xc1c2cf, new ElementComposition[] {new ElementComposition("li",1)}),
    MERCURY(  "hg",0xe8e9ee,0xbc2b3b, new ElementComposition[] {new ElementComposition("hg",1)}),
    NICKEL(   "ni",0xF6E3A7,          new ElementComposition[] {new ElementComposition("ni",0.95),new ElementComposition("fe",0.05)}),
    SILVER(   "ag",0xffffff,0xdaf7f8, 3, new ElementComposition[] {new ElementComposition("ag",1)}),
    THORIUM(  "th",0x333,   0x444818, 3, new ElementComposition[] {new ElementComposition("th",1)}),
    TIN(      "sn",0x9daaae,0xc7bdc4, new ElementComposition[] {new ElementComposition("sn",1)}),
    TITANIUM( "ti",0xE8E9EE,0x9abdc4, 3, new ElementComposition[] {new ElementComposition("ti",1)}),
    TUNGSTEN( "w", 0x1d2630,0x5e5454, 3, new ElementComposition[] {new ElementComposition("w",1)}),
    URANIUM(  "u", 0x077a07,0xaeb559, 3, new ElementComposition[] {new ElementComposition("u",1)}),
    ZINC(     "zn",0xaecfd8,0xaeb5ca, new ElementComposition[] {new ElementComposition("zn",1)}),


    /**
     * Minerals + Ores
     */

    CARBON(   "c",     0x000000,0x000000, new ElementComposition[] {new ElementComposition("c", 1)}),
    SILICON(  "si",    0x000000,0x000000, new ElementComposition[] {new ElementComposition("si",1)}),
    GALENA(   "pbag",  0x746486, new ElementComposition[] {new ElementComposition("pb",0.87),new ElementComposition("s",0.12),new ElementComposition("ag",0.01)}),
    SPODUMENE("lialsi",0xba6f87, new ElementComposition[] {new ElementComposition("li",0.95),new ElementComposition("al",0.04),new ElementComposition("si",0.01)}),


    /**
     * Alloys
     */

    BRASS(   "cuzn",0xc8a23c, new ElementComposition[] {new ElementComposition("cu",0.6), new ElementComposition("zn",0.4)}),
    BRONZE(  "cusn",0xffa23c, new ElementComposition[] {new ElementComposition("cu",0.88),new ElementComposition("sn",0.12)}),
    ELECTRUM("agau",0xffe05c, new ElementComposition[] {new ElementComposition("ag",0.5),new ElementComposition("au",0.5)}),
    STEEL(   "fec", 0x6b6d74, new ElementComposition[] {new ElementComposition("fe",0.99),new ElementComposition("c",0.01)});

    private final String symbol;
    private final int colorPrimary, colorSecondary;
    private final float hardness;
    private final ElementComposition[] composition;


    ElementDictionary(String symbol, int colorPrimary, int colorSecondary, ElementComposition[] composition)
    {
        this(symbol,colorPrimary,colorSecondary,1,composition);
    }

    ElementDictionary(String symbol, int colorPrimary, ElementComposition[] composition)
    {
        this(symbol,colorPrimary,colorPrimary,1,composition);
    }

    ElementDictionary(String symbol, int colorPrimary, int colorSecondary, float hardness, ElementComposition[] composition)
    {
        this.symbol         = symbol;
        this.colorPrimary   = colorPrimary;
        this.colorSecondary = colorSecondary;
        this.hardness       = hardness;
        this.composition    = composition;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public int getColorPrimary()
    {
        return colorPrimary;
    }

    public int getColorSecondary()
    {
        return colorSecondary;
    }

    public float getHarvestLevel()
    {
        return hardness;
    }

    public ElementComposition[] getComposition()
    {
        return composition;
    }

    public ElementComposition getComposition(byte index)
    {
        return composition[index];
    }
}
