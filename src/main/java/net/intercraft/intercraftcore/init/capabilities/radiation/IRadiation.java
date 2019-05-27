package net.intercraft.intercraftcore.init.capabilities.radiation;

public interface IRadiation {
    long getExposure();

    int getLevel(int index);

    void tick();

    void setExposure(long value);
}
