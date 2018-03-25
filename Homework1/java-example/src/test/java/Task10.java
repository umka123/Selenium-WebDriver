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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

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
        WebElement oldPrice, newPrice;
        String nameMain, nameProduct;
        int oldPriceMain, oldPriceProduct, newPriceMain, newPriceProduct;
        String oldPriceColor, newPriceColor;
        String oldPriceStyle, newPriceStyle;

        /* Check the main page */
        nameMain = product.get(0).findElement(By.cssSelector("div.name")).getText();
        oldPrice = product.get(0).findElement(By.cssSelector(".regular-price"));
        newPrice = product.get(0).findElement(By.cssSelector(".campaign-price"));

        oldPriceMain = Integer.valueOf(oldPrice.getText().substring(1));
        newPriceMain = Integer.valueOf(newPrice.getText().substring(1));
        assert(oldPriceMain > newPriceMain);

        oldPriceColor = oldPrice.getCssValue("color");
        assert(isGrey(oldPriceColor));
        newPriceColor = newPrice.getCssValue("color");
        assert(isRed(newPriceColor));

        oldPriceStyle = oldPrice.getCssValue("text-decoration");
        assert(oldPriceStyle.substring(0,12).compareTo("line-through") == 0);
        newPriceStyle = newPrice.getCssValue("font-weight");
        assert(Integer.valueOf(newPriceStyle) >= 700);

        /* Check the product page */
        product.get(0).click();
        wait.until(presenceOfElementLocated(By.cssSelector("h1")));
        nameProduct = driver.findElement(By.cssSelector("h1")).getText();
        assert (nameMain.compareTo(nameProduct)==0);

        oldPrice = driver.findElement(By.cssSelector("div.information .regular-price"));
        oldPriceProduct = Integer.valueOf(oldPrice.getText().substring(1));
        assert (oldPriceMain == oldPriceProduct);
        oldPriceColor = oldPrice.getCssValue("color");
        assert(isGrey(oldPriceColor));

        newPrice = driver.findElement(By.cssSelector("div.information .campaign-price"));
        newPriceProduct = Integer.valueOf(newPrice.getText().substring(1));
        assert (newPriceMain == newPriceProduct);
        newPriceColor = newPrice.getCssValue("color");
        assert (isRed(newPriceColor));

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private static boolean isGrey (String color){
        Pattern regexp = Pattern.compile("rgba?\\((\\d*)\\D*(\\d*)\\D*(\\d*).*\\)");
        Matcher matcher = regexp.matcher(color);
        assert(matcher.matches());

        int r = Integer.valueOf(matcher.group(1));
        int g = Integer.valueOf(matcher.group(2));
        int b = Integer.valueOf(matcher.group(3));

        return( r==g && g==b); // the color is grey
    }

    private static boolean isRed (String color){
        Pattern regexp = Pattern.compile("rgba?\\((\\d*)\\D*(\\d*)\\D*(\\d*).*\\)");
        Matcher matcher = regexp.matcher(color);
        assert(matcher.matches());

        int g = Integer.valueOf(matcher.group(2));
        int b = Integer.valueOf(matcher.group(3));

        return( g==0 && b==0); // the color is red
    }
}
