package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {  // Класс - хранилище данных
    // dataBase - будет static - чтобы большие данные хранить в одном месте памяти
    public static Map<String, ArrayList> dataBase = new HashMap<String, ArrayList>();
    private String current = "";  // указатель на текущую запись в базе
    public Data() {

    }

    public void setCurrent(String s) {  // Установка указателя на требуемую запись
        this.current = s;
    }
    public String getCurrent() {
        return this.current;
    }

    public int addRecord(String name) {  // добавление нового элемента в базу
        if (!dataBase.containsKey(name)) {
            dataBase.put(name, new ArrayList<String>());
            setCurrent(name);  // Установка указателя на новую запись
            return 0;
        } else {
            return -1;
        }
    }
    public void addPhone(String phone) {  // Добавление нового телефона у текущему элементу в базе
        ArrayList<String> phones = dataBase.get(this.current);
        phones.add(phone);
    }

    public void clear() {  // Чистим базу, т.к. dataBase - static
        setCurrent("");
        dataBase.clear();
    }
}
