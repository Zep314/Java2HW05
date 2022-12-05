package view;

import model.Data;

import java.util.ArrayList;
import java.util.logging.Logger;

public class View {   // Класс вьювера - вывод на экран и взаимодействие с пользователем
    private final Logger log;
    public View(Logger log) {
        this.log = log;
    }  // чтобы писать в один и тот же лог, что и в контроллере
    public void info() {  // информация
        log.info("/info");
        System.out.println("Программа - телефонный справочник");
        System.out.println("Для помощи наберите \"/help\"");
    }
    public void bye() {  // Прощание
        log.info("/quit");
        System.out.println("Программа завершена.");
    }
    public void errorCommand(String cmd) {  // Ошибку ввели
        log.info(String.format("Error command: \"%s\"\n",cmd));
        System.out.println("Неверная команда. Для помощи наберите \"help\"");
    }
    public void help() {  // выводим помощь
        log.info("/help");
        System.out.println("Программа - поддерживает следующие команды:");
        System.out.println("/help - вывод помощи");
        System.out.println("/info - вывод информации о программе");
        System.out.println("/printAll - печать всей базы целиком");
        System.out.println("/printCurrent - печать текущей записи базы данных");
        System.out.println("/addRecord <name> - добавление записи с именем <name>");
        System.out.println("/addPhone <phoneNumber> - добавление номера телефона <phoneNumber> в текущую запись");
        System.out.println("/setRecord <name> - установка записи <name> в качестве текущей");
        System.out.println("/editRecord <name> - редактирование записи с индексом <name>");
        System.out.println("/editPhone <phoneNumber> - редактирование номера телефона <phoneNumber> в текущей записи");
        System.out.println("/saveJSON <file> - сохранение базы в файл <file> в формате JSON");
        System.out.println("/loadJSON <file> - чтение из файла <file> в формате JSON базы данных");
        System.out.println("/saveTXT <file> - сохранение базы в файл <file> в формате TXT");
        System.out.println("/loadTXT <file> - чтение из файла <file> в формате TXT базы данных");
        System.out.println("/saveXML <file> - сохранение базы в файл <file> в формате XML");
        System.out.println("/loadXML <file> - чтение из файла <file> в формате XML базы данных");
    }

//    public void printOneJob(Job job) {  // Выводим одну запись на экран
//        log.info(String.format("/printCurrent: %s%n",((WorkJob)job).toString()));
//        System.out.println("Текущая задача:");
//        System.out.println("ID: "+((WorkJob)job).getId().toString());
//        System.out.println("Приоритет: "+((WorkJob)job).getPriority());
//        System.out.println("Автор: "+((WorkJob)job).getAuthor());
//        System.out.println("Текст задачи: "+((WorkJob)job).getSubject());
//        System.out.println("Задача создана: "+((WorkJob)job).getCreationDT().format(Settings.formatter));
//        System.out.println("Дедлайн: "+((WorkJob)job).getDeadlineDT().format(Settings.formatter));
//    }
//    public WorkJob recordAddEdit(boolean edit, WorkJob job) {  // Добавление или редактирование одной записи
//        WorkJob newRecord = new WorkJob();
//        log.info(edit ? String.format("/editRecord: %s%n", ((WorkJob)job).toString()) : "/addRecord");
//        Scanner scan = new Scanner(System.in);
//        String inputLine = "";
//        boolean isCorrect = false;
//        System.out.println(edit ? "Редактирование текущей записи" : "Добавление новой записи");
//
//        if (edit) {
//            System.out.println("Если хотите оставить поле без изменений - просто нажмите Enter");
//            System.out.printf("Текст задачи: %s%n", job.getSubject());
//        }
//        // Обработка ввода текста задачи
//        System.out.print("Введите текст задачи: ");
//        inputLine = scan.nextLine();
//        newRecord.setSubject((edit) ? (inputLine.length()>0) ? inputLine : job.getSubject() : inputLine);
//
//        do {  // обработка ввода приоритета
//            if (edit) {
//                System.out.printf("Текущий приоритет %s%n", job.getPriority());
//            }
//            System.out.printf("Введите приоритет задачи: %s1 - LOW; 2 - MIDDLE; 3 - HIGH: ",
//                                (edit) ? "0 - не меняем; " : "");
//            inputLine = scan.nextLine();
//
//            if (inputLine != null && inputLine.matches("[0-9]+")) {
//                if (0 <= Integer.parseInt(inputLine) && Integer.parseInt(inputLine) < 4) {
//                    isCorrect = true;
//                }
//            }
//            if (!isCorrect) {
//                System.out.println("Некорректный ввод! повторите!");
//            }
//        } while (!isCorrect);
//        if (edit && Integer.parseInt(inputLine) == 0) {
//            newRecord.setPriority(job.getPriority().getPriority());
//        }
//        else {
//            switch (Integer.parseInt(inputLine)) {
//                case Priority.MIDDLE -> newRecord.setPriority(Priority.MIDDLE);
//                case Priority.HIGH -> newRecord.setPriority(Priority.HIGH);
//                default -> newRecord.setPriority(Priority.LOW);
//            }
//        }
//
//        // обработка ввода имени автора
//        if (edit) {
//            System.out.printf("Автор: %s%n", job.getAuthor());
//        }
//        System.out.print("Введите автора: ");
//        inputLine = scan.nextLine();
//        newRecord.setAuthor((edit) ? (inputLine.length()>0) ? inputLine : job.getAuthor() : inputLine);
//
//        // Обработка времени создания записи
//        // Если редактируем - то время создания - прежнее
//        if (edit) {
//            newRecord.setCreationDT(job.getCreationDT());
//        }
//        else {  // Ели добавили новую задачу - ставим текущее время
//            newRecord.setCreationDT(LocalDateTime.now());
//        }
//
//        // Обработка ввода времени дедлайна
//        isCorrect = false;
//        do {
//            if (edit) {
//                System.out.printf("Текущий дедлайн: %s%n", job.getDeadlineDT().format(Settings.formatter));
//            }
//            System.out.print("Введите дату и время дедлайна в формате (dd-MM-yyyy HH:mm:ss): ");
//            inputLine = scan.nextLine();
//            try {
//                isCorrect = true;
//            } catch (Exception e) {
//                if (edit && inputLine.length() == 0) {
//                    isCorrect = true;
//                }
//                else {
//                    System.out.println("Некорректный ввод! повторите!");
//                }
//            }
//        } while (!isCorrect);
//        if (edit && inputLine.length() == 0) {
//            newRecord.setDeadlineDT(job.getDeadlineDT());
//        }
//        else {
//            newRecord.setDeadlineDT(LocalDateTime.parse(inputLine,Settings.formatter));
//        }
//
//        System.out.print("Сохранить информацию? [Y/n]");
//        inputLine = scan.nextLine();
//        if (inputLine.equals("Y") || inputLine.isEmpty()) {
//            log.info(String.format("%s%n%s%n",(edit) ? "Отредактированная запись:":"Новая запись:",newRecord.toString()));
//            System.out.println("Информация сохранена");
//            return newRecord;
//        }
//        else {
//            log.info("Операция отменена.");
//            return null;
//        }
//    }
//    public void printAll(List<WorkJob> localList,Integer index) {  // Красивый вывод на печать всего списка задач
//        WorkJobStream stream = new WorkJobStream(localList);
//        WorkJob job = new WorkJob();
//        log.info("/printAll");
//        System.out.println("| ID|Приор.|      Дедлайн      |                      Задача                      |             Автор            |       Создано     |");
//        System.out.printf("+%s+%s+%s+%s+%s+%s+%n"
//                , "-".repeat(3)
//                , "-".repeat(6)
//                , "-".repeat(19)
//                , "-".repeat(50)
//                , "-".repeat(30)
//                , "-".repeat(19)
//        );
//        stream.mySort();  // Сортируем список в соответствие с правилами
//        while (stream.hasNext()) {
//            job = stream.next();
//            System.out.printf("%s%3d|%6s|%19s|%50s|%30s|%19s|%n"
//                    , (job.getId() == index) ? "*" : "|"
//                    , job.getId()
//                    , ((Priority)job.getPriority()).toString()
//                    , job.getDeadlineDT().format(Settings.formatter)
//                    , job.getSubject()
//                    , job.getAuthor()
//                    , job.getCreationDT().format(Settings.formatter)
//                    );
//        }
//    }
//    public Integer setIndex() {  // установка индекса текущей записи
//        log.info("/setRecord");
//        Scanner scan = new Scanner(System.in);
//        while (true) {
//            System.out.print("Введите новый индекс текущей записи: ");
//            String inputLine = scan.nextLine();
//            if (inputLine != null && inputLine.matches("[0-9]+")) {
//                if (0 < Integer.parseInt(inputLine)) {
//                    return Integer.parseInt(inputLine);
//                }
//            }
//                System.out.println("Некорректный ввод! повторите!");
//        }
//    }
    public void printCurrent(Data db) {
        log.info(String.format("/printCurrent %s",db.getCurrent()));
        if (!db.dataBase.containsKey(db.getCurrent())) {
            System.out.println("Записи с таким именем нет!");
        }
        else {
            ArrayList<String> phones = db.dataBase.get(db.getCurrent());
            System.out.printf("Имя: %s%n",db.getCurrent());  // Наименование записи
            if (phones.size() == 0) {
                System.out.printf("Список телефонов у %s пуст.%n", db.getCurrent());
            }
            else {
                for(String s: phones) {  // Список всех телефонов текущей записи
                    System.out.printf("\t %s%n",s);
                }
            }
        }
    }

