package com.example.task1;

import java.util.ArrayList;
import java.util.List;

public class LampTestRunner {
    public static List<String> runTests() {
        List<String> results = new ArrayList<>();
        Lamp lamp = new Lamp();

        // 1. Turn lamp on and off
        results.add("Executing: lamp.turnOn()");
        lamp.turnOn();
        results.add("  - Result: isOn=" + lamp.isOn() + ", isShining=" + lamp.isShining() + ", intensity=" + lamp.getIntensity());
        results.add("Test 1a (Turn On): " + (lamp.isOn() && lamp.isShining() ? "PASS" : "FAIL"));
        
        results.add("Executing: lamp.turnOff()");
        lamp.turnOff();
        results.add("  - Result: isOn=" + lamp.isOn() + ", isShining=" + lamp.isShining() + ", intensity=" + lamp.getIntensity());
        results.add("Test 1b (Turn Off): " + (!lamp.isOn() && !lamp.isShining() && lamp.getIntensity() == 0 ? "PASS" : "FAIL"));

        // 2. Brighten to 10
        results.add("Executing: lamp.turnOn(), then brighten 9 times");
        lamp.turnOn();
        for (int i = 0; i < 9; i++) lamp.brighten();
        results.add("  - Result: intensity=" + lamp.getIntensity() + ", isShining=" + lamp.isShining());
        results.add("Test 2 (Brighten to 10): " + (lamp.getIntensity() == 10 && lamp.isShining() ? "PASS" : "FAIL"));

        // 3. Attempt to brighten above 10 -> bulb burns -> lamp turns off
        results.add("Executing: lamp.brighten() when intensity is 10");
        lamp.brighten();
        results.add("  - Result: isBulbBurned=" + lamp.isBulbBurned() + ", isOn=" + lamp.isOn() + ", intensity=" + lamp.getIntensity());
        results.add("Test 3 (Brighten above 10): " + (lamp.isBulbBurned() && !lamp.isOn() && !lamp.isShining() && lamp.getIntensity() == 0 ? "PASS" : "FAIL"));

        // 4. Dim to 0 -> lamp turns off
        results.add("Executing: Reset bulb, turnOn (int=1), then dim()");
        lamp.replaceBulb(); // reset bulb
        lamp.turnOn(); // intensity 1
        lamp.dim();
        results.add("  - Result: isOn=" + lamp.isOn() + ", intensity=" + lamp.getIntensity());
        results.add("Test 4 (Dim to 0): " + (!lamp.isOn() && lamp.getIntensity() == 0 ? "PASS" : "FAIL"));

        // 5. Replace bulb while lamp is off -> success
        results.add("Executing: lamp.replaceBulb() while off");
        boolean success = lamp.replaceBulb();
        results.add("  - Result: success=" + success);
        results.add("Test 5 (Replace bulb off): " + (success ? "PASS" : "FAIL"));

        // 6. Attempt to replace bulb while lamp is on -> failure
        results.add("Executing: lamp.turnOn(), then lamp.replaceBulb()");
        lamp.turnOn();
        boolean replaceFailure = !lamp.replaceBulb();
        results.add("  - Result: Replace prevented=" + replaceFailure);
        results.add("Test 6 (Replace bulb on): " + (replaceFailure ? "PASS" : "FAIL"));

        // 7. Turn on lamp with burned bulb -> no light produced
        results.add("Executing: Burn bulb, turnOff, then turnOn");
        lamp.turnOff();
        lamp.turnOn(); // reset intensity
        for (int i = 0; i < 10; i++) lamp.brighten(); // burn it
        lamp.turnOn(); // try to turn on burned
        results.add("  - Result: isOn=" + lamp.isOn() + ", isShining=" + lamp.isShining());
        results.add("Test 7 (Turn on burned): " + (!lamp.isShining() && !lamp.isOn() ? "PASS" : "FAIL"));

        return results;
    }
}
