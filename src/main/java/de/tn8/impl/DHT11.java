package de.tn8.impl;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import de.tn8.listener.LCDListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DHT11 implements Runnable{


    private boolean threadActive=false;
    private Date lastDataUpdate = new Date();

    private List<LCDListener> lcdListenerList = new ArrayList<LCDListener>();

    private Pin dht11GpioPin;
    private  int[] data   = new int[5];
    private final GpioPinDigitalOutput powerOutput;

    //100 micro seconds as timeout
    private final static long TIMEOUT_NANO_SECONDS = 100000;
    private final static long TIMEOUT_MICRO_SECONDS = 100;

    public DHT11(Pin gpioPin, GpioController gpio, Pin gpioPowerPin){

        powerOutput = gpio.provisionDigitalOutputPin(gpioPowerPin);
        powerOutput.high();

        //no data available yet
        lastDataUpdate.setTime(0);

        this.dht11GpioPin = gpioPin;
        System.out.println("Use Data Pin: " + dht11GpioPin.getAddress());
        System.out.println("Use Power Pin: " + gpioPowerPin.getAddress());
    }

    /**
     * restart DHT11. Turn power off for 15 seconds then turn it on again and wait for a few seconds
     */
    private void restart() throws InterruptedException {
        powerOutput.low();
        Thread.sleep(1000*15);
        powerOutput.high();
        Thread.sleep(1000*3);
    }

    public void addLCDListener(LCDListener lcdListener){
        lcdListenerList.add(lcdListener);
    }

    public boolean readData() throws InterruptedException {


        int [] readData = new int[5];
        boolean [] dataBuffer = new boolean[40];

        Gpio.pinMode(dht11GpioPin.getAddress(), Gpio.OUTPUT);
        Gpio.digitalWrite(dht11GpioPin.getAddress(), Gpio.HIGH);
        Gpio.delay(10);



        //send command
        Gpio.digitalWrite(dht11GpioPin.getAddress(), Gpio.LOW);

        //delay for wakeup
        Gpio.delay(18);
        //Thread.sleep(18);
        Gpio.digitalWrite(dht11GpioPin.getAddress(), Gpio.HIGH);
        Gpio.delayMicroseconds(40);


        Gpio.pinMode(dht11GpioPin.getAddress(), Gpio.INPUT);

        //check whether DHT11 has mentioned our command
        long t = Gpio.micros();
        while(Gpio.digitalRead(dht11GpioPin.getAddress()) == 0){
            if(Gpio.micros() - t  > TIMEOUT_MICRO_SECONDS){
                //TODO throw Timeout exception;
                System.out.println("Timeout first low");
                return false;
            }
        }

        t = Gpio.micros();
        while(Gpio.digitalRead(dht11GpioPin.getAddress()) == 1){
            if(Gpio.micros() - t  > TIMEOUT_MICRO_SECONDS){
                //TODO throw Timeout exception;

                System.out.println("Timeout first high");
                return false;
            }
        }

        //alright we made it, now we can read our 40 bits
        //each bit transmission starts with a 50 micro seconds low signal
        // followed by a high signal for 26-28 microseconds for a 0 bit or 70 microseconds for a 1

        long [] timeTaken = new long[40];
        //read out 40 states
        t = Gpio.micros();
        long time = 0;
        for(int i = 0; i < 40; i++){
            while(Gpio.digitalRead(dht11GpioPin.getAddress()) == 0 && time < TIMEOUT_MICRO_SECONDS){
                time = Gpio.micros() - t;
            }

            t = Gpio.micros();
            while(Gpio.digitalRead(dht11GpioPin.getAddress()) == 1 && time  < TIMEOUT_MICRO_SECONDS){
                time = Gpio.micros() - t;
            }
            t = Gpio.micros();
            if(time  < TIMEOUT_MICRO_SECONDS){
                timeTaken[i] = time;
            }else{
                System.out.println("Timeout in iteration " + i);
                System.out.println(Arrays.toString(timeTaken));
                return false;
            }

        }

        System.out.println("Success");

        System.out.println(Arrays.toString(timeTaken));

        for(int i = 0; i < 40; i++) {

            if(timeTaken[i] > 48){
                dataBuffer[i] = true;
            }else{
                dataBuffer[i] = false;
            }

            if(dataBuffer[i]) {
                readData[i / 8] += 1;
            }
            // if there are more bits comming for this byte, shift the bits to the left.
            if (i % 8 != 7) {
                readData[i / 8] <<= 1;
            }
        }

        if(isCheckSumCorrect(readData)){
            data = readData;
            return true;
        }
        System.out.println("Checksum incorrect");
        return false;

    }

    public double getTemperature(){
        double temperature = (data[2] & 0x7F);
        if((data[2] & 0x80) != 0){
            //signed bit here means temperature is negative
            temperature *= -1;
            if((data[3] & 0x7F) != 0){
                //if there is any bit set in decimal byte reduce temperature by decimal number
                temperature = temperature - 1 + (data[3]&0x0F) * 0.1;
            }
        }else{
            // no signed bit set means just adding the decimal number
            temperature += (data[3]&0x0F) * 0.1;
        }
        return temperature;
    }

    public double getHumidity(){
        double humidity = data[0] + data[1]*0.1;
        return humidity;
    }

    private boolean isCheckSumCorrect(int[] data){
        System.out.println("Checksum calculation:");
        System.out.println(Arrays.toString(data));
        return (data[0] + data[1] + data[2] + data[3]) % 256 == data[4];
    }

    private void shutdown(){
        //dht11Pin.setShutdownOptions(true,PinState.LOW, PinPullResistance.OFF);
    }

    public boolean isThreadActive() {
        return threadActive;
    }

    public void stopThread() {
        threadActive=false;
    }

    public Date getLastDataUpdate() {
        return lastDataUpdate;
    }

    public String toString(){
        return Arrays.toString(data);
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        threadActive = true;
        try {
            while(threadActive) {
                while (!readData()) {

                    //if there was no result for 5 Minutes, restart DHT 11
                    if (System.currentTimeMillis() - startTime > 1000 * 60 * 5) {
                        restart();
                        startTime = System.currentTimeMillis();
                    }

                    //wait for 5 seconds before try again:
                    Thread.sleep(5000);
                }
                lastDataUpdate = new Date();
                for (int i = 0; i < lcdListenerList.size(); i++){
                    lcdListenerList.get(i).sendTemperatureAndHumidity(getTemperature(), getHumidity());
                }

                //after successful read wait for 30 seconds
                Thread.sleep(30000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastDataUpdate = new Date();
        threadActive = false;
    }


}
