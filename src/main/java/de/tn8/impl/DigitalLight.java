package de.tn8.impl;

import com.pi4j.io.gpio.*;

/**
 * For this class you need to have a set up a with a single light.
 * E.g. a LED with a 5 V input and 220 Ohm Resistor.
 */
public class DigitalLight {


    private Pin gpioPin;
    private final GpioController gpio;
    private final GpioPinDigitalOutput myLed;

    /**
     * @param gpioPin The Pin that is used. Please use to the wiringPI numbering http://wiringpi.com/pins/
     * @param gpio GPIO Controller
     */
    public DigitalLight(Pin gpioPin, GpioController gpio) {
        this.gpioPin = gpioPin;
        this.gpio = gpio;
        myLed = gpio.provisionDigitalOutputPin(gpioPin, "My LED", PinState.LOW);
    }

    /**
     * @return GPIO controller
     */
    public GpioController getGpio() {
        return gpio;
    }

    /**
     * @return the digital output pin of the led
     */
    public GpioPinDigitalOutput getMyLed() {
        return myLed;
    }

    /**
     * @return get the PIN
     */
    public Pin getGpioPin() {
        return gpioPin;
    }

    /**
     * @param gpioPin Pin to be set for the Output Pin
     */
    public void setGpioPin(Pin gpioPin) {
        this.gpioPin = gpioPin;
    }

    /**
     * turns on the light
     */
    public void turnOn() {
        myLed.high();
    }

    /**
     * turns of the light
     */
    public void turnOff() {
        myLed.low();
    }

    /**
     * makes sure that light is turned off before shutdown
     */
    public void shutdown() {
        myLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }

}
