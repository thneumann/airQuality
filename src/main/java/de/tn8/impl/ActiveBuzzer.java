package de.tn8.impl;

import com.pi4j.io.gpio.*;

public class ActiveBuzzer {

    private final GpioController gpio;
    private final GpioPinDigitalOutput myBuzzer;

    public ActiveBuzzer(Pin gpioPinSwitch, GpioController gpio) {
        this.gpio = gpio;
        myBuzzer = gpio.provisionDigitalOutputPin(gpioPinSwitch, "My Buzzer", PinState.LOW);
    }

    /**
     * turns on
     */
    public void turnOn() {
        myBuzzer.high();
    }

    public boolean isActive(){
        return myBuzzer.isHigh();
    }

    /**
     * turns off
     */
    public void turnOff() {
        myBuzzer.low();
    }

    public void shutdown(){
        myBuzzer.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }

}
