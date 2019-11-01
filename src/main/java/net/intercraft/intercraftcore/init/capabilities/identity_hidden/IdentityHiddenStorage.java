package net.intercraft.intercraftcore.init.capabilities.identity_hidden;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

public class IdentityHiddenStorage implements Capability.IStorage<IIdentityHidden>
{


    @Override
    public INBT writeNBT(Capability<IIdentityHidden> capability, IIdentityHidden instance, Direction side)
    {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean(Reference.MODID+":identity_hidden",instance.getHidden());
        return tag;
    }

    @Override
    public void readNBT(Capability<IIdentityHidden> capability, IIdentityHidden instance, Direction side, INBT nbt)
    {
        if (nbt instanceof CompoundNBT) {
            CompoundNBT tag = (CompoundNBT)nbt;
            instance.setHidden(tag.getBoolean(Reference.MODID+":identity_hidden"));
        }
    }

    public static class Factory implements Callable<IIdentityHidden>
    {

        @Override
        public IIdentityHidden call() throws Exception
        {
            return new IdentityHidden();
        }
    }
}