import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart");
        return this;
    }

    public void pickProduct() {
        driver.findElement(By.className("product")).click();
        wait.until(presenceOfElementLocated(By.id("box-product")));
    }
}
