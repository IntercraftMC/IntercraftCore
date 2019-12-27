package net.intercraft.intercraftcore.networking.messages;

import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageTreeTap
{

    private BlockPos  pos;
    private int       volume;
    private FluidType type;



    public MessageTreeTap(BlockPos pos, int volume, FluidType type)
    {
        this.pos    = pos;
        this.volume = volume;
        this.type   = type;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {

            try {
                TreeTapTileEntity tile = (TreeTapTileEntity)Minecraft.getInstance().world.getTileEntity(pos);

                tile.volume = volume;
                tile.fluidType = type;
            } catch (NullPointerException err) {
                System.out.println(String.format("Could not find TreeTapTileEntity at %s %s %s!",pos.getX(),pos.getY(),pos.getZ()));
            }


        });
        ctx.get().setPacketHandled(true);
    }


    public static void encode(MessageTreeTap message, PacketBuffer buffer)
    {

        /*buffer.writeInt(message.pos.getX());
        buffer.writeInt(message.pos.getY());
        buffer.writeInt(message.pos.getZ());*/
        buffer.writeBlockPos(message.pos);

        buffer.writeInt(message.volume);
        buffer.writeString(message.type.getName());
    }

    public static MessageTreeTap decode(final PacketBuffer buffer)
    {
        return new MessageTreeTap(buffer.readBlockPos(),buffer.readInt(),FluidType.valueOf(buffer.readString().toUpperCase()));
    }
}
