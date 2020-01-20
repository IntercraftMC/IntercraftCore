package net.intercraft.intercraftcore.api;

import net.minecraft.util.IStringSerializable;

public enum CableCaseFaces implements IStringSerializable
{
    NONE("none"),
    CONNECTED("connected"),
    PLATE("plate"),
    MODULE("module");

    private final String name;
    CableCaseFaces(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
