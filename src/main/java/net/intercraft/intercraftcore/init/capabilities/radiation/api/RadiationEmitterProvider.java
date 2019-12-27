package net.intercraft.intercraftcore.init.capabilities.radiation.api;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.concurrent.Callable;


public class RadiationEmitterProvider implements ICapabilityProvider
{

    @CapabilityInject(IRadiationEmitter.class)
    public static Capability<IRadiationEmitter> RAE_CAP = null;

    private IRadiationEmitter instance;

    public RadiationEmitterProvider(int intensity)
    {
        instance = new RadiationEmitter(intensity);
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == RAE_CAP)
            return (LazyOptional<T>) LazyOptional.of(() -> instance);
        return (LazyOptional<T>) LazyOptional.empty();
    }


    public static class Factory implements Callable<IRadiationEmitter>
    {
        @Override
        public IRadiationEmitter call() throws Exception
        {
            return new RadiationEmitter();
        }
    }
}
