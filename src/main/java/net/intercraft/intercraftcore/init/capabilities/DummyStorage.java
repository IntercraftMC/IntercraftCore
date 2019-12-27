package net.intercraft.intercraftcore.init.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * A dummy storage that does nothing because a capability always needs a storage when registering for some reason.
 */
public class DummyStorage<T> implements Capability.IStorage<T>
{
    @Nullable
    @Override
    public INBT writeNBT(Capability<T> capability, T instance, Direction side){return null;}

    @Override
    public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt){}
}