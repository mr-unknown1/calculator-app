package com.example.task1;

public class Bulb {
    private boolean isOn;
    private boolean isBurned;

    public Bulb() {
        this.isOn = false;
        this.isBurned = false;
    }

    public void turnOn() {
        if (!isBurned) {
            isOn = true;
        }
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isBurned() {
        return isBurned;
    }

    // Added to allow burning the bulb as per Lamp's brighten logic
    void setBurned(boolean burned) {
        isBurned = burned;
    }
}
