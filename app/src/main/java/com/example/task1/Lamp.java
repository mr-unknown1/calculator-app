package com.example.task1;

public class Lamp {
    private boolean isOn;
    private int intensity;
    private Bulb bulb;

    public Lamp() {
        this.isOn = false;
        this.intensity = 0;
        this.bulb = new Bulb();
    }

    public void turnOn() {
        if (!bulb.isBurned()) {
            isOn = true;
            if (intensity == 0) {
                intensity = 1;
            }
            bulb.turnOn();
        } else {
            // lamp must not produce light, bulb remains off
            // isOn state is a bit ambiguous if it refers to the switch or the light. 
            // "If bulb is burned: lamp must not produce light, bulb remains off"
            // Usually 'isOn' for the lamp would represent the switch state.
            // But if it doesn't produce light, maybe isOn should stay false?
            // "If bulb is not burned: set isOn = true"
            // This implies if it IS burned, we don't necessarily set isOn = true.
        }
    }

    public void turnOff() {
        isOn = false;
        intensity = 0;
        bulb.turnOff();
    }

    public void brighten() {
        if (isOn) {
            intensity++;
            if (intensity > 10) {
                bulb.setBurned(true);
                bulb.turnOff();
                isOn = false;
                intensity = 0;
            }
        }
    }

    public void dim() {
        if (isOn) {
            intensity--;
            if (intensity == 0) {
                isOn = false;
                bulb.turnOff();
            }
        }
    }

    public boolean replaceBulb() {
        if (!isOn) {
            bulb = new Bulb();
            return true;
        }
        return false;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isShining() {
        return isOn && intensity > 0 && bulb.isOn();
    }

    public boolean isBulbBurned() {
        return bulb.isBurned();
    }

    public int getIntensity() {
        return intensity;
    }
}
