package de.tn8.main;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.I2C;
import de.tn8.impl.*;
import de.tn8.monitor.AirQualityMonitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        final GpioController gpio = GpioFactory.getInstance();

        try {
            AirQualityMonitor aqm = new AirQualityMonitor(new SerialConfig(),I2CBus.BUS_1, gpio,RaspiPin.GPIO_00, RaspiPin.GPIO_02, RaspiPin.GPIO_23, RaspiPin.GPIO_24, RaspiPin.GPIO_25, RaspiPin.GPIO_22, RaspiPin.GPIO_29, RaspiPin.GPIO_28);

            Thread airQualityThread = new Thread(aqm);
            airQualityThread.start();

            while(airQualityThread.isAlive()){
                //Here you can start another implementation while airquality is measured. E.g. you could send the data to some online endpoint
                //However, for now just sleep
                Thread.sleep(3000);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        }



    }


}
