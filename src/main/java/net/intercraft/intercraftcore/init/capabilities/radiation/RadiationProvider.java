package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;


public class RadiationProvider implements ICapabilitySerializable<INBT>
{


    @CapabilityInject(IRadiation.class)
    public static Capability<IRadiation> RAD_CAP = null;

    private IRadiation instance = RAD_CAP.getDefaultInstance();


    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == RAD_CAP)
            return (LazyOptional<T>) LazyOptional.of(() -> instance);
        return (LazyOptional<T>) LazyOptional.empty();
    }


    @Override
    public INBT serializeNBT()
    {
        return RAD_CAP.getStorage().writeNBT(RAD_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        RAD_CAP.getStorage().readNBT(RAD_CAP, instance, null, nbt);
    }
}
