package de.tn8.impl;

import com.pi4j.gpio.extension.pcf.PCF8574GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.gpio.extension.pcf.PCF8574Pin;
import com.pi4j.wiringpi.Gpio;
import de.tn8.monitor.AirQualityMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LCD1602 {

    private static final Logger logger = LogManager.getLogger(LCD1602.class);


    //see datasheets for commands DB7 = MSB -> DB0 LSB
    private final static byte CLEARDISPLAY = 0x01;
    private final static byte RETURNHOME = 0x02;
    //means shifting the display cursor after reading/writing data
    private final static byte ENTRYMODESHIFTRIGHT = 0x04;
    private final static byte ENTRYMODESHIFTLEFT = 0x06;
    private final static byte ENTRYMODENOSHIFT = 0x05;
    //Display ON/OFF Control
    private final static byte DISPLAYOFF = 0x08;
    private final static byte DISPLAYONNOCURSORNOBLINKS = 0x0C;
    private final static byte DISPLAYONNOCURSORBLINKS = 0x0D;
    private final static byte DISPLAYONCURSORNOBLINKS = 0x0E;
    private final static byte DISPLAYONCURSORBLINKS = 0x0F;
    //Shift Cursor/display
    private final static byte SHIFTCURSORLEFT = 0x10;
    private final static byte SHIFTCURSORRIGHT = 0x14;
    private final static byte SHIFTDISPLAYANDCURSORLEFT = 0x18;
    private final static byte SHIFTDISPLAYANDCURSORRIGHT = 0x1C;
    //Function set
    private final static byte ONELINEDISPLAYBOLDLETTERS8BIT = 0x34;
    private final static byte ONELINEDISPLAYSMALLLETTERS8BIT = 0x30;
    private final static byte TWOLINEDISPLAY8BIT = 0x38;
    private final static byte ONELINEDISPLAYBOLDLETTERS4BIT = 0x24;
    private final static byte ONELINEDISPLAYSMALLLETTERS4BIT = 0x20;
    private final static byte TWOLINEDISPLAY4BIT = 0x28;
    //set line
    private final static byte LINE1 = (byte) 0x80;
    private final static byte LINE2 = (byte)  0xC0;




    // PCF8574 pins
    private final static byte RSPIN_ADDR = 0x01;
    private final static byte RWPIN_ADDR = 0x02;
    private final static byte EPIN_ADDR = 0x04;
    private final static byte LEDPIN_ADDR = 0x08;

    private byte rsPin = 0;
    private byte rwPin = 0;
    private byte ePin = 0;
    private byte LEDPin = 1;

    private final static byte PCF8574_ADDR = 0x27;

    private I2CBus i2c;
    private I2CDevice device;

    public LCD1602(int i2cBus) throws IOException, I2CFactory.UnsupportedBusNumberException {

        i2c = I2CFactory.getInstance(i2cBus);
        device = i2c.getDevice(PCF8574_ADDR);
    }

    public void init4bitMode() throws IOException, InterruptedException {
        byte rsPin = 0;
        byte rwPin = 0;
        byte ePin = 0;
        byte LEDPin = 1;

        //turn on power pin
        sendCommand4BitMode((byte) 0x03);
        Thread.sleep(1);
        sendCommand4BitMode((byte) 0x03);
        Thread.sleep(1);
        sendCommand4BitMode((byte) 0x03);
        Thread.sleep(1);
        sendCommand4BitMode((byte) 0x02);
        Thread.sleep(1);

        sendCommand4BitMode(TWOLINEDISPLAY4BIT);
        Thread.sleep(1);
        sendCommand4BitMode(CLEARDISPLAY);
        Thread.sleep(1);
        sendCommand4BitMode(RETURNHOME);
        Thread.sleep(1);
        sendCommand4BitMode(ENTRYMODESHIFTLEFT);
        Thread.sleep(1);
        sendCommand4BitMode(DISPLAYONNOCURSORNOBLINKS);
        Thread.sleep(1);
        setWriteMode();
        sendStringTwoLineMode("Loading ...", 0, 1);
        sendStringTwoLineMode("Loading ...", 0, 2);
        Thread.sleep(1);

    }

    private byte getCommandLCD1602CommandByte(){
        return (byte) (RSPIN_ADDR*rsPin + RWPIN_ADDR*rwPin + LEDPIN_ADDR*LEDPin);
    }

    private void sendCommand4BitMode(byte command) throws IOException, InterruptedException {
        logger.info("send command: " + Integer.toString(command, 2) + " = " + command);
        byte sendCommands [] = new byte[2];
        //send first 4 bits
        logger.info("Screen command: " + Integer.toString(0xf0 & command, 2));
        logger.info("Screen command with LCD Mode bits: " + Integer.toString(0xf0 & command | getCommandLCD1602CommandByte(), 2));
        sendCommands[0] = (byte) (0xf0 & command | getCommandLCD1602CommandByte());
        //send last 4 bits
        logger.info("Screen command: " + Integer.toString(((0x0f & command) << 4), 2));
        logger.info("Screen command with LCD Mode bits: " + Integer.toString(((0x0f & command) << 4) | getCommandLCD1602CommandByte(), 2));
        sendCommands[1] = (byte) (((0x0f & command) << 4) | getCommandLCD1602CommandByte());
        sendDataToPCF8574(sendCommands);
    }

    private void sendDataToPCF8574(byte [] sendCommands) throws IOException, InterruptedException {
        for (int i = 0; i < sendCommands.length; i++){
            logger.info("sendDataToPCF8574: " + Integer.toString(sendCommands[i], 2) + " = " + sendCommands[i]);
            device.write(sendCommands[i]);
            device.write( (byte) (sendCommands[i] | EPIN_ADDR));
            Thread.sleep(0, 500000);
            device.write( (byte) (sendCommands[i] & ~EPIN_ADDR));
            Thread.sleep(0, 500000);
        }
    }

    private void sendCharacter (char c) throws IOException, InterruptedException {
        setWriteMode();

        logger.info("Write character: " + c);
        sendCommand4BitMode((byte) c);

    }

    private void setPosition(int line, int pos){

    }

    private void setWriteMode(){
        rsPin = 1;
        byte rwPin = 0;
        byte LEDPin = 1;
    }

    private void setCommandMode(){
        rsPin = 0;
        byte rwPin = 0;
        byte LEDPin = 1;
    }

    public void sendStringTwoLineMode(String s, int position, int line) throws IOException, InterruptedException {
        if(position > 0x27 || position < 0){
            throw new RuntimeException("Position is out of range");
        }
        byte newPos = 0;
        switch (line) {
            case 1:
                newPos = (byte) (LINE1 + position);
                break;
            case 2:
                newPos = (byte) (LINE2 + position);
                break;
            default:
                throw new RuntimeException("You can only choose between line 1 and 2");
        }

        setCommandMode();
        sendCommand4BitMode(newPos);


        for (int i = 0; i < s.length(); i++){
            sendCharacter(s.charAt(i));
        }
    }

    public void turnDisplayOff() throws IOException, InterruptedException {
        setCommandMode();

        sendCommand4BitMode(DISPLAYOFF);
    }

    public void clearDisplay() throws IOException, InterruptedException {
        setCommandMode();
        sendCommand4BitMode(CLEARDISPLAY);
    }

}
