package de.tn8.impl;

import com.pi4j.io.serial.*;
import de.tn8.listener.LCDListener;
import de.tn8.monitor.AirQualityMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MHZ19B implements Runnable{

    private static final Logger logger = LogManager.getLogger(MHZ19B.class);

    private final Serial serial;
    private int ppm = -1;
    private boolean checksumCorrect = false;
    private SerialConfig serialConfig;

    private List<LCDListener> lcdListenerList = new ArrayList<LCDListener>();

    private boolean threadActive = false;

    public MHZ19B(SerialConfig serialConfig) throws IOException, InterruptedException {
        this.serialConfig = serialConfig;
        logger.info("Serial Config:" + serialConfig);
         serial = SerialFactory.createInstance();

        serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent serialDataEvent) {
                try {
                    logger.info(serialDataEvent.getHexByteString());
                    byte [] response = serialDataEvent.getBytes();

                    int checksum = 0;
                    for (int i = 1; i < response.length-1; i++){
                        //do & oxFF in order to convert to int. You could have a signed byte here
                        checksum += response[i]& 0xff;
                    }
                    checksum %= 255;
                    checksum = 255 - checksum;
                    checksum++;
                    logger.info(checksum);
                    //allow checksum to be different by 1
                    if(checksum == (response[8]&0xff) || checksum+1 == (response[8]&0xff) ){
                        logger.info("ppm: " + (256*(int)response[2]+(response[3] & 0xff)));
                        ppm = (256*(int)response[2]+(response[3] & 0x7F) + (response[3]&0x80));
                        checksumCorrect = true;
                    }else {
                        checksumCorrect = false;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void init(SerialConfig config) throws IOException {
        logger.info(config + " Serial closed: " + serial.isClosed());

        serial.open(config);
    }

    private byte[] generateReceiveDataCommand(){
        return new byte [] {(byte)0xFF, (byte)0x01, (byte)0x86, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x79};
    }

    private byte[] generateZeroPointCalibrationCommand(){
        return new byte [] {(byte)0xFF, (byte)0x01, (byte)0x87, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x78};
    }


    public void sendZeroPointCalibration() throws IOException {
        if(serial.isClosed()){
            serial.open(serialConfig);
        }
        serial.write(generateZeroPointCalibrationCommand());
    }


    public int getData() throws IOException, InterruptedException {
        if(serial.isClosed()){
            serial.open(serialConfig);
        }
        serial.discardAll();
            checksumCorrect = false;
            while(!checksumCorrect) {
                serial.write(generateReceiveDataCommand());
                Thread.sleep(2000);
            }
        return ppm;
    }

    public int getPpm() {
        return ppm;
    }

    public void stopThread(){
        threadActive = false;
    }

    public void addLCDListener(LCDListener lcdListener){
        lcdListenerList.add(lcdListener);
    }

    @Override
    public void run() {

        threadActive = true;

        while(threadActive){

            try {
                getData();
                for (int i = 0; i < lcdListenerList.size(); i++){
                    lcdListenerList.get(i).sendCo2Ppm(ppm);
                }
                //after a succesfull read wait 10 seconds
                Thread.sleep(10000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
