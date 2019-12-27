package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.init.IntercraftPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IStringSerializable;

public class Radiation implements IRadiation//, IRadiationEmitter, IRadiationBlocker
{

    private static final short AbsDropRate = 1, ExpDropRate = 3;
    public static final int tickrate = 5;

    private float
            protectionTop,
            protectionMiddle,
            protectionBottom;

    private final int[] levelThreshold = {
            5000,
            10000,
            15000,
            20000,
            25000,
            30000
    };

    private int minimum = 100;


    private long EXPOSURE,ABSORBED;

    public Radiation()
    {
        ABSORBED = minimum;

        protectionTop    = 0;
        protectionMiddle = 0;
        protectionBottom = 0;

    }





    @Override
    public long getExposure()
    {
        return EXPOSURE;
    }

    @Override
    public long getAbsorbed()
    {
        return ABSORBED;
    }

    @Override
    public float getProtectionMultiplier(Radiation.ExposureEntryPoint entryPoint)
    {
        switch (entryPoint) {
            case TOP:
                return protectionTop;
            case BOTTOM:
                return protectionBottom;
            default:
                return protectionMiddle;
        }
    }

    @Override
    public void setExposure(long value)
    {
        EXPOSURE = value;
    }

    @Override
    public void setProtectionMultiplier(Radiation.ExposureEntryPoint entryPoint,float value)
    {
        switch (entryPoint) {
            case TOP:
                protectionTop = value;
                break;
            case BOTTOM:
                protectionBottom = value;
                break;
            default:
                protectionMiddle = value;
        }
    }

    @Override
    public void setAbsorbed(long value)
    {
        ABSORBED = value;
    }

    @Override
    public void addExposure(Entity entity,int valueTop,int valueMiddle,int valueBottom)
    {
        if (valueTop < 0) throw new IllegalArgumentException("Can't add a negative exposure value!");

        valueTop    *= 1 - protectionTop;
        valueMiddle *= 1 - protectionMiddle;
        valueBottom *= 1 - protectionBottom;

        final int sum = valueTop+valueMiddle+valueBottom;
        float toExposure = sum * 0.95f, toAbsorbed = sum * 0.03f, toFire = sum * 0.02f;

        EXPOSURE += Math.round(toExposure);
        ABSORBED += Math.round(toAbsorbed);
        if (toFire > 60)
            entity.setFire(Math.round(toFire / 20));

    }

    @Override
    public void tick(Entity entity)
    {

        /*if (this.ABSORBED >= this.levelThreshold[0]) {
            // Start showing Radiation poison symptoms.
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,0));
        } else if (this.ABSORBED >= this.levelThreshold[1]) {
            // Not feeling very good, nausea, random damage.
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,1));
        } else if (this.ABSORBED >= this.levelThreshold[2]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,2));
            // Same as level 2 but more.
        } else if (this.ABSORBED >= this.levelThreshold[3]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,3));
            // Most likely going to die. You've absorbed too much radiation and won't wear off in time.
        }*/

        // TODO Not have each level give a certain amplifier of Radiation Poison but give special render shader for different levelThreshold and only give RP at the most severe levelThreshold and is most likely lethal.
        for (int i=this.levelThreshold.length-1;i>=0;i--) {
            if (this.ABSORBED >= this.levelThreshold[i]) {


                EffectInstance effect = ((LivingEntity) entity).getActivePotionEffect(IntercraftPotions.RADIATION);

                if (effect != null) {
                    if (effect.getDuration() <= 20)
                        applyRad(entity,500,i);
                } else {
                    applyRad(entity, 500, i);
                }
                break;
            }
        }


        if (this.ABSORBED > this.minimum) {
            this.ABSORBED -= AbsDropRate;
        }

        if (this.EXPOSURE > ExpDropRate) {
            this.ABSORBED += ExpDropRate;
            this.EXPOSURE -= ExpDropRate;
        }



    }


    private static void applyRad(Entity entity, int duration, int level)
    {
        ((LivingEntity) entity).addPotionEffect(new EffectInstance(IntercraftPotions.RADIATION, duration, level));
    }

    public enum ExposureEntryPoint implements IStringSerializable
    {
        TOP("top"),
        MIDDLE("middle"),
        BOTTOM("bottom");

        private final String name;

        ExposureEntryPoint(String name)
        {
            this.name = name;
        }

        @Override
        public String getName()
        {
            return name;
        }
    }

}
