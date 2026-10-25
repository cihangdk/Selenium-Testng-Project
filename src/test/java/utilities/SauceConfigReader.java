package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class SauceConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("saucedemo.properties");
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException("saucedemo.properties okunamadi", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}