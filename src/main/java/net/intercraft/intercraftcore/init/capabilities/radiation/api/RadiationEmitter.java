package net.intercraft.intercraftcore.init.capabilities.radiation.api;

import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.Entity;

public class RadiationEmitter implements IRadiationEmitter
{

    private int intensity;

    public RadiationEmitter(int intensity)
    {
        this.intensity = intensity;
    }

    public RadiationEmitter()
    {
        this(0);
    }

    @Override
    public int getIntensity()
    {
        return intensity;
    }

    @Override
    public void setIntensity(int intensity)
    {
        this.intensity = intensity;
    }

    @Override
    public void addToEntity(Entity entity, float distanceTop, float distanceMiddle, float distanceBottom)
    {
        entity.getCapability(RadiationProvider.RAD_CAP).ifPresent(cap -> {

            final int top = Math.round(intensity/Math.max(1,distanceTop)), middle = Math.round(intensity/Math.max(1,distanceTop)), bottom = Math.round(intensity/Math.max(1,distanceTop));

            cap.addExposure(entity,top,middle,bottom);
        });
    }

    @Override
    public void addToEntity(Entity entity)
    {
        entity.getCapability(RadiationProvider.RAD_CAP).ifPresent(cap -> cap.addExposure(entity,intensity,intensity,intensity));
    }
}
