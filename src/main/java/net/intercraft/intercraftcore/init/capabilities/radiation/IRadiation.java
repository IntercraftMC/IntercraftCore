package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.minecraft.entity.Entity;

public interface IRadiation
{
    long getExposure();

    long getAbsorbed();

    float getProtectionMultiplier(Radiation.ExposureEntryPoint entryPoint);

    void addExposure(Entity entity,int valueTop,int valueMiddle,int valueBottom);

    void setExposure(long value);

    void setAbsorbed(long value);

    void setProtectionMultiplier(Radiation.ExposureEntryPoint entryPoint, float value);

    void tick(Entity entity);
}