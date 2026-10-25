package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ProductsPage {

    public ProductsPage() {
        PageFactory.initElements(Driver.getdriver(), this);
    }

    @FindBy(css = "[data-test='inventory-item']")
    public List<WebElement> productList;

    @FindBy(css = "[data-test='product-sort-container']")
    public WebElement sortDropdown;

    @FindBy(css = "[data-test='inventory-item-price']")
    public List<WebElement> priceList;

    @FindBy(xpath = "(//button[text()='Add to cart'])[1]")
    public WebElement firstAddToCartButton;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    public WebElement cartBadge;

    @FindBy(xpath = "(//div[@data-test='inventory-item-name'])[1]")
    public WebElement firstProductName;

    @FindBy(css = "[data-test='shopping-cart-link']")
    public WebElement cartLink;
}