package net.intercraft.intercraftcore.init.capabilities.radiation;

public interface IRadiation {
    long getExposure();

    void setExposure(long value);

    long getAbsorbed();

    void setAbsorbed(long value);

    void tick();
}