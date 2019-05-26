package net.intercraft.intercraftcore.potion;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionRadiationSickness extends Potion  {

    public static final float healthAmplifier = 4;

    public PotionRadiationSickness(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);


        setRegistryName("radiation_sickness");
        setIconIndex(1,0);

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


            float newMaxHealth = player.getMaxHealth() - (amplifier * healthAmplifier + healthAmplifier);

            health.setBaseValue(newMaxHealth);
            player.setHealth(newMaxHealth);
        }
    }

    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
            IAttributeInstance health = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

            float newMaxHealth = player.getMaxHealth() + (amplifier * healthAmplifier + healthAmplifier);

            health.setBaseValue(newMaxHealth);

        }

    }
}
