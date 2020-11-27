package de.tn8.abstr;

import com.pi4j.wiringpi.Gpio;

public abstract class PWM {

    protected int range = 1024;

    /**
     * @param range sets the resolution, which is the number of steps between 0% and 100% duty cycle.
     *              Duty Cycle is the pulse width divided by the period (D=PW/T). A duty cycle of 80% means
     *              the signal is 80% of the time on and 20% of the time off.
     *              The clock that is supplied to the PWM hardware doesn't set the repeat rate but the smallest unit of change on the PWM output line. In balanced mode it specifies the shortest high or low times for the pulse train.
     *
     * The way that this works is that you specify a repeat rate in terms of a range parameter which is used to count down to zero at the clock rate. For example, if you set a clock rate of 1KHz and a range of 1024 then the repeat rate is 1/1024 KHz i.e.  0.99Hz - because the PWM cycle starts over after 1024 clock pulses.
     */
    public void setRange(int range) {
        Gpio.pwmSetRange(range);
        this.range=range;
    }

    /**
     * @param clock sets the PWM frequency. To calculate the frequency you have to divide the clock by 18750.
     *              This means a clock of 250 would end up with a frequency of 75 Hz (18750/250=75).
     *              The clock that is supplied to the PWM hardware doesn't set the repeat rate but the smallest unit of change on the PWM output line. In balanced mode it specifies the shortest high or low times for the pulse train.
     *
     * The way that this works is that you specify a repeat rate in terms of a range parameter which is used to count down to zero at the clock rate. For example, if you set a clock rate of 1KHz and a range of 1024 then the repeat rate is 1/1024 KHz i.e.  0.99Hz - because the PWM cycle starts over after 1024 clock pulses.
     */
    public void setClock(int clock) {
        Gpio.pwmSetClock(clock);
    }

    /*
     * @param Mode Either Gpio.PWM_MODE_MS or Gpio.PWM_MODE_BAL
     *             Gpio.PWM_MODE_MS:
     *             In mark/space mode you simply have a repeat rate and a duty cycle.
     *             This is precisely what you need if you want to drive a traditional PWM device such as a servo motor.
     *             However for other applications where the PWM signal is being used as a simple D to A convertor then
     *             this is not the best way to send out a pulse train with a given duty cycle. (D to A -> Digital Analog Converter)
     *             In mark/space mode a 50% duty cycle would simply mean holding the line high for 512 clock pulses and then low for 512 clock pulses and so on.
     *             n mark/space mode the line is held high for the first 512 counts and low for the remainder of the 1024 counts. I
     *             (https://iot-programmer.com/index.php/books/22-raspberry-pi-and-the-iot-in-c/chapters-raspberry-pi-and-the-iot-in-c/60-raspberry-pi-and-the-iot-in-c-pulse-width-modulation-servos-and-more)
     *             Gpio.PWM_MODE_BAL:
     *             For example suppose you want 1KHz pulse train with a 50% duty cycle pulse train to say deliver 50% power or voltage to a device. The mark/space way of doing this switches the GPIO line on for 500 microseconds and off for 500 microseconds. The fluctuations in voltage are very slow and this causes problems for the driven device. A better way would be to divide the time into say 1 microsecond blocks and spread the on and off times throughout the block. In this case the device would be high for 1 microsecond and low for one microsecond and the filtering problems would be much easier to solve.
     *             This idea of distributing the duty cycle over the full repeat period is what balanced mode implements. It is much more suitable to applications such as DtoA conversion and driving loads such as LEDs or motors.
     *             In balanced mode a 50% duty cycle would mean pulses 500 microseconds high and 500 microseconds low. Note that the repeat rate would still be 0,99Hz but the signal would be much higher frequency even though it was a 50% duty cycle.
     *             In balanced mode the line is held high for 512 counts spread over the the total 1024 counts.
     *             (https://iot-programmer.com/index.php/books/22-raspberry-pi-and-the-iot-in-c/chapters-raspberry-pi-and-the-iot-in-c/60-raspberry-pi-and-the-iot-in-c-pulse-width-modulation-servos-and-more)
     */
    public void setMode(int mode) {
        if (mode == Gpio.PWM_MODE_MS || mode == Gpio.PWM_MODE_BAL) {
            Gpio.pwmSetMode(mode);
        } else {
            throw new RuntimeException("Invalid PWM mode selected.");
        }
    }

    public abstract void shutdown();

}
