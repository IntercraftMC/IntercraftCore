package net.intercraft.intercraftcore.init;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;

public class PotionRadiation extends Potion {
    public PotionRadiation(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);


        setRegistryName("radiation");

    }
    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        System.out.println("Buuuurn!");
    }
}
