package net.intercraft.intercraftcore.ore;

public class ElementComposition {

    private String name;
    private double percentage;

    public ElementComposition(String name, double percentage) {

        this.name = name;
        this.percentage = percentage;

    }

    public String getName() {
        return this.name;
    }

    public double getPercentage() {
        return this.percentage;
    }


    public String toString() {
        return this.name+": "+this.percentage*100+"%";
    }
}
