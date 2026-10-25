package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutPage {

    public CheckoutPage() {
        PageFactory.initElements(Driver.getdriver(), this);
    }

    @FindBy(css = "[data-test='continue']")
    public WebElement continueButton;

    @FindBy(css = "[data-test='error']")
    public WebElement errorMessage;

    @FindBy(css = "[data-test='firstName']")
    public WebElement firstNameInput;

    @FindBy(css = "[data-test='lastName']")
    public WebElement lastNameInput;

    @FindBy(css = "[data-test='postalCode']")
    public WebElement postalCodeInput;

    @FindBy(css = "[data-test='finish']")
    public WebElement finishButton;
}