package de.tn8.impl;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import de.tn8.abstr.PWM;

public class DimmableLight extends PWM {


    private Pin gpioPin;
    private final GpioController gpio;
    private final GpioPinPwmOutput myLed;

    public DimmableLight(Pin gpioPin, GpioController gpio) {
        this.gpioPin = gpioPin;
        this.gpio = gpio;
        if(gpioPin == RaspiPin.GPIO_01){
            myLed = gpio.provisionPwmOutputPin(gpioPin, "My LED");
        }else{
            myLed = gpio.provisionSoftPwmOutputPin(gpioPin, "My LED");
        }
        System.out.println("New Dimmable light with range: ");
        myLed.setPwmRange(100);
    }

    public void setDuty(int duty){
        myLed.setPwm(duty);
    }

    public void shutdown(){
        myLed.setPwm(0);
        myLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }


}
