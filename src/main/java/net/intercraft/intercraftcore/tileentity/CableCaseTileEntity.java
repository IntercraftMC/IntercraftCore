package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.tileentity.TileEntity;

import java.util.LinkedList;
import java.util.List;

public class CableCaseTileEntity extends TileEntity
{
    private LinkedList<CableCaseWire> wires;
    private static final byte maxCap = 32;

    public CableCaseTileEntity()
    {
        super(IntercraftTileEntities.CABLECASE);
        wires = new LinkedList<>();
    }

    public byte getMaxCapacity()
    {
        return maxCap;
    }

    public byte getCapacity()
    {
        byte a = 0;
        for (byte i=0;i<wires.size();i++)
            a+=wires.get(i).getCapacity();
        return a;
    }


}
