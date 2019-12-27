package net.intercraft.intercraftcore.init.capabilities.radiation.api;

import net.minecraft.entity.Entity;

public interface IRadiationEmitter
{
    int getIntensity();

    void setIntensity(int intensity);

    void addToEntity(Entity entity, float distanceTop, float distanceMiddle, float distanceBottom);
    void addToEntity(Entity entity);
}
