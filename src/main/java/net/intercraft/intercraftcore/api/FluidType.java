package net.intercraft.intercraftcore.api;

public enum FluidType
{
    NONE("none",0),
    WATER("water",1),
    RESIN("resin",2.5);

    private final String name;
    private double viscosity;

    FluidType(String name, double viscosity)
    {
        this.name = name;
        this.viscosity = viscosity;
    }

    public String toString()
    {
        return this.name+":"+this.viscosity;
    }

    public String getName()
    {
        return this.name;
    }

    public double getViscosity()
    {
        return this.viscosity;
    }
}
