package tests.day16_POM_DataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class C03_DataProvider {
    /*
        DataProvider, testlerimizde birçok veriyi loop kullanmadan aktarmak için kullanılabılır.Bunun için
    öncelıkle @Test methodumuzu oluşturur ve @Test notasyonundan sonra arguman olarak dataProvider yazıp
    String olarak bir isim belirtiriz.Belirttiğimiz isim üzerine gelip otomatik olarak @DataProvider
    notasyonuna sahip 2 boyutlu Object return eden bir method oluştururuz.
       @Dataprovider notasyonuna sahip method'a aşagıdaki gibi kullanmak istediğimiz dataları gireriz.Bu
    dataları kullanabılmek için Test methodumuzda String bir parametre ekleriz.
    */
    @DataProvider
    public static Object[][] araclar() {
        return new Object[][]{{"Audi"},
                              {"BMW"},
                              {"Tesla"},
                              {"Mercedes"},
                              {"Ford"}};
    }

    @Test(dataProvider ="araclar")
    public void test01(String data) {
        System.out.println(data);
    }
}
