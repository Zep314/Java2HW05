package util;

import model.Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class MyJsonRoutine {
    public static Data readData(String filename) throws IOException, ParseException {
        Data db = new Data();
        if (new File(filename).exists()) {
            FileReader reader = new FileReader(filename);               // Читаем файл
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(reader); // И парсим его в JSON объект
            for(String s: (Set<String>)jsonObj.keySet()) {              // Формируем нашу базу данных
                ArrayList<String> phones = new ArrayList<String>();     // из JSON объекта
                db.dataBase.put(s, phones);
                phones.addAll((ArrayList<String>) jsonObj.get(s));  // устанавливаем все телефоны разом
            }
            System.out.printf("Данные прочитаны из файла %s%n", filename);
        }
        else {
            System.out.printf("Файл %s не найден! Загружена пустая база данных!%n", filename);
        }

        return db;
    }

    public static void writeData(Data db, String filename) throws IOException {
        JSONObject jsonObj = new JSONObject();
        for(String s: db.dataBase.keySet()) {   // Проходим по всей базе и формируем JSON объект
            JSONArray jsonArr = new JSONArray();
            ArrayList<String> phones = db.dataBase.get(s);
            jsonArr.addAll(phones);  // пишем все телефоны разом
            jsonObj.put(s,jsonArr);
        }
        Files.write(Paths.get(filename), jsonObj.toJSONString().getBytes());  // пишем JSON в файл
        System.out.printf("Данные записаны в файл %s%n",filename);
    }
}
