package tests.day16_POM_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.Driver;
import utilities.ReusableMethods;

public class C01_PageClassKullanimi {
    @Test
    public void test01() {
        SoftAssert softAssert = new SoftAssert();
        Locates locates = new Locates();
    /*
    Username: student
    Password: Password123
    */
        //https://practicetestautomation.com/practice-test-login/ adresine gidelim
        Driver.getdriver().get("https://practicetestautomation.com/practice-test-login/");

        //Sayfa içindeki başlıkta Test login yazdığını doğrulayalım.
        softAssert.assertEquals(locates.sayfaiciBaslik.getText(),"Test login");

        //Username ve password bilgilerini ilgili alanlara girelim ve giriş yapalım.
        locates.username.sendKeys("student", Keys.TAB,"Password123",Keys.TAB,Keys.ENTER);
        ReusableMethods.bekle(2);

        //Logged In Successfully yazdığını doğrulayalım.
        softAssert.assertEquals(locates.loginSuccesHeader.getText(),"Logged In Successfully");
        softAssert.assertAll();
        locates.bekle(2);
        Driver.quitDriver();
    }
}
