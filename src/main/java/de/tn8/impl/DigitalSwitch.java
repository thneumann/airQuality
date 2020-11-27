package de.tn8.impl;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * LED Light with 5V and 220 Ohm resistor combined with a
 * switch on 3.3V and GPIO Port, each with 10 kOhm resistor
 */
public class DigitalSwitch {

    private int switchCount = 0;
    private final GpioController gpio;
    private final GpioPinDigitalInput mySwitch;

    public DigitalSwitch(Pin gpioPinSwitch, GpioController gpio) {
        this.gpio = gpio;
        mySwitch = gpio.provisionDigitalInputPin(gpioPinSwitch, "My Switch", PinPullResistance.PULL_DOWN);
        listenSwitchChange();
    }

    public DigitalSwitch(GpioController gpio, GpioPinDigitalInput mySwitch) {
        this.gpio = gpio;
        this.mySwitch = mySwitch;
        listenSwitchChange();
    }

    private void listenSwitchChange(){
        GpioPinListenerDigital listener = new GpioPinListenerDigital(){

            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //only toggle if signal is high such that the light stays turned on
                if(mySwitch.isHigh()){
                    switchCount++;
                }
            }
        };
        mySwitch.addListener(listener);
    }

    public void addListener(GpioPinListenerDigital listener){
        mySwitch.addListener(listener);
    }

    public void runForTenSwitches(){
        for(switchCount = 0;switchCount < 10;){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        mySwitch.removeAllListeners();
        mySwitch.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }

    public GpioPinDigitalInput getMySwitch() {
        return mySwitch;
    }
}
