package net.intercraft.intercraftcore.api.enumProperties;

import net.minecraft.util.IStringSerializable;

public enum CableCaseFaces implements IStringSerializable
{
    NONE("none"),
    CONNECTED("connected"),
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
