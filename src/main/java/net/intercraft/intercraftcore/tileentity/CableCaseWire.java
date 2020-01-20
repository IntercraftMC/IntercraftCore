package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.wire.Wire;
import net.minecraft.util.Direction;

public class CableCaseWire
{
    private final Direction.Axis axis;
    private final Wire wire;
    private final byte in, out, cap;
    private final boolean end;

    public CableCaseWire(Direction.Axis axis, Wire wire, byte in, byte out, byte cap, boolean end)
    {
        this.axis = axis;
        this.wire = wire;
        this.in = in;
        this.out = out;
        this.cap = cap;
        this.end = end;
    }

    public byte getCapacity()
    {
        return cap;
    }

    public void draw()
    {

    }
}
