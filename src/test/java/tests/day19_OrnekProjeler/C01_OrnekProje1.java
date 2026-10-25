package tests.day19_OrnekProjeler;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.SauceConfigReader;
import utilities.Driver;
import pages.ProductsPage;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;
import pages.CartPage;
import pages.CheckoutPage;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import pages.CheckoutCompletePage;

public class C01_OrnekProje1 {

    @Test
    public void gecerliKullaniciGirisYapabilmeli() {
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        Assert.assertEquals(loginPage.pageTitle.getText(), "Products");
    }

    @Test
    public void yanlisSifreHataVermeli() {
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("sauceWrongPassword"));
        loginPage.loginButton.click();

        Assert.assertEquals(loginPage.errorMessage.getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void bosKullaniciAdiHataVermeli() {
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.loginButton.click();

        Assert.assertEquals(loginPage.errorMessage.getText(),
                SauceConfigReader.getProperty("sauceUsernameRequiredError"));
    }

    @Test
    public void kilitliKullaniciGirisYapamamali() {
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceLockedUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        Assert.assertEquals(loginPage.errorMessage.getText(),
                SauceConfigReader.getProperty("sauceLockedUserError"));
    }

    @Test
    public void urunListesindeAltiUrunOlmali() {
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        ProductsPage productsPage = new ProductsPage();
        int beklenenSayi = Integer.parseInt(SauceConfigReader.getProperty("sauceExpectedProductCount"));

        Assert.assertEquals(productsPage.productList.size(), beklenenSayi);
    }

    @Test
    public void fiyataGoreDusuktenYuksegeSiralamaDogruOlmali() {
        //Siteye gir ve giriş yap
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        //Sıralama menüsünden “Price (low to high)” seç
        ProductsPage productsPage = new ProductsPage();

        Select select = new Select(productsPage.sortDropdown);
        select.selectByVisibleText(SauceConfigReader.getProperty("sauceSortLowToHigh"));

        //Ekrandaki fiyatları oku ve sayıya çevir
        List<Double> ekrandakiFiyatlar = new ArrayList<>();
        for (WebElement fiyat : productsPage.priceList) {
            ekrandakiFiyatlar.add(Double.parseDouble(fiyat.getText().replace("$", "")));
        }

        //Aynı fiyatları kendimiz küçükten büyüğe sırala
        List<Double> siralanmisFiyatlar = new ArrayList<>(ekrandakiFiyatlar);
        Collections.sort(siralanmisFiyatlar);

        //Ekrandaki sıra ile bizim sıraladığımız listeyi karşılaştır
        Assert.assertEquals(ekrandakiFiyatlar, siralanmisFiyatlar);
    }

    @Test
    public void sepeteUrunEklenincRozetBirOlmali() {
        //Siteye git ve giriş yap
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        //İlk ürünü sepete ekle
        ProductsPage productsPage = new ProductsPage();
        productsPage.firstAddToCartButton.click();

        //Sepet rozetindeki yazıyı beklenen değerle karşılaştır
        Assert.assertEquals(productsPage.cartBadge.getText(),
                SauceConfigReader.getProperty("sauceExpectedCartBadge"));
    }

    @Test
    public void sepettekiUrunAdiDogruOlmali() {
        //Siteye git ve giriş yap
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        //İlk ürünün adını oku ve sakla
        ProductsPage productsPage = new ProductsPage();
        String urunSayfasindakiAd = productsPage.firstProductName.getText();

        //İlk ürünü sepete ekle ve sepete git
        productsPage.firstAddToCartButton.click();
        productsPage.cartLink.click();

        //Sepetteki ürün adını, sakladığımız adla karşılaştır
        CartPage cartPage = new CartPage();
        Assert.assertEquals(cartPage.cartItemName.getText(), urunSayfasindakiAd);
    }

    @Test
    public void bosOdemeFormuHataVermeli() throws Exception {
        //Siteye git ve giriş yap
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        //Chrome'un şifre uyarısını Enter ile kapat
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //İlk ürünü sepete ekle ve sepete git
        ProductsPage productsPage = new ProductsPage();
        productsPage.firstAddToCartButton.click();
        productsPage.cartLink.click();

        //Checkout butonuna bas
        CartPage cartPage = new CartPage();
        cartPage.checkoutButton.click();

        //Formu boş bırakıp Continue'ya bas
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.continueButton.click();

        //Hata mesajını beklenen metinle karşılaştır
        Assert.assertEquals(checkoutPage.errorMessage.getText(),
                SauceConfigReader.getProperty("sauceFirstNameRequiredError"));
    }

    @Test
    public void tamCheckoutAkisiTamamlanmali() throws Exception {
        //Siteye git ve giriş yap
        Driver.getdriver().get(SauceConfigReader.getProperty("sauceUrl"));

        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(SauceConfigReader.getProperty("sauceUser"));
        loginPage.passwordInput.sendKeys(SauceConfigReader.getProperty("saucePassword"));
        loginPage.loginButton.click();

        //Chrome'un şifre uyarısını Enter ile kapat
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //İlk ürünü sepete ekle ve sepete git
        ProductsPage productsPage = new ProductsPage();
        productsPage.firstAddToCartButton.click();
        productsPage.cartLink.click();

        //Checkout butonuna bas
        CartPage cartPage = new CartPage();
        cartPage.checkoutButton.click();

        //Formu doldur ve Continue'ya bas
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.firstNameInput.sendKeys(SauceConfigReader.getProperty("sauceCheckoutFirstName"));
        checkoutPage.lastNameInput.sendKeys(SauceConfigReader.getProperty("sauceCheckoutLastName"));
        checkoutPage.postalCodeInput.sendKeys(SauceConfigReader.getProperty("sauceCheckoutZipCode"));
        checkoutPage.continueButton.click();

        //Siparişi tamamla
        checkoutPage.finishButton.click();

        //Teşekkür mesajını doğrula
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        Assert.assertEquals(completePage.completeHeader.getText(),
                SauceConfigReader.getProperty("sauceOrderCompleteMessage"));
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }
}