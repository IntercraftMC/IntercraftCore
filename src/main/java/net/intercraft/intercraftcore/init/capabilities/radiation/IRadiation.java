package net.intercraft.intercraftcore.init.capabilities.radiation;

public interface IRadiation {
    long getExposure();

    int getLevel(int index);

    void tick();

    void increase(int value);

    void setExposure(long value);
}
