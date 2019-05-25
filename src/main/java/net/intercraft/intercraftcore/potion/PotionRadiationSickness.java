package net.intercraft.intercraftcore.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class PotionRadiationSickness extends Potion
{
    private static float oldMaxHealth;
    private static boolean maxHealthCheck = false;

    public static final float healthAmplifier = 4;

    public PotionRadiationSickness(boolean isBadEffectIn, int liquidColorIn)
    {
        super(isBadEffectIn, liquidColorIn);


        setRegistryName("radiation_sickness");

    }

    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        if (entityLivingBaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;

            if (!maxHealthCheck) {
                oldMaxHealth = player.getMaxHealth();
                maxHealthCheck = true;
            }
            IAttributeInstance health = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);


            float maxHealth = oldMaxHealth - (amplifier * healthAmplifier + healthAmplifier);


            player.setHealth(maxHealth);
            health.setBaseValue(maxHealth);
        }
    }

    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        //System.out.println("Removed bad effects.");
        if (entityLivingBaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
            IAttributeInstance health = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

            health.setBaseValue(oldMaxHealth);
        }

    }
}
