package tests.day14_Notasyonlar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class C03_Priority {
    /*
        TestNG'de test methodalarımızı istediğimiz sırada çalıstırmak istersek @Test notasyonundan sonra
    arguman olarak priority yazarak sıralayabiliriz.@Test notasyonuna sahip bir method'da priority yoksa
    default olarak 0(sıfır) dır.
     */
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test     //Bir şey yazmadık otomatikman sıfır olarak kabul eder 2.olarak çalışır
    public void test01() {
        driver.get("https://bestbuy.com");
    }

    @Test(priority = 1) //3.olarak çalışır
    public void test02() {
        driver.get("https://facebook.com");
    }

    @Test(priority = -1) //1.olarak bu method çalışır
    public void test03() {
        driver.get("https://amazon.com");
    }

    @Test(priority = 2) //4.olarak çalışır
    public void test04() {
        driver.get("https://youtube.com");
    }
}
