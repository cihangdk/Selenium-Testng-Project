package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;

        static {
            properties = new Properties();

            try {
                FileInputStream fis = new FileInputStream("configuration.properties");
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    /*
       .properties uzantılı dosyadaki datalara erişebilmek için return type'ı String olan parametreli static bir
    method oluştururuz.
       Bu method içinde Properties classından bir obje oluştururuz ve FileInputStream ile .properties uzantılı
    dosyanın yolunu belirterek java ortamında dosyayı okuturuz.Properties classından oluşturdugumuz objeye
    akışa aldığımız dosyayı (fis) load() methodu ile yükleriz.Ve son olarak properties classından oluşturdugumuz
    obje ile getProperty() methodunu kullanarak .properties uzantılı dosyadaki key'in değerini bize return eder.
     */
}
