package de.tn8.impl;

import com.pi4j.io.gpio.*;

public class PassiveBuzzer {

    private final GpioController gpio;
    private final GpioPinPwmOutput myBuzzer;
    private boolean stop = false;

    public PassiveBuzzer(Pin gpioPinSwitch, GpioController gpio) {
        this.gpio = gpio;
        myBuzzer = gpio.provisionSoftPwmOutputPin(gpioPinSwitch, "My Buzzer");
    }

    public void permanentNoise(){
        myBuzzer.setPwm(50);
    }

    public void makeNoise() {
        stop = false;
        myBuzzer.setPwm(50);
        while (true) {
            for (int i = 0; i < 360; i++) {
                double sin = Math.sin(i * Math.PI / 180.);
                double tone = 50 + sin * 49;
                if (stop) {
                    return;
                }
                myBuzzer.setPwm((int) tone);
                System.out.println("Tone value: " + tone);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopNoise() {
        stop = true;
        myBuzzer.setPwm(0);
    }

    public void shutdown() {
        myBuzzer.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }

}
