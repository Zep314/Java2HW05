package util;

import java.time.format.DateTimeFormatter;

// Настройки всякие
public class Settings {
    public static final String db = "./db.json";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
}
