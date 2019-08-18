package net.intercraft.intercraftcore.init.capabilities.ore_veins;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class OreVeinStorage implements Capability.IStorage<IOreVeins>
{
    @Override
    public INBT writeNBT(Capability<IOreVeins> capability, IOreVeins instance, Direction side)
    {
        CompoundNBT tag = new CompoundNBT();

        Arrays.asList(VeinTypes.values()).forEach(vein ->
                tag.putDouble(Reference.MODID+":weight_"+vein.getName(),instance.getWeight(vein.get())));

        return tag;
    }

    @Override
    public void readNBT(Capability<IOreVeins> capability, IOreVeins instance, Direction side, INBT nbt)
    {
        if (nbt instanceof CompoundNBT) {
            CompoundNBT tag = (CompoundNBT)nbt;
            Arrays.asList(VeinTypes.values()).forEach(vein ->
                    instance.setWeight(vein.get(),tag.getDouble(Reference.MODID+":weight_"+vein.getName())));
        }
    }

    public static class Factory implements Callable<IOreVeins>
    {
        @Override
        public IOreVeins call() throws Exception {
            return new OreVeins();
        }
    }
}