package net.intercraft.intercraftcore.init.capabilities;

public interface IRadiation {
    long getExposure();

    int getLevel(int index);

    void tick();

    void increase(int value);
}
