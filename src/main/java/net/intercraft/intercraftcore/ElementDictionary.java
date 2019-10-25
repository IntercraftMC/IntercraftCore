package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.ore.ElementComposition;

public enum ElementDictionary
{

    /**
    * Pure metal elements
    */

    ALUMINIUM("al",0xd8f0ff,0x996100, new ElementComposition[] {new ElementComposition("al",1)}),
    COPPER(   "cu",0xc1834b,0x5e9c59, new ElementComposition[] {new ElementComposition("cu",0.95),new ElementComposition("au",0.05)}),
    GOLD(     "au",0xe7e74c,0xe7e74c, new ElementComposition[] {new ElementComposition("au",0.99),new ElementComposition("cu",0.01)}),
    IRIDIUM(  "ir",0xffffff,0xf0ffd8, new ElementComposition[] {}),
    IRON(     "fe",0xe5dddd,0xb6a8a8, new ElementComposition[] {}),
    LEAD(     "pb",0x664c86,0xc1c2c5, new ElementComposition[] {}),
    LITHIUM(  "li",0xdfe3ee,0xc1c2cf, new ElementComposition[] {}),
    MERCURY(  "hg",0xe8e9ee,0xbc2b3b, new ElementComposition[] {}),
    NICKEL(   "ni",0xdfe3ee,0xc1c2cf, new ElementComposition[] {}),
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

    CARBON( "c", 0x000000,0x000000, new ElementComposition[] {}),
    SILICON("si",0x000000,0x000000, new ElementComposition[] {}),



    /**
     * Alloys
     */

    BRASS( "cuzn",0xc8a23c,0xc8a23c, new ElementComposition[] {}),
    BRONZE("cusn",0xffa23c,0xffa23c, new ElementComposition[] {}),
    STEEL( "fec", 0x6b6d74,0x6b6d74, new ElementComposition[] {});

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
