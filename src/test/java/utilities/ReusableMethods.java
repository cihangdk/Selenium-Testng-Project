package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ReusableMethods {
    protected static WebDriver driver;
    protected static Select select;
    protected static Actions actions;
    protected static WebDriverWait wait;
    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;
    protected static JavascriptExecutor js;

    @Before
    public  void setUp() throws Exception {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public  void tearDown() throws Exception {
        bekle(3);
        //driver.quit();
    }

    //bekleme metodu (Hard wait)
    public static void bekle(int saniye) {
        try {
            Thread.sleep(1000 * saniye);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //pencere geçiş

    public static void window(int index) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[index].toString());

    }

    //Ddm index
    public static void selectindex(WebElement ddmElement, int index) {
        select = new Select(ddmElement);
        select.selectByIndex(index);
    }

    //Alert accept
    public static void alertOK() {
        driver.switchTo().alert().accept();
    }

    // Webdriver wait visibility
    public static void gorunenekadarbekle(WebElement element, int saniye) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(saniye));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Webdriver wait invisibility
    public static void kaybolanakadarbekle(WebElement element, int saniye) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(saniye));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // Webdriver wait visibilityLocate
    public static void webdriverWait(By locate, int saniye) {
        new WebDriverWait(driver, Duration.ofSeconds(saniye))
                .until(ExpectedConditions.visibilityOfElementLocated(locate));
    }

    //Download wait
    public static void downloadwait(String dosyaYolu) {
        for (int i = 0; i < 100; i++) {

            if (Files.exists(Paths.get(dosyaYolu))) {
                break;
            }
            bekle(1);
        }
    }

    //Dosya sil
    public static void dosyasil(String dosyaYolu) {
        try {
            Files.delete(Paths.get(dosyaYolu));
        } catch (IOException e) {
            System.out.println("dosya bulunamadı");
            ;
        }
    }

    //Upload file
    public static void upload(String dosyayolu) throws AWTException {
        try {
            StringSelection stringSelection = new StringSelection(dosyayolu);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); //Panoya dosya yolu kopyalandı ctrl+c
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            bekle(2);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            bekle(2);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //screenshot
    public static void screenShot() {
        String tarih = new SimpleDateFormat("_HH_mm_ss_dd.MM.yyyy").format(new Date());
        String dosyayolu = System.getProperty("user.dir") + "/src/test/java/resources/screenShot/" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
        bekle(1);
        try {
            Files.write(Paths.get(dosyayolu), ts.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Webelement ScreenShot
    public static void webElemetScreenShot(WebElement element) {
        String tarih = new SimpleDateFormat("_HH_mm_ss_dd.MM.yyyy").format(new Date());
        String dosyayolu = System.getProperty("user.dir") + "/src/test/java/resources/WebelementScreenShot/" + tarih + ".png";
        bekle(1);
        try {
            Files.write(Paths.get(dosyayolu), element.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Rapor webelemet Screenshot
    public static String raporWEScreenShot(WebElement element) {
        return element.getScreenshotAs(OutputType.BASE64);
    }

    //ExtentReport
    public static void report(String raporismi, String Testadi, String Testaciklama, String qa) {
        String tarih = new SimpleDateFormat("_HH_mm_ss_dd.MM.yyyy").format(new Date());
        String dosyayolu = System.getProperty("user.dir") + "/src/test/java/resources/reports/" + tarih + ".html";

        extentReports = new ExtentReports();
        extentHtmlReporter = new ExtentHtmlReporter(dosyayolu);
        extentReports.attachReporter(extentHtmlReporter);

        //Raporda görünmesini istediğimiz bilgiler için;
        extentReports.setSystemInfo("QA", qa);
        extentReports.setSystemInfo("Browser", "Chrome");
        extentHtmlReporter.config().setReportName(raporismi);
        extentHtmlReporter.config().setDocumentTitle("ExtentReport A.ş.");
        extentTest = extentReports.createTest("Arama Kutusu", "Arama Kutusunda Ürün Aratma");
    }

    //JsExecuter click
    public static void jsClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    //JsExecuter Scroll Element
    public static void jsScrollElement(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);

    }

    //JsExecuter Scroll End Element
    public static void jsScrollEnd() {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

    }

    //JsExecuter Scroll Home
    public static void jsScrollHome() {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");

    }

    //JsExecuter SendKeys
    public static void jsSendKeys(String text, WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value ='"+text+"'",element);
    }

}
