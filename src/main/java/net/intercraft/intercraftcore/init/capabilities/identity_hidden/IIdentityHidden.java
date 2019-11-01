package net.intercraft.intercraftcore.init.capabilities.identity_hidden;

import net.minecraft.entity.player.PlayerEntity;

public interface IIdentityHidden
{
    boolean getHidden();

    void setHidden(PlayerEntity player, boolean hidden);
    void setHidden(boolean hidden);


}
