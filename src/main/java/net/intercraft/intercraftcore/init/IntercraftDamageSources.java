package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.util.DamageSource;

public class IntercraftDamageSources {
    public static final DamageSource RADIATION = new DamageSource(Reference.MODID+".radiation") {
        @Override
        public boolean isUnblockable() {
            return true;
        }

        @Override
        public boolean canHarmInCreative() {
            return false;
        }


    };
}
