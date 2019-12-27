package net.intercraft.intercraftcore.networking.messages;

import net.intercraft.intercraftcore.tileentity.ChunkLoaderBaseTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageChunkLoader
{
    private BlockPos pos;
    private boolean  canLoad;

    public MessageChunkLoader(BlockPos pos,boolean canLoad)
    {
        this.pos     = pos;
        this.canLoad = canLoad;
    }


    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            try {
                ChunkLoaderBaseTileEntity tile = (ChunkLoaderBaseTileEntity) Minecraft.getInstance().world.getTileEntity(pos);
                tile.canLoad = canLoad;

            } catch (NullPointerException err) {
                System.out.println(String.format("Could not find ChunkLoaderTileEntity at %s %s %s!",pos.getX(),pos.getY(),pos.getZ()));
            }

        });
    }

    public static void encode(MessageChunkLoader message, PacketBuffer buffer)
    {
        buffer.writeBlockPos(message.pos);
        buffer.writeBoolean(message.canLoad);
    }

    public static MessageChunkLoader decode(final PacketBuffer buffer)
    {
        return new MessageChunkLoader(buffer.readBlockPos(),buffer.readBoolean());
    }
}
