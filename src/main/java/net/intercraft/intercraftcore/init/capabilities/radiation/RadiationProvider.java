package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;


public class RadiationProvider implements ICapabilitySerializable<INBTBase>
{


    @CapabilityInject(IRadiation.class)
    public static final Capability<IRadiation> RAD_CAP = null;

    private IRadiation instance = RAD_CAP.getDefaultInstance();


    /*public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == RAD_CAP;
    }*/

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing)
    {
        return (LazyOptional<T>) LazyOptional.of(() -> {
            return new Radiation();
        });
    }


    @Override
    public INBTBase serializeNBT()
    {
        return RAD_CAP.getStorage().writeNBT(RAD_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(INBTBase nbt)
    {
        RAD_CAP.getStorage().readNBT(RAD_CAP, this.instance, null, nbt);
    }


    /*public <R> R cast(T instance)
    {
        return (T)instance;
    }*/

    /*public static final Capability<IRadiation> RADIATION_CAPABILITY = null;

    private IRadiation instance = RADIATION_CAPABILITY.getDefaultInstance();*/


}
