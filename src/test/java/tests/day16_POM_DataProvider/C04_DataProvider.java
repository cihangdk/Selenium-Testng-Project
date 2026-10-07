package tests.day16_POM_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C04_DataProvider {
    Locates locates;

    @Test
    public void test01() {
        locates = new Locates();

        //Amazon sayfasına gidelim
        Driver.getdriver().get(ConfigReader.getProperty("amazonUrl"));
        Driver.getdriver().navigate().refresh();
        Driver.getdriver().navigate().refresh();

        //Arama kutusunda {Audi,BMW,Tesla,Mercedes,Ford} aratalim.
        List<String> araclar = new ArrayList<>(Arrays.asList("Audi", "Bmw", "Tesla", "Mercedes", "Ford"));

        for (int i = 0; i < araclar.size(); i++) {

            locates.aramaKutusu.sendKeys(araclar.get(i), Keys.ENTER);
            locates.bekle(2);
            Driver.getdriver().navigate().back();
            locates.aramaKutusu.clear();
        }
    }
    @DataProvider(name ="aramaTesti")
    public static Object[][] araclar() {
        return new Object[][]{{"Audi"},
                              {"BMW"},
                              {"Tesla"},
                              {"Mercedes"},
                              {"Ford"}};
    }

    @Test(dataProvider = "aramaTesti")
    public void test02(String data) {
        locates = new Locates();

        //Amazon sayfasına gidelim
        Driver.getdriver().get(ConfigReader.getProperty("amazonUrl"));
        Driver.getdriver().navigate().refresh();
        Driver.getdriver().navigate().refresh();

        //Arama kutusunda {Audi,BMW,Tesla,Mercedes,Ford} aratalim.
        locates.aramaKutusu.sendKeys(data,Keys.ENTER);
    }

    @Test(dataProvider = "aramaTesti")
    public void test03(String data) {
        /*
        Örneğin farklı bir sayfada da yukarıdaki araçları aratacağız.Bu gibi durumlarda da daha önceden
    oluşturdugumuz @DataProvider notasyonuna sahip methodu kullanabiliriz.
         */
        System.out.println(data);
    }
}