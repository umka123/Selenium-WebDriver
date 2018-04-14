import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product")
    private WebElement firstProduct;

    public MainPage open() {
        driver.get("http://localhost/litecart");
        return this;
    }

    public void pickProduct() {
        firstProduct.click();
        wait.until(presenceOfElementLocated(By.id("box-product")));
    }
}
