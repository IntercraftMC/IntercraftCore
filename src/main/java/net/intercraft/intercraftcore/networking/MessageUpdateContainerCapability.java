package net.intercraft.intercraftcore.networking;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class MessageUpdateContainerCapability<T, DATA>
{
    private final Capability<T> capability;
    private final Direction side;

    public MessageUpdateContainerCapability(Capability<T> capability, Direction side)
    {
        this.capability = capability;
        this.side = side;
    }


    public abstract void updateCapabilityData();

    public static abstract class Instance<T,DATA,MESSAGE extends MessageUpdateContainerCapability<T,DATA>>
    {
        public final void handle(final Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() -> {

            });
            ctx.get().setPacketHandled(true);
        }
    }


    /*protected MessageUpdateContainerCapability(final Capability<T> capability, Direction side)
    {
        this.capability = capability;
        this.side = side;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {

    }

    public static void encode(MessageUpdateContainerCapability message, PacketBuffer buffer)
    {

    }

    public static MessageUpdateContainerCapability decode(final PacketBuffer buffer)
    {
        //return new MessageUpdateContainerCapability()
    }


    protected abstract DATA readCapabilityData(final ByteBuf buf);

    protected abstract void writeCapabilityData(final ByteBuf buf, DATA data);

    public abstract static class Handler<T, DATA, MESSAGE extends MessageUpdateContainerCapability<T, DATA>>
    {

    }*/


}
