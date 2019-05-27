package net.intercraft.intercraftcore.init.capabilities.radiation;

import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationBlocker;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.IRadiationEmitter;

public class Radiation implements IRadiation, IRadiationEmitter, IRadiationBlocker {

    private long exposure;

    private double multiplier = 1;

    private final int[] levels = {
            5000,
            10000,
            20000,
            40000
    };

    private int minimum = 100;

    public Radiation(long startValue) {
        this.exposure = startValue;
    }

    @Override
    public void emission(int value) {
        increase(value);
    }

    @Override
    public void multiplier(double multiplier) {
        if (multiplier <= 1 && multiplier > 0)
            this.multiplier = multiplier;
    }


    @Override
    public long getExposure() {
        return this.exposure;
    }

    @Override
    public int getLevel(int index) {
        return this.levels[index];
    }

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


        if (this.exposure >= minimum) {
            this.exposure--;
            System.out.println("Current exposure value is: "+this.exposure);
        }
    }

    private void increase(int value) {
        if (value*this.multiplier > 0)
            this.exposure += value*this.multiplier;
    }

    @Override
    public void setExposure(long value) {
        this.exposure = value;
    }

}
