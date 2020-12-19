package de.tn8.impl;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftTone;
import de.tn8.monitor.AirQualityMonitor;
import de.tn8.music.Songs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PassiveBuzzer implements Runnable{

    private static final Logger logger = LogManager.getLogger(PassiveBuzzer.class);


    private final GpioController gpio;
   // private final GpioPinPwmOutput myBuzzer;
    private boolean stop = true;
    private boolean stopThread = false;
    private int pinNumber;

    public PassiveBuzzer(Pin gpioPinSwitch, GpioController gpio) {
        this.gpio = gpio;
        pinNumber = gpioPinSwitch.getAddress();
        SoftTone.softToneCreate(gpioPinSwitch.getAddress());
//        if(gpioPinSwitch == RaspiPin.GPIO_01){
//            myBuzzer = gpio.provisionPwmOutputPin(gpioPinSwitch, "My Buzzer");
//
//        }else{
//            myBuzzer = gpio.provisionSoftPwmOutputPin(gpioPinSwitch, "My Buzzer");
//        }
    }

    public boolean isActive(){
        return stop;
    }

    public void turnOff() {
        stop = true;
        SoftTone.softToneWrite(pinNumber, 0);
    }

    public void turnOn() {
        makeNoise();
    }

    //public GpioPinPwmOutput getMyBuzzer(){
    //    return myBuzzer;
    //}

    public void makeNoise() {
        stop = false;
        SoftTone.softToneWrite(pinNumber, 4000);
    }

    public void stopNoise() {
        stop = true;
        SoftTone.softToneWrite(pinNumber, 0);
    }

    public void shutdown() {
        stopThread = true;
        //myBuzzer.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        gpio.shutdown();
    }
    public void playRandomSong(){
        stop = false;
        int randomNumber = new Random().nextInt(11);
        switch (randomNumber){
            case 0:
                playSong(Songs.MANADERNA_MELODY,Songs.MANADERNA_TEMPO,0.3,0.8);
                break;
            case 1:
                playSong(Songs.FINAL_COUNTDOWN_MELODY,Songs.FINAL_COUNTDOWN_TEMPO,0.3,1.2);
                break;
            case 2:
                playSong(Songs.BONNAGARD_MELODY,Songs.BONNAGARD_TEMPO,0.3,0.8);
                break;
            case 3:
                playSong(Songs.DECK_THE_HALLS_MELODY,Songs.DECK_THE_HALLS_TEMPO,0.3,0.8);
                break;
            case 4:
                playSong(Songs.CRAZY_FROG_MELODY,Songs.CRAZY_FROG_TEMPO,0.3,0.9);
                break;
            case 5:
                playSong(Songs.TWINKLE_TWINKLE_MELODY,Songs.TWINKLE_TWINKLE_TEMPO,0.5,1);
                break;
            case 6:
                playSong(Songs.POPCORN_MELODY,Songs.POPCORN_TEMPO,0.5,1);
                break;
            case 7:
                playSong(Songs.STAR_WARS_MELODY,Songs.STAR_WARS_TEMPO,0.5,1);
                break;
            case 8:
                playSong(Songs.MELODY,Songs.TEMPO,1.3,0.8);
                break;
            case 9:
                playSong(Songs.UNDERWORLD_MELODY,Songs.UNDERWORLD_TEMPO,1.3,0.8);
                break;
            case 10:
                playSong(Songs.ADVENTURE_TIME_MELODY,Songs.ADVENTURE_TIME_TEMPO,1.3,1.5);
                break;
        }

    }

    private void playNote(int note, double noteDuration){
        //note is the frequency;
        if(note == 0){
            //myBuzzer.setPwm(0);
            SoftTone.softToneWrite(pinNumber, 0);
            logger.info("Note is 0");
            try {
                Thread.sleep((long)(1000*noteDuration));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        double period = 1.0 / note;	 //in physics, the period (sec/cyc) is the inverse of the frequency (cyc/sec)
        double delayValue = period / 2;	 //calculate the time for half of the wave
        int numCycles = (int)(noteDuration * note);	 //the number of waves to produce is the duration times the frequency

        logger.info("Go for " + note + " for " +  noteDuration + "us");
//        Gpio.pwmSetClock((int) (2000));
//        Gpio.pwmSetRange((int)(9600/note));
//        myBuzzer.setPwmRange((int)(9600/note));
//        myBuzzer.setPwm((int)(9600/(2*note)));
        SoftTone.softToneWrite(pinNumber, note);
            try {
                TimeUnit.MICROSECONDS.sleep((long)(noteDuration*1000*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void playSong(int [] melody, int [] tempo, double pause, double pace){
        if(melody.length == tempo.length){
            for (int i = 0; i < melody.length; i++){
                if(stop){
                    return;
                }
                double noteDuration = pace/tempo[i];
                logger.info("play note " + melody[i] + " for " + noteDuration);
                playNote(melody[i], noteDuration);
                long pauseBetweenNotes = (long)(noteDuration*pause*1000);
                try {
                    Thread.sleep(pauseBetweenNotes);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            logger.info("ERROR: MELODY AND TEMPO NOT EQUALS");
        }
    }

    @Override
    public void run() {
        stop=false;
        stopThread=false;
        while(!stopThread) {
                while (!stop) {
                    logger.info("Play random Song");
                    playRandomSong();
//                    for (int i = 0; i < 360; i++) {
//                        double sin = Math.sin(i * Math.PI / 180.);
//                        double tone = 50 + sin * 49;
//                        myBuzzer.setPwm((int) tone);
//                        logger.info("Tone value: " + tone);
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            stopNoise();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
