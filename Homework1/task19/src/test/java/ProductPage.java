import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasSizes() {
        return driver.findElements(By.name("options[Size]")).size()>0;
    }

    public ProductPage pickSize(int i) {
        if(this.hasSizes()) {
            Select sizeSelect = new Select(driver.findElement(By.name("options[Size]")));
            sizeSelect.selectByIndex(i);
        }
        return this;
    }

    public void addToCart(){
        Integer p = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()); // amount of products already in cart
        driver.findElement(By.name("add_cart_product")).click();
        p++;    //the amount of products increased
        wait.until(textToBePresentInElementLocated(By.cssSelector("span.quantity"), p.toString()));
    }
}
