import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "add_cart_product")
    public WebElement addButton;

    @FindBy(css = "span.quantity")
    public WebElement quantity;

    @FindBy(name = "options[Size]")
    public WebElement selectSize;

    public boolean hasSizes() {
        return driver.findElements(By.name("options[Size]")).size()>0;
    }

    public ProductPage pickSize(int i) {
        if(this.hasSizes()) {
            Select sizeSelect = new Select(selectSize);
            sizeSelect.selectByIndex(i);
        }
        return this;
    }

    public void addToCart(){
        Integer p = Integer.parseInt(quantity.getText()); // amount of products already in cart
        addButton.click();
        p++;    //the amount of products increased
        wait.until(textToBePresentInElementLocated(By.cssSelector("span.quantity"), p.toString()));
    }
}
