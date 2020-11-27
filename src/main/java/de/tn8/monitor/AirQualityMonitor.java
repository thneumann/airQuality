package de.tn8.monitor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.serial.SerialConfig;
import de.tn8.impl.*;
import de.tn8.listener.LCDListener;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AirQualityMonitor implements Runnable{


    private MHZ19B mhz19B;
    private LCD1602 lcd;
    private DHT11 dht11;

    private DigitalLight redLight;
    private DigitalLight blueLight;
    private DigitalLight greenLight;

    private ActiveBuzzer activeBuzzer;
    private DigitalSwitch digitalSwitch;
    private DigitalSwitch slideSwitch;

    LocalDateTime lastAlarm = LocalDateTime.now().minusMinutes(500);
    LocalDateTime buttonSwitchHoldDown = LocalDateTime.now();

    private boolean stop = false;
    private boolean mute = false;

    public AirQualityMonitor(SerialConfig serialConfig, int i2cbus, GpioController gpio, Pin dht11GpioPin, Pin dht11GpioPowerPin, Pin redLED, Pin greenLED, final Pin blueLED, Pin activeBuzzerPin, Pin digitalSwitchPin, Pin slideSwitchPin) throws IOException, InterruptedException, I2CFactory.UnsupportedBusNumberException {

        System.out.println("init LEDs");
        redLight = new DigitalLight(redLED, gpio);
        blueLight = new DigitalLight(blueLED, gpio);
        greenLight = new DigitalLight(greenLED, gpio);

        greenLight.turnOn();

        activeBuzzer = new ActiveBuzzer(activeBuzzerPin, gpio);
        digitalSwitch = new DigitalSwitch(digitalSwitchPin, gpio);

        digitalSwitch.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                activeBuzzer.turnOff();
                redLight.turnOff();
                if(mute){
                    blueLight.turnOn();
                }else{
                    greenLight.turnOn();
                }
            }
        });

        digitalSwitch.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                if(gpioPinDigitalStateChangeEvent.getState().isLow()){
                    buttonSwitchHoldDown = LocalDateTime.now();
                }else{
                    if(ChronoUnit.SECONDS.between(buttonSwitchHoldDown, LocalDateTime.now()) > 20){
                        if(!activeBuzzer.isActive()){
                            activeBuzzer.turnOn();
                            System.out.println("Send zero Point Calibration");
                            try {
                                mhz19B.sendZeroPointCalibration();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            activeBuzzer.turnOff();
                        }
                    }
                }
            }
        });

        slideSwitch = new DigitalSwitch(slideSwitchPin, gpio);
        slideSwitch.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                if(gpioPinDigitalStateChangeEvent.getState().isHigh()){
                    greenLight.turnOn();
                    blueLight.turnOff();
                    mute=false;
                }if(gpioPinDigitalStateChangeEvent.getState().isLow()){
                    greenLight.turnOff();
                    blueLight.turnOn();
                    mute=true;
                }
            }
        });

        if(slideSwitch.getMySwitch().isLow()){
            greenLight.turnOff();
            blueLight.turnOn();
            mute=true;
        }


        System.out.println("init MHZ19B");
        mhz19B = new MHZ19B(serialConfig);
        System.out.println("init LCD");
        lcd = new LCD1602(i2cbus);
        Thread.sleep(3000);
        lcd.init4bitMode();
        Thread.sleep(3000);
        System.out.println("init DHT11");
        dht11 = new DHT11(dht11GpioPin, gpio, dht11GpioPowerPin);
        Thread.sleep(3000);

        LCDListener lcdListener = new LCDListener() {
            @Override
            public void sendTemperatureAndHumidity(double tempperature, double humidity) {
                try {
                    lcd.sendStringTwoLineMode(generateTemperatureHumidityString(),0,1);
                    if(dht11.getHumidity() > 72.0){
                        startAlarm();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void sendCo2Ppm(int ppm) {
                try {
                    lcd.sendStringTwoLineMode(generatePPMString(), 0, 2);
                    if(ppm > 1000){
                        startAlarm();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        mhz19B.addLCDListener(lcdListener);
        dht11.addLCDListener(lcdListener);

    }

    public void stopThread(){
        stop = true;
    }

    private String generatePPMString(){
        return "CO2: " + mhz19B.getPpm() + " PPM";
    }

    private String generateTemperatureHumidityString(){
        return dht11.getTemperature() + ((char)0xDF + "C, ") + dht11.getHumidity() + "%RH";
    }

    private void startAlarm(){
        if(ChronoUnit.MINUTES.between(lastAlarm, LocalDateTime.now()) > 5) {
            greenLight.turnOff();
            blueLight.turnOff();
            redLight.turnOn();
            if(!mute){
                activeBuzzer.turnOn();
            }
            lastAlarm = LocalDateTime.now();
        }
    }

    @Override
    public void run() {
        stop = false;
        Thread dht11Thread = new Thread(dht11);
        dht11Thread.start();

        Thread MHZ19BThread = new Thread(mhz19B);
        MHZ19BThread.start();
        int counter = 0;
        while(!stop) {
            try {
                Thread.sleep(10000);
                //after 3 minutes refresh the screen in case of errors in the transmission
                if(++counter >= 18){
                    counter = 0;
                    lcd.sendStringTwoLineMode(generatePPMString(), 0, 2);
                    lcd.sendStringTwoLineMode(generateTemperatureHumidityString(),0,1);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        mhz19B.stopThread();
        dht11.stopThread();

    }


}
