package de.tn8.main;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.wiringpi.Gpio;
import de.tn8.impl.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseCases {

    public static void doMHZ19BUseCase(){


        try {
            MHZ19B mhz19B = new MHZ19B(new SerialConfig());

            while(true){
                mhz19B.getData();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void doLCDUseCase(){


        final GpioController gpio = GpioFactory.getInstance();

        char degree = 0xDF;

        try {
            LCD1602 lcd = new LCD1602(I2CBus.BUS_1);
            Thread.sleep(5000);
            lcd.init4bitMode();
            Thread.sleep(5000);
            lcd.sendStringTwoLineMode("Hello World!", 0, 1);
            lcd.sendStringTwoLineMode(degree+"C YEEES!", 4, 2);
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void doTemperatureHumidityUseCase() {

        final GpioController gpio = GpioFactory.getInstance();

        DHT11 dht11 = new DHT11(RaspiPin.GPIO_00, gpio, RaspiPin.GPIO_02);
        try {
            //let DHT start up
            Thread.sleep(2000);
            for (int i = 0; i < 10; i++) {

                Thread t = new Thread(dht11);
                t.start();
                Thread.sleep(1000);

                while(dht11.isThreadActive()){
                    Thread.sleep(3000);
                }
                System.out.println("Temperature: " + dht11.getTemperature() + "°C");
                System.out.println("Humidity: " + dht11.getHumidity() + "%");
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void doADCPotiRGBLightUseCase() {

        final GpioController gpio = GpioFactory.getInstance();

        int range = 100;

        RGBLight rgbLight = new RGBLight(RaspiPin.GPIO_03, RaspiPin.GPIO_00, RaspiPin.GPIO_02, gpio);
        rgbLight.setMode(Gpio.PWM_MODE_BAL);
        rgbLight.setRange(range);
        rgbLight.setClock(500);

        try {
            ADCADS7830 adcads7830 = new ADCADS7830(I2CBus.BUS_1);

            int channelRed = 0;
            int channelGreen = 1;
            int channelBlue = 2;

            rgbLight.setBlueColor();
            Thread.sleep(3000);

            for (int i = 0; i < 10000; i++) {
                int dutyRed = adcads7830.readChannel(channelRed) * 100 / 255;
                int dutyGreen = adcads7830.readChannel(channelGreen) * 100 / 255;
                int dutyBlue = adcads7830.readChannel(channelBlue) * 100 / 255;
                //System.out.println("Duty: " + duty);
                rgbLight.setRedDuty(dutyRed);
                rgbLight.setGreenDuty(dutyGreen);
                rgbLight.setBlueDuty(dutyBlue);
                if (i % 50 == 0) {
                    System.out.println("Current value of channel red " + channelRed + ": " + adcads7830.readChannel(channelRed));
                    System.out.println("Current value of channel green " + channelRed + ": " + adcads7830.readChannel(channelGreen));
                    System.out.println("Current value of channel blue " + channelRed + ": " + adcads7830.readChannel(channelBlue));
                }
                Thread.sleep(30);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        }

        rgbLight.shutdown();

    }


    public static void doADCPotiLightUseCase() {

        final GpioController gpio = GpioFactory.getInstance();

        DimmableLight dimmableLight = new DimmableLight(RaspiPin.GPIO_00, gpio);
        dimmableLight.setMode(Gpio.PWM_MODE_BAL);
        dimmableLight.setRange(10000);
        dimmableLight.setClock(500);

        int channel = 0;

        try {
            ADCADS7830 adcads7830 = new ADCADS7830(I2CBus.BUS_1);

            byte[] bytes = adcads7830.readFirstEightByte();
            Arrays.toString(bytes);
            for (int i = 0; i < bytes.length; i++) {
                System.out.println("Byte " + (i + 1) + ": " + bytes[i]);
            }

            for (int i = 0; i < 10000; i++) {
                int duty = adcads7830.readChannel(channel) * 100 / 255;
                //System.out.println("Duty: " + duty);
                dimmableLight.setDuty(duty);
                if (i % 50 == 0) {
                    System.out.println("Current value of channel " + channel + ": " + adcads7830.readChannel(channel));
                    System.out.println("Current Voltage is: " + adcads7830.calculateVoltage(3.3));
                }
                Thread.sleep(30);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        }

    }


    public static void doADCPotiUseCase() {
        // find available busses

        try {
            ADCADS7830 adcads7830 = new ADCADS7830(I2CBus.BUS_1);
            System.out.println("Current value 0x80: " + adcads7830.readValue(0x80));
            System.out.println("Current value: 0x00: " + adcads7830.readValue(0x00));

            byte[] bytes = adcads7830.readFirstEightByte();
            Arrays.toString(bytes);
            for (int i = 0; i < bytes.length; i++) {
                System.out.println("Byte " + (i + 1) + ": " + bytes[i]);
            }

            for (int i = 0; i < 100; i++) {
                System.out.println("Current Value is: " + adcads7830.readValue(0x00));
                System.out.println("Current value 0x80: " + adcads7830.readValue(0x80));
                System.out.println("Current Voltage is: " + adcads7830.calculateVoltage(3.3));
                Thread.sleep(1000);
            }

        } catch (IOException exception) {
            System.out.println("I/O error on I2C bus occurred");
        } catch (I2CFactory.UnsupportedBusNumberException exception) {
            System.out.println("Unsupported I2C bus required");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void doSwitchPassiveBuzzerUseCase() {

        final GpioController gpio = GpioFactory.getInstance();

        final DigitalSwitch digitalSwitch = new DigitalSwitch(RaspiPin.GPIO_01, gpio);
        final PassiveBuzzer passiveBuzzer = new PassiveBuzzer(RaspiPin.GPIO_00, gpio);

        GpioPinListenerDigital listener = new GpioPinListenerDigital() {

            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //only toggle if signal is high such that the light stays turned on
                if (digitalSwitch.getMySwitch().isLow()) {
                    passiveBuzzer.makeNoise();
                }
                if (digitalSwitch.getMySwitch().isHigh()) {
                    passiveBuzzer.stopNoise();
                }
            }
        };

        digitalSwitch.addListener(listener);

        //passiveBuzzer.permanentNoise();

        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        passiveBuzzer.shutdown();
        digitalSwitch.shutdown();

    }

    public static void doSwitchActiveBuzzerUseCase() {

        final GpioController gpio = GpioFactory.getInstance();

        final DigitalSwitch digitalSwitch = new DigitalSwitch(RaspiPin.GPIO_01, gpio);
        final ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_00, gpio);

        GpioPinListenerDigital listener = new GpioPinListenerDigital() {

            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //only toggle if signal is high such that the light stays turned on
                if (digitalSwitch.getMySwitch().isLow()) {
                    activeBuzzer.turnOn();
                }
                if (digitalSwitch.getMySwitch().isHigh()) {
                    activeBuzzer.turnOff();
                }
            }
        };

        digitalSwitch.addListener(listener);


        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        activeBuzzer.shutdown();
        digitalSwitch.shutdown();

    }

    public static void doRGBLightUseCase() {
        final GpioController gpio = GpioFactory.getInstance();
        int range = 1000;

        RGBLight rgbLight = new RGBLight(RaspiPin.GPIO_00, RaspiPin.GPIO_02, RaspiPin.GPIO_01, gpio);
        rgbLight.setMode(Gpio.PWM_MODE_BAL);
        rgbLight.setRange(range);
        rgbLight.setClock(500);

        //check red,blue,green,yellow,violet,light blue and white color

        try {

            rgbLight.setRedColor();

            Thread.sleep(2000);

            rgbLight.setBlueColor();
            Thread.sleep(2000);

            rgbLight.setGreenColor();
            Thread.sleep(2000);

            rgbLight.setYellowColor();
            Thread.sleep(2000);

            rgbLight.setVioletColor();
            Thread.sleep(2000);

            rgbLight.setLightBlueColor();
            Thread.sleep(2000);

            rgbLight.setWhiteColor();
            Thread.sleep(2000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //make some random colors
        for (int i = 0; i < 10 + 1; i++) {
            try {

                rgbLight.setRandomDuty();

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        rgbLight.shutdown();

    }

    public static void doPWMLightUseCase() {

        final GpioController gpio = GpioFactory.getInstance();
        int range = 1000;

        DimmableLight dimmableLight = new DimmableLight(RaspiPin.GPIO_01, gpio);
        dimmableLight.setMode(Gpio.PWM_MODE_MS);
        dimmableLight.setRange(range);
        dimmableLight.setClock(500000);

        try {

            dimmableLight.setDuty(500);

            Thread.sleep(2000);

            dimmableLight.setDuty(950);

            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < range + 1; i++) {
            try {

                dimmableLight.setDuty(i);

                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = range; i >= 0; i--) {
            try {

                dimmableLight.setDuty(i);

                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        dimmableLight.shutdown();

    }


    public static void doBarGraphWaterDropUseCase() {


        final GpioController gpio = GpioFactory.getInstance();

        List<Pin> pins = new ArrayList<Pin>();
        pins.add(RaspiPin.GPIO_00);
        pins.add(RaspiPin.GPIO_01);
        pins.add(RaspiPin.GPIO_02);
        pins.add(RaspiPin.GPIO_03);
        pins.add(RaspiPin.GPIO_04);
        pins.add(RaspiPin.GPIO_05);
        pins.add(RaspiPin.GPIO_06);
        pins.add(RaspiPin.GPIO_08);
        pins.add(RaspiPin.GPIO_09);
        pins.add(RaspiPin.GPIO_10);

        BarGraph barGraph = new BarGraph(pins, gpio);

        for (int i = 0; i < 20; i++) {
            try {
                barGraph.waterdrop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        barGraph.shutdown();

    }

    public static void doLightSwitchUseCase() {

        final GpioController gpio = GpioFactory.getInstance();

        final DigitalSwitch digitalSwitch = new DigitalSwitch(RaspiPin.GPIO_01, gpio);

        final DigitalLight digitalLight = new DigitalLight(RaspiPin.GPIO_00, gpio);

        GpioPinListenerDigital listener = new GpioPinListenerDigital() {

            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //only toggle if signal is high such that the light stays turned on
                if (digitalSwitch.getMySwitch().isHigh()) {
                    digitalLight.getMyLed().toggle();
                }
            }
        };

        digitalSwitch.addListener(listener);

        digitalSwitch.runForTenSwitches();

        digitalLight.shutdown();
        digitalSwitch.shutdown();

    }

    public static void doLightUseCase() throws InterruptedException {

        final GpioController gpio = GpioFactory.getInstance();

        DigitalLight digitalLight = new DigitalLight(RaspiPin.GPIO_00, gpio);
        System.out.println("Turn the light on!");
        digitalLight.turnOn();

        Thread.sleep(3000);
        System.out.println("Turn the light off!");
        digitalLight.turnOff();

        digitalLight.shutdown();

    }


}
