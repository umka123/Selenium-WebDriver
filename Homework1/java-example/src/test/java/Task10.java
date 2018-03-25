import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task10 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test1() {
        /* Start page */
        driver.get("http://localhost/litecart");
        List<WebElement> product = driver.findElements(By.cssSelector("div#box-campaigns li.product"));
        String nameMain, nameProduct;
        int oldPriceMain, oldPriceProduct, newPriceMain, newPriceProduct;
        String oldPriceColor, newPriceColor;

        nameMain = product.get(0).findElement(By.cssSelector("div.name")).getText();
        oldPriceMain = Integer.valueOf(product.get(0).findElement(By.cssSelector("s.regular-price")).getText().substring(1));
        newPriceMain = Integer.valueOf(product.get(0).findElement(By.cssSelector("strong.campaign-price")).getText().substring(1));
        System.out.println("name: " + nameMain + " ole price: " + oldPriceMain + " new price: " + newPriceMain);
        //assert(oldPriceMain > newPriceMain);

        //oldPriceColor = product.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        //System.out.println("oldcolor: " + oldPriceColor);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
