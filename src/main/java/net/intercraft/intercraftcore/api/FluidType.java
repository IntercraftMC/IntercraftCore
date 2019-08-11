package net.intercraft.intercraftcore.api;

import net.minecraft.util.IStringSerializable;

public enum FluidType implements IStringSerializable
{
    NONE("none"),
    WATER("water"),
    RESIN("resin");

    private final String name;

    FluidType(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}
