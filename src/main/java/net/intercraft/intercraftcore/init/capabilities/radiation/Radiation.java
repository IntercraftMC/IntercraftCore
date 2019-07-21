package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationBlocker;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationEmitter;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationWorld;
import net.minecraft.entity.Entity;

import java.lang.ref.WeakReference;

public class Radiation implements IRadiation, IRadiationEmitter, IRadiationBlocker, IRadiationWorld {

    private double multiplier = 1;


    private final int AbsDropRate = 4, ExpDropRate = 1;
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

        this.EXPOSURE = this.minimum;
    }

    @Override
    public void emission(int value, float distance, Entity target)
    {
        //increase(value);
        //this.EXPOSURE += Math.round(value/distance);
        increase(Math.round(value/distance));
    }

    @Override
    public void multiplier(double multiplier)
    {
        if (multiplier <= 1 && multiplier > 0)
            this.multiplier = multiplier;
    }

    @Override
    public void setMinimum(int value)
    {
        if (value >= 0)
            this.minimum = value;
    }


    @Override
    public long getExposure()
    {
        return this.EXPOSURE;
    }

    @Override
    public void setExposure(long value)
    {
        this.EXPOSURE = value;
    }

    @Override
    public long getAbsorbed()
    {
        return this.ABSORBED;
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
    public void tick()
    {

        /*if (this.ABSORBED >= this.levels[0]) {
            // Start showing Radiation poison symptoms.
        } else if (this.ABSORBED >= this.levels[1]) {
            // Not feeling very good, nausea, random damage.
        } else if (this.ABSORBED >= this.levels[2]) {
            // Same as level 2 but more.
        } else if (this.ABSORBED >= this.levels[3]) {
            // Most likely going to die. You've absorbed too much radiation and won't wear off in time.
        }*/


        if (this.ABSORBED >= this.minimum) {
            this.ABSORBED -= this.AbsDropRate;
            //System.out.println("Current EXPOSURE value is: "+this.EXPOSURE);
        }

        if (this.EXPOSURE > this.ExpDropRate) {
            this.ABSORBED += this.ExpDropRate;
            this.EXPOSURE -= this.ExpDropRate;
        }


    }

    private void increase(int value)
    {
        if (value*this.multiplier > 0)
            this.EXPOSURE += value*this.multiplier;
    }
}
