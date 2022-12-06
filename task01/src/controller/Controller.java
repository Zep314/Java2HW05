package controller;

import model.Data;
import util.MyJsonRoutine;
import util.MyXmlRoutine;
import util.Settings;
import view.View;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;


public class Controller {  // Класс контроллера - тут все и происходит
    private final Logger log = Logger.getLogger(Controller.class.getName());
    Data db = new Data();
    View view = new View(this.log);  // Цепляем вьювер
//    List<WorkJob> list = new ArrayList<>();  // Данные из модели
    public Controller() {  // Активируем логгер
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Controller.class.getResourceAsStream("../log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
    public void run() throws IOException, ParseException {  // основной метод
        boolean ex = false;
        Scanner scan = new Scanner(System.in);
        String inputLine = "";
        view.info();

        loadFromJSON(Settings.db);

        while (!ex) {  // главный цикл программы
            System.out.print(">>> ");
            inputLine = scan.nextLine();
            if (inputLine.length()>0) {
                switch (inputLine.split(" ",2)[0]) {
                    // ждем команду
                    case "/quit" -> ex = true;         // Выход
                    case "/info" -> this.view.info();  // Информация
                    case "/help" -> this.view.help();  // Помощь
                    case "/addRecord" -> view.addRecord(db, inputLine.split(" ",2)[1]);  // Добавляем новую запись
                    case "/addPhone" -> view.addPhone(db ,inputLine.split(" ",2)[1]);
//                    case "/editRecord" -> view.editRecord();  // Редактируем текущую запись
//                    case "/editPhone" -> ;
                    case "/printAll" -> view.printAll(this.db);
                    case "/printCurrent" -> view.printCurrent(this.db);  // Печатаем текущую запись
                    case "/setRecord" -> setRecord(inputLine.split(" ",2)[1]);        // Установка указателя на нужную запись
                    case "/saveJSON" -> saveToJSON(inputLine.split(" ",2)[1]);            // Сохранение всего списка задач в файл JSON
                    case "/loadJSON" -> loadFromJSON(inputLine.split(" ",2)[1]);          // Загрузка из файла JSON списка задач
                    case "/saveXML" -> saveToXML(inputLine.split(" ",2)[1]);            // Сохранение всего списка задач в файл JSON
                    case "/loadXML" -> loadFromXML(inputLine.split(" ",2)[1]);          // Загрузка из файла JSON списка задач
                    default -> view.errorCommand(inputLine);
                }
            }
        }
        saveToJSON(Settings.db);
        view.bye();
    }

    private void setRecord(String record) {
        if (db.dataBase.containsKey(db.getCurrent())) {
            db.setCurrent(record);
            System.out.printf("Текущая запись %s установлена!%n", record);
        }
        else {
            System.out.printf("Записи с именем %s не существует!%n Текущей записью остается %s%n", record, db.getCurrent());
        }
    }
    private void loadFromJSON(String file) throws IOException, ParseException {
        this.db = MyJsonRoutine.readData(file);

        Map.Entry<String, ArrayList> entry = db.dataBase.entrySet().iterator().next();
        db.setCurrent(entry.getKey());
    }

    private void saveToJSON(String file) throws IOException {
        MyJsonRoutine.writeData(this.db,file);
    }
    private void loadFromXML(String file) throws IOException, ParseException {
        this.db = MyXmlRoutine.readData(file);

        Map.Entry<String, ArrayList> entry = db.dataBase.entrySet().iterator().next();
        db.setCurrent(entry.getKey());
    }

    private void saveToXML(String file) throws IOException {
        MyXmlRoutine.writeData(this.db,file);
    }
}
