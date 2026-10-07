package tests.day_17_DataProviderClass_XmlFile;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.Locates;
import utilities.ConfigReader;
import utilities.DataProviderUtil;
import utilities.Driver;

public class C01_DataProviderClass {
    @Test(dataProvider = "kullaniciBilgileri",dataProviderClass = DataProviderUtil.class)
    public void test01(String email, String pass) {
        Locates locates = new Locates();

        //demo.opencart adresine gidelim
        Driver.getdriver().get(ConfigReader.getProperty("automationUrl"));

        //Login olalım click yapalım
        locates.login.click();

        //E-mail adersler ile başarılı bir şekilde login olup,login olduğumuzu test edelim
        locates.email.sendKeys(email, Keys.TAB ,pass,Keys.ENTER);
        locates.bekle(1);
        locates.logout.click();
    }
}
