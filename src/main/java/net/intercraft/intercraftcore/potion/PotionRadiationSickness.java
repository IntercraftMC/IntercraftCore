package net.intercraft.intercraftcore.potion;

import net.intercraft.intercraftcore.init.IntercraftDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class PotionRadiationSickness extends Effect
{

    private static final float healthAmplifier = 4;

    private static float oldHealth = -1;

    public PotionRadiationSickness() {
        super(EffectType.HARMFUL,0x077a07);


        setRegistryName("radiation_sickness");
        //setIconIndex(1,0);

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = 200 >> amplifier;
        if (j > 0) {
            return duration % j == 0;
        } else { return true; }

    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        entityLivingBaseIn.attackEntityFrom(IntercraftDamageSources.RADIATION,1);

        (entityLivingBaseIn).addPotionEffect(new EffectInstance(Effects.NAUSEA, 5*20));
    }



    public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;

            IAttributeInstance health = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

            if (oldHealth == -1)
                oldHealth = player.getMaxHealth();

            float newMaxHealth = player.getMaxHealth() - (amplifier * healthAmplifier + healthAmplifier);

            health.setBaseValue(newMaxHealth);


            if (player.getHealth() - newMaxHealth > 0)
                player.getEntity().attackEntityFrom(IntercraftDamageSources.RADIATION,player.getHealth() - newMaxHealth);
        }
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            IAttributeInstance health = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

            float newMaxHealth = player.getMaxHealth() + (amplifier * healthAmplifier + healthAmplifier);

            if (oldHealth != -1)
                newMaxHealth = oldHealth;
            else
                System.out.println("Guessed how much health to give!");


            oldHealth = -1;
            health.setBaseValue(newMaxHealth);

        }

    }
}
