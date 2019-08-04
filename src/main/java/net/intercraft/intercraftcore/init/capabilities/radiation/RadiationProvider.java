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


    /*public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == RAD_CAP;
    }*/

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        return (LazyOptional<T>) LazyOptional.of(() -> instance);
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


    /*public <R> R cast(T instance)
    {
        return (T)instance;
    }*/

    /*public static final Capability<IRadiation> RADIATION_CAPABILITY = null;

    private IRadiation instance = RADIATION_CAPABILITY.getDefaultInstance();*/


}
