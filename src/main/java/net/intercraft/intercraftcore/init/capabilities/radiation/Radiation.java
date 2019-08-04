package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.init.IntercraftPotions;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationBlocker;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationEmitter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;

public class Radiation implements IRadiation, IRadiationEmitter, IRadiationBlocker
{

    private final int AbsDropRate = 1, ExpDropRate = 3;
    private final int[] levels = {
            5000,
            10000,
            20000,
            40000
    };

    private int minimum = 100;


    private long EXPOSURE;
    private long ABSORBED;

    public Radiation()
    {

        this.ABSORBED = this.minimum;
    }





    @Override
    public long getExposure()
    {
        return this.EXPOSURE;
    }

    @Override
    public long getAbsorbed()
    {
        return this.ABSORBED;
    }

    @Override
    public void setExposure(long value)
    {
        this.EXPOSURE = value;
    }

    @Override
    public void setAbsorbed(long value)
    {
        this.ABSORBED = value;
    }




    /*@Override
    public int getLevel(int index) {
        return this.levels[index];
    }*/

    @Override
    public void tick(Entity entity)
    {

        /*if (this.ABSORBED >= this.levels[0]) {
            // Start showing Radiation poison symptoms.
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,0));
        } else if (this.ABSORBED >= this.levels[1]) {
            // Not feeling very good, nausea, random damage.
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,1));
        } else if (this.ABSORBED >= this.levels[2]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,2));
            // Same as level 2 but more.
        } else if (this.ABSORBED >= this.levels[3]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(IntercraftPotions.RADIATION,60,3));
            // Most likely going to die. You've absorbed too much radiation and won't wear off in time.
        }*/

        // TODO Not have each level give a certain amplifier of Radiation Poison but give special render shader for different levels and only give RP at the most severe levels and is most likely lethal.
        for (int i=this.levels.length-1;i>=0;i--) {
            if (this.ABSORBED >= this.levels[i]) {


                EffectInstance effect = ((LivingEntity) entity).getActivePotionEffect(IntercraftPotions.RADIATION);

                if (effect != null) {
                    if (effect.getDuration() == 20)
                        applyRad(entity,500,i);
                } else {
                    applyRad(entity, 500, i);
                }
                break;
            }
        }


        if (this.ABSORBED >= this.minimum) {
            this.ABSORBED -= this.AbsDropRate;
        }

        if (this.EXPOSURE > this.ExpDropRate) {
            this.ABSORBED += this.ExpDropRate;
            this.EXPOSURE -= this.ExpDropRate;
        }


        increaseExposure(entity, emitting(entity));



    }

    @Override
    public long emitting(Entity entityTarget)
    {
        return 0;
    }

    @Override
    public double blocking()
    {
        return 1;
    }


    private void increaseExposure(Entity entity, long value)
    {
        /**
         * TODO Create an algorithm to decide how much goes to @ABSORBED and @EXPOSURE and how much to convert to fire damage.
         *
         * Ex (pseudo code).
         * @param value = 300 * blocking();
         * EXPOSURE += 250;
         * ABSORBED += 50;
         * @param value = 1200 * blocking();
         * EXPOSURE += 1000;
         * ABSORBED += 150;
         * fireDamage(50);
         */
    }


    private void applyRad(Entity entity, int duration, int level)
    {
        ((LivingEntity) entity).addPotionEffect(new EffectInstance(IntercraftPotions.RADIATION, duration, level));

    }
}
