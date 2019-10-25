package net.intercraft.intercraftcore.ore;

import net.intercraft.intercraftcore.ElementDictionary;

public class ElementComposition
{

    private String symbol;
    private double percentage;

    public ElementComposition(String symbol, double percentage)
    {
        this.symbol = symbol;
        this.percentage = percentage;
    }

    /*public ElementDictionary getElement()
    {
        return this.element;
    }*/

    public String getSymbol(boolean formatted)
    {
        if (formatted)
            return format(this.symbol);
        else
            return this.symbol;
    }

    public double getPercentage()
    {
        return this.percentage;
    }

    public String toString()
    {
        return format(this.symbol)+":"+(int)this.percentage*100+"%";
    }


    private String format(String str)
    {
        if (str.length() > 1)
            return String.format("%c%s",Character.toUpperCase(str.charAt(0)),str.substring(1));
        else
            return Character.toUpperCase(str.charAt(0))+""; // ??? [1.14.2] Natural wood log check + I18n
    }
}
