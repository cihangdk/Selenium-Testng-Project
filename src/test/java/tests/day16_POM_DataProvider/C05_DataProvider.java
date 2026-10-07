package tests.day16_POM_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

public class C05_DataProvider {

        Locates locates;
        @DataProvider(name ="aramaTesti")
        public static Object[][] araclar() {
            return new Object[][]{{ConfigReader.getProperty("marka1")},
                                  {ConfigReader.getProperty("marka2")},
                                  {ConfigReader.getProperty("marka3")},
                                  {ConfigReader.getProperty("marka4")},
                                  {ConfigReader.getProperty("marka5")}};
        }

        @Test(dataProvider = "aramaTesti")
        public void test02(String data) {
            locates = new Locates();

            //Amazon sayfasına gidelim
            Driver.getdriver().get(ConfigReader.getProperty("amazonUrl"));
            Driver.getdriver().navigate().refresh();
            Driver.getdriver().navigate().refresh();

            //Arama kutusunda {Audi,BMW,Tesla,Mercedes,Ford} aratalim.
            locates.aramaKutusu.sendKeys(data, Keys.ENTER);
    }
}
