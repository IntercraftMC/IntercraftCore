package net.intercraft.intercraftcore.init.capabilities.radiation;

public interface IRadiation {
    long getExposure();

    void setExposure(long value);

    void tick();
}