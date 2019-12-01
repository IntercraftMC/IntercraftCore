package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.util.DamageSource;

public class IntercraftDamageSources
{
    public static final DamageSource RADIATION = new DamageSource(Reference.MODID+".radiation")
    {
        @Override
        public boolean isUnblockable()
        {
            return true;
        }

        @Override
        public boolean canHarmInCreative()
        {
            return false;
        }


    };

    public static final DamageSource RADIATION_BURN = new DamageSource(Reference.MODID+".radiation_burn")
    {

        @Override
        public boolean isUnblockable()
        {
            return false;
        }

        @Override
        public boolean canHarmInCreative()
        {
            return false;
        }


    };

    public static final DamageSource DISOBEDIENCE = new DamageSource(Reference.MODID+".disobedience")
    {
        @Override
        public boolean isUnblockable()
        {
            return true;
        }

        @Override
        public boolean canHarmInCreative()
        {
            return true;
        }


    };
}
