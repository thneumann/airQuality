package de.tn8.impl;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import de.tn8.abstr.PWM;
import de.tn8.monitor.AirQualityMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DimmableLight extends PWM {

    private static final Logger logger = LogManager.getLogger(DimmableLight.class);


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
        logger.info("New Dimmable light with range: ");
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
