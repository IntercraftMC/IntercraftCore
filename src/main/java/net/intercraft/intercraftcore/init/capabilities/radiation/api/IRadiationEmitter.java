package net.intercraft.intercraftcore.init.capabilities.radiation.api;

import net.minecraft.entity.Entity;

public interface IRadiationEmitter {

    /*
    * How much radiation the block or item emits.
    * @param How much to apply to the target.
    * @return How much to give.
    * */
    void emission(int value, float distance, Entity target);
}
