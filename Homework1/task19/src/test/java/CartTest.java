import com.tngtech.java.junit.dataprovider.UseDataProvider;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartTest extends TestBase{
    //List<Product> products = new ArrayList<>();

    @Test
    //@UseDataProvider(value = "validProducts", location = DataProviders.class)
    public void AddRemove() {
        Products products = Products.newEntity().withAmount(3).build();

        loadMainPage();

        addProducts(products);

        Checkout();

        removeAllProducts();
    }


    private void loadMainPage(){
        driver.get("http://localhost/litecart");
    }

    private void addProducts(Products products){
        Integer p = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()); // amount of products in the cart

        /* Add products to the cart */
        for (Integer i = 0; i < products.size(); i++ ){
            driver.findElement(By.cssSelector("li.product")).click();
            wait.until(presenceOfElementLocated(By.cssSelector("h1.title")));
            WebElement title = driver.findElement(By.cssSelector("h1.title"));

            if(driver.findElements(By.cssSelector("select")).size()!=0){
                Select sizeSelect = new Select(driver.findElement(By.cssSelector("select")));
                sizeSelect.selectByIndex(products.getProductSize(i));
            }
            /* add to cart */
            driver.findElement(By.name("add_cart_product")).click();
            p++; //the amount of products increased
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), p.toString()));
            driver.findElement(By.cssSelector("div#logotype-wrapper")).click();
            wait.until(stalenessOf(title));
        }
    }

    private void Checkout(){
        WebElement check = driver.findElement(By.partialLinkText("Checkout"));
        check.click();
        wait.until(stalenessOf(check));
    }

    private void removeAllProducts(){
        while (driver.findElements(By.cssSelector("table.dataTable td.item")).size() > 0){
            WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
            int oldItems = driver.findElements(By.cssSelector("table.dataTable td.item")).size();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
            assert(driver.findElements(By.cssSelector("table.dataTable td.item")).size() == oldItems - 1);
        }
    }
}
