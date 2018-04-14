package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "add_cart_product")
    private WebElement addButton;

    @FindBy(css = "span.quantity")
    private WebElement quantity;

    @FindBy(name = "options[Size]")
    private List<WebElement> selectSize;

    private boolean hasSizes() {
        return selectSize.size()>0;
    }

    public ProductPage pickSize(int i) {
        if(this.hasSizes()) {
            Select sizeSelect = new Select(selectSize.get(0));
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
