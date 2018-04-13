import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Application {
    private WebDriver driver;
    private  WebDriverWait wait;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    public void quit() {
        driver.quit();
    }

    public void loadMainPage(){
        driver.get("http://localhost/litecart");
    }

    public void addProducts(Products products){
        /* Add products to the cart */
        for (Integer i = 0; i < products.size(); i++ ){
            addOneProduct(products,i);
        }
    }

    public void addOneProduct(Products products, int index){
        loadMainPage();
        Integer p = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()); // amount of products in the cart

        driver.findElement(By.cssSelector("li.product")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("h1.title")));

        if(driver.findElements(By.cssSelector("select")).size()!=0){
            Select sizeSelect = new Select(driver.findElement(By.cssSelector("select")));
            sizeSelect.selectByIndex(products.getProductSize(index));
        }
        /* add to cart */
        driver.findElement(By.name("add_cart_product")).click();
        p++; //the amount of products increased
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), p.toString()));
    }

    public void Checkout(){
        WebElement check = driver.findElement(By.partialLinkText("Checkout"));
        check.click();
        wait.until(stalenessOf(check));
    }

    public void removeAllProducts(){
        while (driver.findElements(By.cssSelector("table.dataTable td.item")).size() > 0){
            WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
            int oldItems = driver.findElements(By.cssSelector("table.dataTable td.item")).size();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
            assert(driver.findElements(By.cssSelector("table.dataTable td.item")).size() == oldItems - 1);
        }
    }

}
