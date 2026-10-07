package tests.day15_DependsOnMethods_softAssert_DriverClass;

import org.testng.annotations.Test;
import utilities.Driver;

public class C03_DriverClassKullanimi {
    @Test
    public void test01() {
        Driver.getdriver().get("https://facebook.com");
        Driver.getdriver().get("https://bestbuy.com");
        Driver.getdriver().get("https://youtube.com");
    }

    @Test
    public void test02() {
        Driver.getdriver().get("https://facebook.com");
        Driver.getdriver().get("https://bestbuy.com");
        Driver.quitDriver();
        Driver.getdriver().get("https://youtube.com");

    }
}
