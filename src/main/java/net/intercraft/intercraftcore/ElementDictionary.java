package net.intercraft.intercraftcore;

//import net.intercraft.intercraftcore.element.*;

public enum ElementDictionary
{

    /**
    * Pure metal elements
    */

    ALUMINIUM("al",0xd8f0ff,0x996100),
    COPPER(   "cu",0xc1834b,0x5e9c59),
    GOLD(     "au",0xe7e74c,0xe7e74c),
    IRIDIUM(  "ir",0xffffff,0xf0ffd8),
    IRON(     "fe",0xe5dddd,0xb6a8a8),
    LEAD(     "pb",0x664c86,0xc1c2c5),
    LITHIUM(  "li",0xdfe3ee,0xc1c2cf),
    MERCURY(  "hg",0xe8e9ee,0xbc2b3b),
    SILVER(   "ag",0xffffff,0xdaf7f8),
    THORIUM(  "th",0x333,   0x444818),
    TIN(      "sn",0x9daaae,0xc7bdc4),
    TITANIUM( "ti",0xE8E9EE,0x9abdc4),
    TUNGSTEN( "w", 0x1d2630,0x5e5454),
    URANIUM(  "u", 0x077a07,0xaeb559),
    ZINC(     "zn",0xaecfd8,0xaeb5ca),


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
