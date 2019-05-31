package net.intercraft.intercraftcore.potion;

import net.intercraft.intercraftcore.init.IntercraftDamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionRadiationSickness extends Potion  {

    private static final float healthAmplifier = 4;

    private static float oldHealth = -1;

    public PotionRadiationSickness(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);


        setRegistryName("radiation_sickness");
        setIconIndex(1,0);

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = 200 >> amplifier;
        if (j > 0) {
            return duration % j == 0;
        } else { return true; }

    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.attackEntityFrom(IntercraftDamageSources.RADIATION,1);

        (entityLivingBaseIn).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 5*20));
    }

    @Override
    public void renderInventoryEffect(PotionEffect effect, net.minecraft.client.gui.Gui gui, int x, int y, float z) {

    }

    @Override
    public void renderHUDEffect(PotionEffect effect, net.minecraft.client.gui.Gui gui, int x, int y, float z, float alpha) {

        //TextureAtlasSprite sprite = new TextureAtlasSprite(new ResourceLocation(Reference.MODID,""),16,16);


        //gui.drawTexturedModalRect(x,y,"");
    }

    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;

            IAttributeInstance health = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

            if (oldHealth == -1)
                oldHealth = player.getMaxHealth();

            float newMaxHealth = player.getMaxHealth() - (amplifier * healthAmplifier + healthAmplifier);

            health.setBaseValue(newMaxHealth);


            if (player.getHealth() - newMaxHealth > 0)
                player.getEntity().attackEntityFrom(IntercraftDamageSources.RADIATION,player.getHealth() - newMaxHealth);
        }
    }

    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
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
