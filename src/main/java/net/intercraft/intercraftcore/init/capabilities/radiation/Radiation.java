package net.intercraft.intercraftcore.init.capabilities.radiation;

public class Radiation implements IRadiation {

    private long exposure;

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
            System.out.println(this.exposure);
        }
    }

    @Override
    public void increase(int value) {
        if (value > 0)
            this.exposure += value;
    }

    @Override
    public void setExposure(long value) {
        this.exposure = value;
    }

}
