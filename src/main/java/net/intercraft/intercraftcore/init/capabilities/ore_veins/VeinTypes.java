package net.intercraft.intercraftcore.init.capabilities.ore_veins;

public enum VeinTypes
{
    COPPER("copper",0.1,5),
    TIN("tin",0.1,6);



    private final String name;
    private final double min;
    private final double max;

    private double weight = 1;

    VeinTypes(String name, double min, double max)
   {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public VeinTypes get()
    {
        return this;
    }

    public String getName()
    {
        return name;
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}
