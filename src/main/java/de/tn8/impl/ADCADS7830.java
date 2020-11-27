package de.tn8.impl;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;

/**
 *
 */
public class ADCADS7830 {

    private static final int ADS7830_ADDR = 0x4b;

    private I2CDevice device;

    public ADCADS7830(int i2cBus) throws IOException, I2CFactory.UnsupportedBusNumberException {

        I2CBus i2c = I2CFactory.getInstance(i2cBus);
        device = i2c.getDevice(ADS7830_ADDR);

    }

    public int readValue(int value) throws IOException {
        return device.read(value);
    }


    /**
     * @param channel
     *         channel is used to write the correct command to the ADC
     *         we have channels 0, 1, 2, 3, 4, 5, 6, 7
     *         0x84|(((channel<<2 | channel>>1)&0x07)<<4) means the following for channel 1 (0000 0001)
     *         --> 1000 0100 | (((0000 0100 | 0000 0000) & 0000 0111) << 4))
     *         --> 1000 0100 | ((0000 0100 & 0000 0111) << 4))
     *         --> 1000 0100 | (0000 0100 << 4)
     *         --> 1000 0100 | 0100 0000
     *         --> 1100 0100
     *         --> 0xC4
     *         --> 128+64+4=196
     *         Write value for channel 0: 132 1000 0100
     *         Write value for channel 1: 196 1100 0100
     *         Write value for channel 2: 148 1001 0100
     *         Write value for channel 3: 212 1101 0100
     *         Write value for channel 4: 164 1010 0100
     *         Write value for channel 5: 228 1110 0100
     *         Write value for channel 6: 180 1011 0100
     *         Write value for channel 7: 244 1111 0100
     * @return
     */
    public int readChannel(int channel) throws IOException {
        int byteValue = 0x84 | (((channel<<2 | channel>>1) & 0x07)<<4);
        return device.read(byteValue);
    }

    public byte[] readFirstEightByte() throws IOException {
        byte[] buffer = new byte[8];
        device.read(buffer, 0, 8);
        return buffer;
    }

    public double calculateVoltage(double inputVoltage) throws IOException {
        return (device.read(0x80)/255.)*inputVoltage;
    }




}
