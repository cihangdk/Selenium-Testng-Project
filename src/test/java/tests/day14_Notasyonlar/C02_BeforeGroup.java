package tests.day14_Notasyonlar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_BeforeGroup {
    WebDriver driver;
    /*
    @BeforeGroup notasyonuna sahip method ile test methodlarımızı gruplayabiliriz.Bunun için @BeforeGroup
  notasyonundan sonra arguman olarak bir isim belirtiriz.Gruplamak istediğimiz test methodlarına sahip
  @Test notasyonundan sonra arguman olarak groups = "isim" yazarak testlerimizi gruplamış oluruz
     */
    @BeforeGroups({"chrome","youtube"})
    public void beforeGroups1(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
}
    @BeforeGroups("firefox")
    public void beforeGroups2(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(groups = "chrome")
    public void test01() {
        driver.get("https://bestbuy.com");
    }

    @Test(groups = "firefox")
    public void test02() {
       driver.get("https://facebook.com");
    }

    @Test(groups = "firefox")
    public void test03() {
        driver.get("https://amazon.com");
    }

    @Test(groups = "youtube")
    public void test04() {
        driver.get("https://youtube.com");
    }

    @AfterGroups({"firefox","youtube"})
    public void afterGroups(){
        driver.quit();
    }


}
