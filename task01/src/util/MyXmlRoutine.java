package util;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Data;

import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;

public class MyXmlRoutine {
    public static Data readData(String filename) {
        Data db = new Data();
        return db;
    }
    public static void writeData(Data db, String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue((XMLStreamWriter) new File(filename), db);
        File file = new File(filename);
        //assertNotNull(file);
    }
    // https://www.baeldung.com/jackson-xml-serialization-and-deserialization
}
