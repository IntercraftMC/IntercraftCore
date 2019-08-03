package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.minecraft.tileentity.TileEntity;

public interface IRadiation {
    long getExposure();

    void setExposure(long value);

    long getAbsorbed();

    void setAbsorbed(long value);

    void tick(TileEntity entity);
}