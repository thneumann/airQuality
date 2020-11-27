package de.tn8.impl;

import com.pi4j.io.gpio.*;

import java.util.ArrayList;
import java.util.List;

public class BarGraph {

    List<GpioPinDigitalOutput> barGraphLeds = new ArrayList<GpioPinDigitalOutput>();
    private final GpioController gpio;

    public BarGraph(List<Pin> barGraphPins, GpioController gpio) {
        this.gpio = gpio;
        if(barGraphPins.size()!=10){
            throw new RuntimeException("A bar graph needs ten pins");
        }else {
            for (Pin pin : barGraphPins) {
                barGraphLeds.add(gpio.provisionDigitalOutputPin(pin, "LED " + pin.getAddress(), PinState.LOW));
            }
            //turn off all lights
            for (GpioPinDigitalOutput pinLed:barGraphLeds){
                pinLed.high();
            }
        }
    }

    public void waterdrop() throws InterruptedException {
        for (int i = 0; i < barGraphLeds.size(); i++){
            barGraphLeds.get(i).low();
            Thread.sleep(100);
            barGraphLeds.get(i).high();
        }
    }

    public void shutdown(){
        //turn off all lights
        for (GpioPinDigitalOutput pinLed:barGraphLeds){
            pinLed.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
        }
        gpio.shutdown();
    }

}
