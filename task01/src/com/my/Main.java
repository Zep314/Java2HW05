package com.my;
import controller.Controller;

import java.io.IOException;

/* Задание
Дописать телефонный справочник:
Создать телефонный справочник с возможностью импорта и
экспорта данных в нескольких форматах.
под форматами понимаем структуру файлов, например:
- в файле на одной строке хранится одна часть записи, пустая
строка - разделитель

 */

public class Main {
    public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException {
        Controller controller = new Controller();
        controller.run();
    }
}