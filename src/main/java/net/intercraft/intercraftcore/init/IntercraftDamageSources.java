package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;

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

        @Override
        public boolean isDamageAbsolute()
        {
            return true;
        }
    };


    public static boolean killPlayer(PlayerEntity player, DamageSource source)
    {
        player.attackEntityFrom(source,player.getAbsorptionAmount() + player.getHealth());

        if (source == DISOBEDIENCE) {
            LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(player.world, player.posX, player.posY, player.posZ, true);
            if (player.world instanceof ServerWorld)
                ((ServerWorld) player.world).addLightningBolt(lightningBoltEntity);
        }

        return !player.isAlive();
    }
}
