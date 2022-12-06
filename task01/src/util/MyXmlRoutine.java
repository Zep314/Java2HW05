package util;
import model.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class MyXmlRoutine {
    public static Data readData(String filename) {
        Data db = new Data();

        File xmlFile = new File(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
            // получаем узлы с именем Language
            // теперь XML полностью загружен в память
            // в виде объекта Document
            NodeList nodeList = document.getElementsByTagName("Abonent");
//            NodeList nodeList = document.getElementsByTagName("Abonent");

            for (int i = 0; i < nodeList.getLength(); i++) {
                ArrayList<String> phones = new ArrayList<String>();
                String name = "";
                Node element = nodeList.item(i);
                name = element.getAttributes().item(0).getNodeName();
                db.dataBase.put(name,phones);
                //langList.add(getLanguage(nodeList.item(i)));
            }

            // печатаем в консоль информацию по каждому объекту Language
//            for (Language lang : langList) {
//                System.out.println(lang.toString());
//            }
        } catch (Exception exc) {
            exc.printStackTrace();
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
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    doc.createElementNS("http://www.w3.org/2001/XMLSchema", "Abonents");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);

            Element element;
            for(String s: db.dataBase.keySet()) { // создаем новый узел XML
                element = doc.createElement("Abonent");
                element.setAttribute("name", s);  // устанавливаем атрибут
                Element node;
                ArrayList<String> phones = db.dataBase.get(s);
                for(String p: phones) {  // создаем новый подузел XML
                    node = doc.createElement("phone");
                    node.appendChild(doc.createTextNode(p));
                    element.appendChild(node);
                }
                rootElement.appendChild(element);
            }
            //создаем объект TransformerFactory для печати в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в файл
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

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
