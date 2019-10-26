package net.intercraft.intercraftcore.api;

public class ElementComposition
{

    private String symbol;
    private double percentage;

    public ElementComposition(String symbol, double percentage)
    {
        this.symbol = symbol;
        this.percentage = percentage;
    }


    public String getSymbol(boolean formatted)
    {
        return formatted ? format(this.symbol) : this.symbol;
    }

    public double getPercentage()
    {
        return this.percentage;
    }

    public int getPercentage(boolean formatted)
    {
        return (int)Math.round(formatted ? this.percentage*100 : this.percentage);
    }

    public String toString()
    {
        return "{"+format(this.symbol)+":"+(int)Math.round(this.percentage*100)+"%}";
    }


    private String format(String str)
    {
        return str.length() > 1 ? String.format("%c%s",Character.toUpperCase(str.charAt(0)),str.substring(1)) : Character.toUpperCase(str.charAt(0))+""; // ??? [1.14.2] Natural wood log check + I18n
    }
}
