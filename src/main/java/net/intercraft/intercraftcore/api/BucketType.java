package net.intercraft.intercraftcore.api;

import net.minecraft.util.IStringSerializable;

public enum BucketType implements IStringSerializable
{
    NONE("none"),
    WOODOAK("wood_oak"),
    METALIRON("metal_iron");

    private final String name;

    BucketType(String name)
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
