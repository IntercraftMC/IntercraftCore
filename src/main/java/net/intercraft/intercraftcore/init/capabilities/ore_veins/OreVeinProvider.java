package net.intercraft.intercraftcore.init.capabilities.ore_veins;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class OreVeinProvider implements ICapabilitySerializable<INBT>
{

    /*public OreVeinProvider(Chunk chunk)
    {
        chunk.markDirty();
    }*/


    @CapabilityInject(IOreVeins.class)
    public static Capability<IOreVeins> VEIN_CAP = null;

    private IOreVeins instance = VEIN_CAP.getDefaultInstance();

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == VEIN_CAP)
            return (LazyOptional<T>) LazyOptional.of(() -> instance);
        return (LazyOptional<T>) LazyOptional.empty();
    }


    @Override
    public INBT serializeNBT()
    {
        return VEIN_CAP.getStorage().writeNBT(VEIN_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        VEIN_CAP.getStorage().readNBT(VEIN_CAP, instance, null, nbt);
    }
}
