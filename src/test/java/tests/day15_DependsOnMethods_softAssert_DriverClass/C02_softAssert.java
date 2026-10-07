package tests.day15_DependsOnMethods_softAssert_DriverClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class C02_softAssert {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void test01() throws InterruptedException {
        /*
             SoftAssert testNG den gelen bir assertion türüdür.Daha önce öğrendiğimiz hardAssert nerede hata
        alırsa kodu orada durdururken,softAssret biz nerede istersek orada durdurur.Bunun için kodlarımızdan
        sonra assertAll() methodunu kullanmamız yeterlidir.
             SoftAssert kullanabilmek için  SoftAssert classından bir obje oluştururuz.
        */
        /*
        Test dataları;
        nafawah933@dmener.com
        test123456Aa
         */

        // https://power-pulse-qa.f.goit.study/ sayfasına gidelim
        driver.get("https://power-pulse-qa.f.goit.study/");

        //Başlığın Power Pulse olduğunu doğrulayalım
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getTitle().contains("Power Pulse"));

        //Sign In butonuna tıklayalım
        WebElement signButton = driver.findElement(By.xpath("//*[@class='WelcomPage_linkSignIn__dpaLe']"));
        softAssert.assertTrue(signButton.isDisplayed());
        signButton.click();

        //E-Mail ve pass bilgilerini girelim
        WebElement email = driver.findElement(By.name("email"));
        softAssert.assertTrue(email.isEnabled());
        email.sendKeys("nafawah933@dmener.com", Keys.TAB, "test123456Aa", Keys.ENTER);


        //3 nokta olan yere tıklayalım
        WebElement ucNokta = driver.findElement(By.xpath("//*[@class='Header_burger_btn__CiJ3x']"));
        ucNokta.click();

        //products bölümüne girelim
        WebElement products = driver.findElement(By.xpath("(//*[text()='Products'])[2]"));
        products.click();
        softAssert.assertTrue(products.isDisplayed());

        // Çıkan alerti kapat
        driver.findElement(By.xpath("//*[@class='CloseBtn_close_btn__xXxBN']")).click();

        /*categories seçeneğinden tüm seçenekleri seçip çıkan tablodaki categori bölümlerinin aynı categori
        isimine sahip olup olmadıklarını doğrulayalım*/
        WebElement categori = driver.findElement(By.xpath("//*[@name='category']"));
        Thread.sleep(1000);
        categori.click();
        List<WebElement> categoriList = driver.findElements(By.xpath("//select//option"));
        categoriList.forEach(t -> System.out.println(t.getText()));
        System.out.println("*******************************");


        for (int i = 0; i < categoriList.size(); i++) {

            categoriList.get(i).click();
            Thread.sleep(1);
            System.out.println(categoriList.get(i).getText());
            Thread.sleep(1);
            WebElement tableCategoriName = driver.findElement(By.xpath("//li[@class='ExercisesItem_productCategory__w+tQU']//span"));
            softAssert.assertEquals(categoriList.get(i).getText(), tableCategoriName.getText());
        }

        //softAssert.assertAll();
    }
        @AfterClass
        public void afterClass() throws InterruptedException {
            Thread.sleep(3000);
            driver.quit();

    }
}
