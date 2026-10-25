package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutCompletePage {

    public CheckoutCompletePage() {
        PageFactory.initElements(Driver.getdriver(), this);
    }

    @FindBy(css = "[data-test='complete-header']")
    public WebElement completeHeader;
}