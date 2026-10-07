# Selenium TestNG Test Otomasyon Projesi

Amazon ve BestBuy gibi e-ticaret sitelerinde yazdığım, eğitim amaçlı web test otomasyon projesi.
Bu siteler sadece test pratiği için kullanılmıştır, sitelerle herhangi bir bağlantım yoktur.

## Kullanılan Teknolojiler

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- DataProvider (veri odaklı test)
- XML ile test suite yönetimi

## Proje Yapısı

- `src/test/java/pages`: Page Object sınıfları
- `src/test/java/tests`: Test sınıfları (notasyonlar, DependsOnMethods, soft assert, DataProvider)
- `src/test/java/utilities`: Yardımcı sınıflar (Driver, ConfigReader, ReusableMethods, DataProviderUtil)

## Nasıl Çalıştırılır

1. Projeyi indirin: `git clone https://github.com/cihangdk/Selenium-Testng-Project.git`
2. IntelliJ IDEA ile açın, Maven bağımlılıklarının inmesini bekleyin.
3. Proje ana klasörüne `configuration.properties` dosyası oluşturun (aşağıya bakın).
4. Testleri `testng.xml` / `classlar.xml` ile veya sınıf üzerinden çalıştırın.

## configuration.properties

Bu dosya kişisel bilgiler içerebileceği için repo'ya eklenmemiştir. Kendi dosyanızı oluşturun.
Örnek içerik (kendi değerlerinizle değiştirin):

    browser=chrome
    amazonUrl=https://www.amazon.com
    bestBuyUrl=https://www.bestbuy.com