    public void printAll(Data db) {
        log.info("/printAll");
        String saveCurrent = db.getCurrent();
        for(String s: db.dataBase.keySet()) {
            db.setCurrent(s);
            printCurrent(db);
            System.out.println();  // Разделитель между записями
        }
        db.setCurrent(saveCurrent);
    }
    public void addRecord(Data db, String name) {
        log.info(String.format("/addRecord %s", name));
        if (name.length() > 0) {
            if (db.addRecord(name) == 0) {
                System.out.println("Запись добавлена.");
            }
            else {
                System.out.println("Запись с таким именем уже есть!");
            }
        }
        else {
            System.out.println("Укажите имя абонента!\n Запись не добавлена!");
        }
    }

    public void addPhone(Data db,String phone) {
        log.info(String.format("/addPhone %s > %s", db.getCurrent(),phone));
        if (db.getCurrent().length() == 0) {
            System.out.println("База данных пуста! Не к чему добавлять телефон!");
            System.out.println("Сначала добавьте запись в базу данных!");
        }
        else {
            if (phone.length() > 0) {
                db.addPhone(phone);
                System.out.println("Номер телефона добавлен.");
            }
            else {
                System.out.println("Укажите номер телефона!\n Номер телефона не добавлен!");
            }
        }

    }
}
