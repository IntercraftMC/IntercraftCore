package net.intercraft.intercraftcore.ore;

public class ElementComposition {

    private String name;
    private double percentage;

    public ElementComposition(String name, double percentage) {

        this.name = name;
        this.percentage = percentage;

    }

    public String getName(boolean formatted) {
        if (formatted)
            return this.name;
        else
            return format(this.name);
    }

    public double getPercentage() {
        return this.percentage;
    }


    public String toString() {
        return format(this.name)+": "+this.percentage*100+"%";
    }

    private String format(String str) {
        return String.format("%c%s",Character.toUpperCase(str.charAt(0)),str.substring(1));
    }
}
