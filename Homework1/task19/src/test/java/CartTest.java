import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test1() {
        driver.get("http://localhost/litecart");
        Integer n = 3; // amount of products to add
        Integer p = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()); // amount of products in the cart

        /* Add products to the cart */
        for (Integer i = 0; i < n; i++ ){
            driver.findElement(By.cssSelector("li.product")).click();
            wait.until(presenceOfElementLocated(By.cssSelector("h1.title")));
            WebElement title = driver.findElement(By.cssSelector("h1.title"));
            if(driver.findElements(By.cssSelector("select")).size()!=0){
                Select size = new Select(driver.findElement(By.cssSelector("select")));
                size.selectByIndex(1);
            }
            /* add to cart */
            driver.findElement(By.name("add_cart_product")).click();
            p++; //the amount of products increased
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), p.toString()));
            driver.findElement(By.cssSelector("div#logotype-wrapper")).click();
            wait.until(stalenessOf(title));
        }

        driver.findElement(By.linkText("Checkout »")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("div#box-checkout-summary")));
        while (driver.findElements(By.cssSelector("table.dataTable td.item")).size() > 0){
            WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
            int oldItems = driver.findElements(By.cssSelector("table.dataTable td.item")).size();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
            assert(driver.findElements(By.cssSelector("table.dataTable td.item")).size() == oldItems - 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
