package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.api.ElementComposition;

public enum ElementDictionary
{

    /**
    * Pure metal elements
    */

    ALUMINIUM("al",0xd8f0ff,0x996100, new ElementComposition[] {new ElementComposition("al",0.9), new ElementComposition("ti",0.1)}),
    COPPER(   "cu",0xc1834b,0x5e9c59, new ElementComposition[] {new ElementComposition("cu",0.99),new ElementComposition("au",0.01)}),
    GOLD(     "au",0xe7e74c,0xe7e74c, new ElementComposition[] {new ElementComposition("au",0.99),new ElementComposition("cu",0.01)}),
    IRIDIUM(  "ir",0xffffff,0xf0ffd8, new ElementComposition[] {new ElementComposition("ir",0.55),new ElementComposition("os",0.45)}),
    IRON(     "fe",0xe5dddd,0xb6a8a8, new ElementComposition[] {new ElementComposition("fe",1)}),
    LEAD(     "pb",0x664c86,0xc1c2c5, new ElementComposition[] {new ElementComposition("pb",1)}),
    LITHIUM(  "li",0xdfe3ee,0xc1c2cf, new ElementComposition[] {}),
    MERCURY(  "hg",0xe8e9ee,0xbc2b3b, new ElementComposition[] {}),
    NICKEL(   "ni",0xdfe3ee,0xc1c2cf, new ElementComposition[] {new ElementComposition("ni",0.95),new ElementComposition("fe",0.05)}),
    SILVER(   "ag",0xffffff,0xdaf7f8, new ElementComposition[] {}),
    THORIUM(  "th",0x333,   0x444818, new ElementComposition[] {}),
    TIN(      "sn",0x9daaae,0xc7bdc4, new ElementComposition[] {}),
    TITANIUM( "ti",0xE8E9EE,0x9abdc4, new ElementComposition[] {}),
    TUNGSTEN( "w", 0x1d2630,0x5e5454, new ElementComposition[] {}),
    URANIUM(  "u", 0x077a07,0xaeb559, new ElementComposition[] {}),
    ZINC(     "zn",0xaecfd8,0xaeb5ca, new ElementComposition[] {}),


    /**
     * Minerals
     */

    CARBON( "c",   0x000000,0x000000, new ElementComposition[] {new ElementComposition("c", 1)}),
    SILICON("si",  0x000000,0x000000, new ElementComposition[] {new ElementComposition("si",1)}),
    GALENA( "pbag",0x000000,0x000000, new ElementComposition[] {new ElementComposition("pb",0.87),new ElementComposition("s",0.12),new ElementComposition("ag",0.01)}),




    /**
     * Alloys
     */

    BRASS( "cuzn",0xc8a23c,0xc8a23c, new ElementComposition[] {new ElementComposition("cu",0.6), new ElementComposition("zn",0.4)}),
    BRONZE("cusn",0xffa23c,0xffa23c, new ElementComposition[] {new ElementComposition("cu",0.88),new ElementComposition("sn",0.12)}),
    STEEL( "fec", 0x6b6d74,0x6b6d74, new ElementComposition[] {new ElementComposition("fe",0.99),new ElementComposition("c",0.01)});

    private final String symbol;
    private final int colorPrimary, colorSecondary;
    private final ElementComposition[] composition;


    ElementDictionary(String symbol, int colorPrimary, int colorSecondary, ElementComposition[] composition)
    {
        this.symbol = symbol;
        this.colorPrimary = colorPrimary;
        this.colorSecondary = colorSecondary;
        this.composition = composition;
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

    public ElementComposition[] getComposition()
    {
        return this.composition;
    }

    public ElementComposition getComposition(int index)
    {
        return this.composition[index];
    }
    
    /*class Elements
    {
        public final Element ALUMINIUM = new Aluminium();
        public final Element COPPER    = new Copper();
        public final Element GOLD      = new Gold();
        public final Element IRIDIUM   = new Iridium();
        public final Element IRON      = new Iron();
        public final Element LEAD      = new Lead();
        public final Element LITHIUM   = new Lithium();
        public final Element MERCURY   = new Mercury();
        public final Element SILVER    = new Silver();
        public final Element THORIUM   = new Thorium();
        public final Element TIN       = new Tin();
        public final Element TITANIUM  = new Titanium();
        public final Element TUNGSTEN  = new Tungsten();
        public final Element URANIUM   = new Uranium();
        public final Element ZINC      = new Zinc();
        //public final Element CARBON;
        //public final Element SILICON;
        public final Element BRASS     = new Brass();
        public final Element BRONZE    = new Bronze();
        public final Element STEEL     = new Steel();
    }*/
}
