package net.intercraft.intercraftcore.networking;

import net.intercraft.intercraftcore.api.FluidType;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageTreeTap
{

    private int volume = -1;
    private FluidType type ;


    public MessageTreeTap()
    {

    }

    public MessageTreeTap(int volume)
    {
        this.volume = volume;
    }

    public MessageTreeTap(FluidType type)
    {
        this.type = type;
    }

    public MessageTreeTap(int volume, FluidType type)
    {
        this.volume = volume;
        this.type = type;
    }

    public MessageTreeTap(PacketBuffer buf)
    {
        int v = -1;
        FluidType f;

        try {
            v = buf.getInt(1);
        } catch (IndexOutOfBoundsException e) { System.out.println("Fack...\n"+e); }

        f = FluidType.valueOf(buf.readString(16).toUpperCase());

        this.type = f;
        this.volume = v != -1 ? v : this.volume;


    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null)return;


        });
        ctx.get().setPacketHandled(true);
    }





    public void encode(PacketBuffer buf)
    {
        if (volume != -1)
            buf.writeInt(volume);
        if (type != null)
            buf.writeString(type.getName());
    }



    /*@Override
    public void toBytes(PacketBuffer buf)
    {
        if (volume != -1)
            buf.writeInt(volume);
        if (type != null)
            buf.writeString(type.getSymbol());
    }

    @Override
    public Object fromBytes(PacketBuffer buf)
    {
        int v;
        FluidType f;

        try {
            v = buf.getInt(1);
        } catch (IndexOutOfBoundsException e) { System.out.println("Fack...\n"+e); }

        f = FluidType.valueOf(buf.readString(16).toUpperCase());

        return this;
    }*/

    /*@Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {

    }*/
}
