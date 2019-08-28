package net.intercraft.intercraftcore;

public enum ElementDictionary
{
    COPPER("cu",0xc1810a,0x57b988),
    GOLD("au",0xe7e74c,0xe7e74c),
    IRON("fe",0xb6a8a8,0x927c7c),
    LEAD("pb",0x534368,0x534368),
    SILVER("ag",0xffffff,0xffffff),
    TIN("sn",0x9daaae,0x9daaae),
    TUNGSTEN("w",0x1d2630,0x1d2630),
    URANIUM("u",0x077a07,0x838383);


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
