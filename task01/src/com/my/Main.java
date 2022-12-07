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

/* Вывод программы

Программа - телефонный справочник
Для помощи наберите "/help"
Данные прочитаны из файла ./db.json
>>> /help
Программа - поддерживает следующие команды:
/help - вывод помощи
/info - вывод информации о программе
/printAll - печать всей базы целиком
/printCurrent - печать текущей записи базы данных
/addRecord <name> - добавление записи с именем <name>
/addPhone <phoneNumber> - добавление номера телефона <phoneNumber> в текущую запись
/setRecord <name> - установка записи <name> в качестве текущей
/saveJSON <file> - сохранение базы в файл <file> в формате JSON
/loadJSON <file> - чтение из файла <file> в формате JSON базы данных
/saveTXT <file> - сохранение базы в файл <file> в формате TXT
/loadTXT <file> - чтение из файла <file> в формате TXT базы данных
/saveXML <file> - сохранение базы в файл <file> в формате XML
/loadXML <file> - чтение из файла <file> в формате XML базы данных
>>> /printAll
Имя: Olga
Список телефонов у Olga пуст.

Имя: Vasily
	 000-000

Имя: Egor
	 34-34-34
	 56-56-56
	 78-78-78

Имя: Mihail
Список телефонов у Mihail пуст.

Имя: Ivan
	 123-123
	 321-321

Имя: Marina
	 111-222

>>> /loadTXT txt.txt
Данные прочитаны из файла txt.txt
>>> /printAll
Имя: Olga
Список телефонов у Olga пуст.

Имя: Vasily
	 000-000

Имя: Egor
	 34-34-34
	 56-56-56
	 78-78-78

Имя: Mihail
Список телефонов у Mihail пуст.

Имя: Ivan
	 123-123
	 321-321

Имя: Marina
	 111-222

>>> /loadXML xml.xml
Abonents
Данные прочитаны из файла xml.xml
>>> /printAll
Имя: Olga
Список телефонов у Olga пуст.

Имя: Egor
	 34-34-34
	 56-56-56
	 78-78-78

Имя: Ivan
	 123-123
	 321-321

Имя: ttttttt
	 000-000

Имя: Qwerty
Список телефонов у Qwerty пуст.

Имя: Marina
	 111-222

>>> /loadJSON json.json
Данные прочитаны из файла json.json
>>> /printAll
Имя: Olga
Список телефонов у Olga пуст.

Имя: Vasily
	 000-000

Имя: Egor
	 34-34-34
	 56-56-56
	 78-78-78

Имя: Mihail
Список телефонов у Mihail пуст.

Имя: Ivan
	 123-123
	 321-321

Имя: Marina
	 111-222

>>> /quit
Программа завершена.
 */