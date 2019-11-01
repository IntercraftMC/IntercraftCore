package net.intercraft.intercraftcore.init.capabilities.identity_hidden;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.networking.MessageIdentityHidden;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.PacketDistributor;

public class IdentityHidden implements IIdentityHidden
{

    private boolean hidden;


    public IdentityHidden()
    {
        this.hidden = false;
    }


    @Override
    public boolean getHidden()
    {
        return hidden;
    }

    @Override
    public void setHidden(PlayerEntity player,boolean hidden) // For changing and sending new data to all trackers.
    {
        this.hidden = hidden;
        IntercraftCore.NETWORK.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new MessageIdentityHidden(player.getEntityId(), hidden));
    }

    @Override
    public void setHidden(boolean hidden)
    {
        this.hidden = hidden; // For internal reading.
    }

    public static void updatePlayerNames(PlayerEntity playerEntity, DimensionType dimension) // When player joins, respawns or changes dimension from the point of @playerEntity.
    {
        IIdentityHidden hiddenTarget = playerEntity.getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
        IntercraftCore.NETWORK.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerEntity), new MessageIdentityHidden(playerEntity.getEntityId(), hiddenTarget.getHidden()));

        for (PlayerEntity player : playerEntity.getServer().getWorld(dimension).getPlayers()) {
            IIdentityHidden hidden = player.getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);
            IntercraftCore.NETWORK.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity), new MessageIdentityHidden(player.getEntityId(),hidden.getHidden()));
        }
    }

}