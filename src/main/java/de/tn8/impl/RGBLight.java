package de.tn8.impl;

import com.pi4j.io.gpio.*;
import de.tn8.abstr.PWM;

import java.util.Random;

public class RGBLight extends PWM {


    private Pin gpioPin;
    private final GpioController gpio;
    private final GpioPinPwmOutput redLed;
    private final GpioPinPwmOutput blueLed;
    private final GpioPinPwmOutput greenLed;

    public RGBLight(Pin gpioPinRed, Pin gpioPinBlue, Pin gpioPinGreen, GpioController gpio) {
        this.gpioPin = gpioPin;
        this.gpio = gpio;
        if(RaspiPin.GPIO_01 == gpioPinRed){
            redLed = gpio.provisionPwmOutputPin(gpioPinRed, "My red LED");
        }else{
            redLed = gpio.provisionSoftPwmOutputPin(gpioPinRed, "My red LED");
        }
        if(RaspiPin.GPIO_01 == gpioPinBlue){
            blueLed = gpio.provisionPwmOutputPin(gpioPinBlue, "My blue LED");
        }else{
            blueLed = gpio.provisionSoftPwmOutputPin(gpioPinBlue, "My blue LED");
        }
        if(RaspiPin.GPIO_01 == gpioPinGreen){
            greenLed = gpio.provisionPwmOutputPin(gpioPinGreen, "My green LED");
        }else{
            greenLed = gpio.provisionSoftPwmOutputPin(gpioPinGreen, "My green LED");
        }
    }

    public void setRedColor(){
        setRedDuty(0);
        setBlueDuty(this.range+1);
        setGreenDuty(this.range+1);
    }

    public void setGreenColor(){
        setRedDuty(this.range+1);
        setBlueDuty(this.range+1);
        setGreenDuty(0);
    }

    public void setBlueColor(){
        setRedDuty(this.range+1);
        setBlueDuty(0);
        setGreenDuty(this.range+1);
    }

    public void setWhiteColor(){
        setRedDuty(0);
        setBlueDuty(0);
        setGreenDuty(0);
    }

    public void setYellowColor(){
        setRedDuty(0);
        setBlueDuty(this.range+1);
        setGreenDuty(0);
    }

    public void setVioletColor(){
        setRedDuty(0);
        setBlueDuty(0);
        setGreenDuty(this.range+1);
    }

    public void setLightBlueColor(){
        setRedDuty(this.range+1);
        setBlueDuty(0);
        setGreenDuty(0);
    }

    public void setRedDuty(int duty){
        redLed.setPwm(duty);
    }

    public void setBlueDuty(int duty){
        blueLed.setPwm(duty);
    }

    public void setGreenDuty(int duty){
        greenLed.setPwm(duty);
    }

    public void setRandomDuty(){
        Random r = new Random();
        setRedDuty(r.nextInt(this.range+1));
        setBlueDuty(r.nextInt(this.range+1));
        setGreenDuty(r.nextInt(this.range+1));
    }

    public void shutdown(){
        redLed.setPwm(0);
        redLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        blueLed.setPwm(0);
        blueLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        greenLed.setPwm(0);
        greenLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }

}
