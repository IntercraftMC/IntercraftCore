package net.intercraft.intercraftcore;

public enum Authors
{
    SIMON("Simon",         "simon_kungen",   "programmer"),
    DAVID("David",         "SirDavidLudwig", "programmer"),
    BLUEFIER("BlueFier42", "BlueFier42",     "artist");


    private final String name, ign, role;

    Authors(String name, String ign, String role)
    {
        this.name = name;
        this.ign  = ign;
        this.role = role;
    }

    public String getName()
    {
        return name;
    }

    public String getInGameName()
    {
        return ign;
    }

    public String getRole()
    {
        return role;
    }

    public String fault()
    {
        return name+" f**ked up!";
    }

    public String isFault(String fault)
    {
        return name+" blames "+fault+".";
    }

    public String isFault(Authors fault)
    {
        return name+" blames "+fault.name+".";
    }
}
