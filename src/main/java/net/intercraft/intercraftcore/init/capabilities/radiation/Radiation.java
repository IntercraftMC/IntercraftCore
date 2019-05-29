package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationBlocker;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationEmitter;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationWorld;
import net.minecraft.entity.Entity;

import java.lang.ref.WeakReference;

public class Radiation implements IRadiation, IRadiationEmitter, IRadiationBlocker, IRadiationWorld {

    private double multiplier;
    public WeakReference<Entity> entity;

    private final int[] levels = {
            5000,
            10000,
            20000,
            40000
    };

    private int minimum;

    private long exposure;

    public Radiation(Entity entity) {
        this.entity = new WeakReference<>(entity);
        this.minimum = 100;
        this.multiplier = 1;

        this.exposure = this.minimum;
    }

    @Override
    public void emission(int value, float distance, Entity target) {
        //increase(value);
        //this.exposure += Math.round(value/distance);
        increase(Math.round(value/distance));
    }

    @Override
    public void multiplier(double multiplier) {
        if (multiplier <= 1 && multiplier > 0)
            this.multiplier = multiplier;
    }

    @Override
    public void setMinimum(int value) {
        if (value >= 0)
            this.minimum = value;
    }


    @Override
    public long getExposure() {
        return this.exposure;
    }

    @Override
    public void setExposure(long value) {
        this.exposure = value;
    }

    /*@Override
    public int getLevel(int index) {
        return this.levels[index];
    }*/

    @Override
    public void tick() {

        /*if (this.exposure >= this.levels[0]) {
            // Start showing Radiation poison symptoms.
        } else if (this.exposure >= this.levels[1]) {
            // Not feeling very good, nausea, random damage.
        } else if (this.exposure >= this.levels[2]) {
            // Same as level 2 but more.
        } else if (this.exposure >= this.levels[3]) {
            // Most likely going to die. You've absorbed too much radiation and won't wear off in time.
        }*/


        if (this.exposure >= this.minimum) {
            this.exposure--;
            System.out.println("Current exposure value is: "+this.exposure);
        }
    }

    private void increase(int value) {
        if (value*this.multiplier > 0)
            this.exposure += value*this.multiplier;
    }
}
