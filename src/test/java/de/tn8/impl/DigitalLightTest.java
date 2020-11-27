package de.tn8.impl;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import junit.framework.TestCase;
import org.junit.BeforeClass;

public class DigitalLightTest extends TestCase {

    static DigitalLight digitalLight;
    static GpioController gpio;

    @BeforeClass
    public static void setUpTestSuite() {

    }

    public void setUp() throws Exception {
        if(gpio == null){
        gpio = GpioFactory.getInstance();
        digitalLight = new DigitalLight(RaspiPin.GPIO_00,gpio);
        }
        super.setUp();
    }

    public void tearDown() throws Exception {
        digitalLight.shutdown();
    }

    public void testGetGpio() {
        assertEquals(gpio, digitalLight.getGpio());
    }

    public void testGetMyLed() {
        assertTrue(digitalLight.getMyLed().isLow());
    }

    public void testGetGpioPin() {
        assertEquals(RaspiPin.GPIO_00, digitalLight.getGpioPin());
    }

    public void testSetGpioPin() {
        digitalLight.setGpioPin(RaspiPin.GPIO_01);
        assertEquals(RaspiPin.GPIO_01, digitalLight.getGpioPin());
        digitalLight.setGpioPin(RaspiPin.GPIO_00);
        assertEquals(RaspiPin.GPIO_00, digitalLight.getGpioPin());
    }

    public void testTurnOn() {
        digitalLight.turnOn();
        assertTrue(digitalLight.getMyLed().isHigh());
    }

    public void testTurnOff(){
        digitalLight.turnOff();
        assertTrue(digitalLight.getMyLed().isLow());
    }

    public void testShutdown() {
        digitalLight.shutdown();
        assertTrue(digitalLight.getMyLed().isLow());
    }
}