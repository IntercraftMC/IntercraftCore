package net.intercraft.intercraftcore.networking;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class IntercraftPacketHandler
{

    private static int index = 1;

    private static final ResourceLocation CHANNEL_NAME     = new ResourceLocation(Reference.MODID,"network");
    private static final String           PROTOCOL_VERSION = new ResourceLocation(Reference.MODID,"1").toString();

    /*public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Reference.MODID,"main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );*/


    public static SimpleChannel getNetworkChannel()
    {
        final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
                .clientAcceptedVersions(version -> true)
                .serverAcceptedVersions(version -> true)
                .networkProtocolVersion(()      -> PROTOCOL_VERSION)
                .simpleChannel();

        // Sync Capability Identity Hidden data message.

        channel.messageBuilder(MessageIdentityHidden.class,index)
                .encoder(MessageIdentityHidden::encode)
                .decoder(MessageIdentityHidden::decode)
                .consumer(MessageIdentityHidden::handle)
                .add(); index++;

        // Send TreeTapTileEntity data to client.

        channel.messageBuilder(MessageTreeTap.class,index)
                .encoder(MessageTreeTap::encode)
                .decoder(MessageTreeTap::decode)
                .consumer(MessageTreeTap::handle)
                .add(); index++;

        /*channel.messageBuilder(MessageStackInContainer.class,index)
                .encoder(MessageStackInContainer::encode)
                .decoder(MessageStackInContainer::decode)
                .consumer(MessageStackInContainer::handle)
                .add(); index++;*/

        // Send ChunkLoaderTileEntity data to client.

        channel.messageBuilder(MessageChunkLoader.class,index)
                .encoder(MessageChunkLoader::encode)
                .decoder(MessageChunkLoader::decode)
                .consumer(MessageChunkLoader::handle)
                .add(); index++;

        return channel;
    }

}