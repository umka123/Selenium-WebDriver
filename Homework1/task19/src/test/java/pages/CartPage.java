package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage open() {
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    @FindBy(css = "td.item")
    private List<WebElement> items;

    @FindBy(name = "remove_cart_item")
    private WebElement removeButton;

    public int itemsQuantity() {
        return items.size();
    }

    public boolean isEmpty() {
        return this.itemsQuantity()==0;
    }

    public void removeItem() {
        if(!this.isEmpty()) {
            WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
            removeButton.click();
            wait.until(stalenessOf(table));
        }
    }

}
