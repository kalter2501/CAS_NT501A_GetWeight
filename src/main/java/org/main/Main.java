//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.main;

import com.fazecast.jSerialComm.SerialPort;
import java.nio.charset.StandardCharsets;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        if (!argumentsAreValid(args)) {
            System.out.println("Не найдены аргументы \n Пример аргументов: COM5(обязательный) debug(опционально)");
            System.exit(0);
        }

        String comPortName = args[0];
        SerialPort port = SerialPort.getCommPort(comPortName);
        if (!port.isOpen()) {
            if (port.openPort()) {
                System.out.println("Порт закрыт, открываем порт");
            } else {
                System.out.println("Не удалось открыть порт");
                System.exit(0);
            }
        }

        System.out.println("Порт открыт");
        System.out.println("Устанавливаем параметры порта");
        port.setComPortParameters(9600, 8, 1, 0, false);
        DataReceivedListener dlr = new DataReceivedListener();
        if (args.length > 1) {
            dlr.setDebug(args[1].equalsIgnoreCase("debug"));
            System.out.println("Debug включен");
        }

        port.addDataListener(dlr);
        System.out.println("Обработчик события добавлен");
        byte[] commandGetWeight = "00 RW CR LF".getBytes(StandardCharsets.US_ASCII);
        System.out.println("Команда на получение веса послана, ожидаем ответа");
        port.writeBytes(commandGetWeight, commandGetWeight.length);
    }

    private static boolean argumentsAreValid(String[] args) {
        return args.length >= 1;
    }
}
