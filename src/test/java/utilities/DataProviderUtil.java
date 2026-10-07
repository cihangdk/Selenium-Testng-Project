package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
    @DataProvider
    public Object[][] kullaniciBilgileri() {
        return new Object[][]{{ConfigReader.getProperty("email1"),ConfigReader.getProperty("pass1")},
                              {ConfigReader.getProperty("email2"),ConfigReader.getProperty("pass2")},
                              {ConfigReader.getProperty("email3"),ConfigReader.getProperty("pass3")},
                              {ConfigReader.getProperty("email4"),ConfigReader.getProperty("pass4")}
        };
    }
}
