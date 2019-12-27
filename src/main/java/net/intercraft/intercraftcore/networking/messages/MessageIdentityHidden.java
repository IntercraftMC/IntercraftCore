package net.intercraft.intercraftcore.networking.messages;

import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IIdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageIdentityHidden
{

    private final boolean hidden;
    private final int playerID;

    public MessageIdentityHidden(int playerID, boolean hidden)
    {
        this.hidden = hidden;
        this.playerID = playerID;
    }



    public static void encode(final MessageIdentityHidden message, final PacketBuffer buffer)
    {
        buffer.writeInt(message.playerID);
        buffer.writeBoolean(message.hidden);
    }


    public static MessageIdentityHidden decode(final PacketBuffer buffer)
    {
        return new MessageIdentityHidden(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(MessageIdentityHidden message, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {

            if (message.playerID == Minecraft.getInstance().player.getEntityId()) return;

            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().player.world.getEntityByID(message.playerID);

            IIdentityHidden hidden = player.getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
            hidden.setHidden(message.hidden);



        });
        ctx.get().setPacketHandled(true);
    }
}
