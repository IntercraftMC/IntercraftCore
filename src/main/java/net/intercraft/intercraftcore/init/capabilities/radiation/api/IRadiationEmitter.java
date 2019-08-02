package net.intercraft.intercraftcore.init.capabilities.radiation.api;

import net.minecraft.entity.Entity;

public interface IRadiationEmitter
{

    /**
    * How much radiation the block or item emits.
    * @param entityTarget target.
    * return How much to give.
    * */

    long emitting(Entity entityTarget);


}
