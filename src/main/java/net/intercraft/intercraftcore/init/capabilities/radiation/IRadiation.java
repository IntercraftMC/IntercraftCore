package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.minecraft.entity.Entity;

public interface IRadiation {
    long getExposure();

    void setExposure(long value);

    long getAbsorbed();

    void setAbsorbed(long value);

    void increase(int value);

    void tick(Entity entity);
}