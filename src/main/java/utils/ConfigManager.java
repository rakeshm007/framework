package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration file not found or could not be loaded.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}