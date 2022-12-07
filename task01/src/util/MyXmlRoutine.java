package util;
import model.Data;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class MyXmlRoutine {  // Читаем / Пишем в XML файл
    public static Data readData(String filename) {
        Data db = new Data();
        db.clear();
        if (new File(filename).exists()) {  // Проверка на существование
            File xmlFile = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                // создаем пустой объект Document, в который считаем xml-файл
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();

                Element root = document.getDocumentElement();  // Вытаскиваем корень XML
                System.out.println(root.getNodeName());

                NodeList nList = document.getElementsByTagName("Abonent");  // Список таких элементов ищем

                for (int i = 0; i < nList.getLength(); i++) {  // Обработка списка
                    if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nList.item(i);
                        ArrayList<String> phones = new ArrayList<String>();
                        for (int j = 0; j < eElement.getChildNodes().getLength(); j++) {  // Тут обрабатываем
                                                                                          // список телефонов
                            if (eElement.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                                phones.add(eElement.getChildNodes().item(j).getTextContent());
                            }
                        }
                        db.dataBase.put(eElement.getAttribute("name"), phones);  // Записываем все в базу данных
                    }
                }
                System.out.printf("Данные прочитаны из файла %s%n", filename);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        else {
            System.out.printf("Файл %s не найден! Загружена пустая база данных!%n", filename);
        }
        return db;
    }

    public static void writeData(Data db, String filename) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document document = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    document.createElementNS("http://www.w3.org/2001/XMLSchema", "Abonents");
            // добавляем корневой элемент в объект Document
            document.appendChild(rootElement);

            Element element;
            for(String s: db.dataBase.keySet()) { // создаем новый узел XML
                element = document.createElement("Abonent");
                element.setAttribute("name", s);  // устанавливаем атрибут
                Element node;
                ArrayList<String> phones = db.dataBase.get(s);
                for(String p: phones) {  // создаем новый подузел XML
                    node = document.createElement("phone");
                    node.appendChild(document.createTextNode(p));
                    element.appendChild(node);
                }
                rootElement.appendChild(element);
            }
            //создаем объект TransformerFactory для печати в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в файл
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            //печатаем в файл
            StreamResult file = new StreamResult(new File(filename));

            //записываем данные
            transformer.transform(source, file);
            System.out.printf("Данные записаны в файл %s%n",filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
