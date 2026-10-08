package tests.day14_Notasyonlar;

import org.testng.annotations.*;

//deneme
public class C01_Notasyonlar {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Herşeyden önce beforeSuite notasyonuna sahip method 1 kez çalışır");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Testlerden önce beforeTest notasyonuna sahip method 1 kez çalışır");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Classtan önce beforeClass notasyonuna sahip method 1 kez çalışır");
    }

    @BeforeMethod //Junit'teki @Before ile aynı
    public void setUp(){
        System.out.println("Her method'dan önce 1 kez çalışır");
    }

    @Test
    public void test01() {
        System.out.println("Test01 çalıştı");
    }

    @Test
    public void test02() {
        System.out.println("Test02 çalıştı");
    }
}
