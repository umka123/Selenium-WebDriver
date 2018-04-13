import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    public int itemsQuantity() {
        return driver.findElements(By.cssSelector("td.item")).size();
    }

    public boolean isEmpty() {
        return this.itemsQuantity()==0;
    }

    public void removeItem() {
        if(!this.isEmpty()) {
            WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
        }
    }

}
