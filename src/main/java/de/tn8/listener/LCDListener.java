package de.tn8.listener;

import de.tn8.impl.LCD1602;

public interface LCDListener {

    public void sendTemperatureAndHumidity(double tempperature, double humidity);
    public void sendCo2Ppm (int ppm);

}
