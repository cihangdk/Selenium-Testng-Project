package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.getdriver(), this);
    }

    @FindBy(css = "[data-test='inventory-item-name']")
    public WebElement cartItemName;

    @FindBy(css = "[data-test='checkout']")
    public WebElement checkoutButton;
}