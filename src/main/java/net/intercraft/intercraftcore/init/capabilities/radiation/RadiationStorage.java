package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

public class RadiationStorage implements Capability.IStorage<IRadiation> {
    @Override
    public INBTBase writeNBT(Capability<IRadiation> capability, IRadiation instance, EnumFacing side) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setLong(Reference.MODID+":radiationexposure",instance.getExposure());
        return tag;
    }

    @Override
    public void readNBT(Capability<IRadiation> capability, IRadiation instance, EnumFacing side, INBTBase nbt) {
        if (nbt instanceof NBTTagCompound) {
            NBTTagCompound tag = (NBTTagCompound)nbt;
            instance.setExposure(tag.getLong(Reference.MODID+":radiationexposure"));
        }
    }

    public static class Factory implements Callable<IRadiation> {
        @Override
        public IRadiation call() throws Exception {
            return new Radiation(null);
        }
    }
}
