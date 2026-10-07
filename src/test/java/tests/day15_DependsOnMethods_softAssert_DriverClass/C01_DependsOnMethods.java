package tests.day15_DependsOnMethods_softAssert_DriverClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_DependsOnMethods {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void test01() {
    //Amazon sayfasına gidelim
        driver.get("https://amazon.com");
        driver.navigate().refresh();
        driver.navigate().refresh();
    }

    @Test(dependsOnMethods = "test01")
    public void test02() {
        //Arama kutusunda İphone aratalım
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("iphone", Keys.ENTER);
    }

    @Test(dependsOnMethods = {"test01","test02"})
    public void test03() {
        //Sonuç yazısında iphone içerdiğini test edelim
        WebElement sonucYazisi = driver.findElement(By.xpath("//*[@class='sg-col-inner']"));
        Assert.assertTrue(sonucYazisi.getText().contains("iphone"));

    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}