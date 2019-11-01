package net.intercraft.intercraftcore.init.capabilities.identity_hidden;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;


public class IdentityHiddenProvider implements ICapabilitySerializable<INBT>
{
    private final PlayerEntity player;

    public IdentityHiddenProvider(PlayerEntity player)
    {
        this.player = player;
    }

    @CapabilityInject(IIdentityHidden.class)
    public static Capability<IIdentityHidden> HID_CAP = null;

    private IIdentityHidden instance = HID_CAP.getDefaultInstance();


    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == HID_CAP)
            return (LazyOptional<T>) LazyOptional.of(() -> instance);
        return (LazyOptional<T>) LazyOptional.empty();
    }


    @Override
    public INBT serializeNBT()
    {
        return HID_CAP.getStorage().writeNBT(HID_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        HID_CAP.getStorage().readNBT(HID_CAP, instance, null, nbt);
    }
}
