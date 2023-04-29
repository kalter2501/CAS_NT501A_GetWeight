//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.main;

import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.nio.charset.StandardCharsets;

public class DataReceivedListener implements SerialPortDataListener {
    private boolean debug;

    DataReceivedListener() {
        this.setDebug(false);
    }

    void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getListeningEvents() {
        return 16;
    }

    public void serialEvent(SerialPortEvent event) {
        System.out.println("Вызвано событие - " + event.getEventType());
        if (event.getEventType() == 16) {
            System.out.println("Вход в обработку события");
            byte[] data = event.getReceivedData();
            System.out.println("Идет декодирование байтов в строку ASCII");
            if (this.debug) {
                System.out.println("Режим отладки включен, вывод всех полученных от порта байтов в ASCII");
                System.out.println(new String(data, StandardCharsets.US_ASCII));
                System.exit(1);
            }

            if (data.length < 22) {
                System.out.println("Размер полученного от порта массива байт неверный!");
                System.exit(0);
            }

            System.out.println(new String(data, 8, 8, StandardCharsets.US_ASCII));
            System.exit(1);
        }

        System.exit(0);
    }
}
