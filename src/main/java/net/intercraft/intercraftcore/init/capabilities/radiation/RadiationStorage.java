package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

public class RadiationStorage implements Capability.IStorage<IRadiation>
{
    @Override
    public INBT writeNBT(Capability<IRadiation> capability, IRadiation instance, Direction side)
    {
        CompoundNBT tag = new CompoundNBT();
        tag.putLong(Reference.MODID+":radiationexposure",instance.getExposure());
        tag.putLong(Reference.MODID+":radiationabsorbed",instance.getAbsorbed());
        return tag;
    }

    @Override
    public void readNBT(Capability<IRadiation> capability, IRadiation instance, Direction side, INBT nbt)
    {
        if (nbt instanceof CompoundNBT) {
            CompoundNBT tag = (CompoundNBT)nbt;
            instance.setExposure(tag.getLong(Reference.MODID+":radiationexposure"));
            instance.setAbsorbed(tag.getLong(Reference.MODID+":radiationabsorbed"));
        }
    }

    public static class Factory implements Callable<IRadiation>
    {
        @Override
        public IRadiation call() throws Exception {
            return new Radiation();
        }
    }
}