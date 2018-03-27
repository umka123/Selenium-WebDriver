import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class Task12 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        /* perform login */
        driver.get("http://localhost/litecart/admin");
        WebElement id = driver.findElement(By.name("username"));
        id.sendKeys("admin");
        WebElement pw = driver.findElement(By.name("password"));
        pw.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        /* See if login was fine */
        wait.until(urlToBe("http://localhost/litecart/admin/"));

    }

    @Test
    public void Test1() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        wait.until(presenceOfElementLocated(By.cssSelector("a.button")));
        driver.findElement(By.linkText("Add New Product")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("div.tabs")));
        /*** General ***/
        driver.findElement(By.name("status")).click();
        String name = genRandom(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ") + " Duck";
        driver.findElement(By.name("name[en]")).sendKeys(name);
        driver.findElement(By.name("code")).sendKeys("rd999");
        // pick all categories
        List<WebElement> check = driver.findElements(By.name("categories[]"));
        for(WebElement i: check){
            i.click();
        }
        // select subcategory as default
        Select category = new Select(driver.findElement(By.name("default_category_id")));
        category.selectByVisibleText("Subcategory");
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("15");
        Select sold = new Select(driver.findElement(By.name("sold_out_status_id")));
        sold.selectByVisibleText("Temporary sold out");

        File file = new File("black_duck.jpg");
        //System.out.println(file.getAbsolutePath());
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
        new Actions(driver)
                .pause(20)
                .perform();

        /*** Information ***/
        driver.findElement(By.linkText("Information")).click();
        new Actions(driver)
                .pause(20)
                .perform();
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("short_description[en]")).sendKeys("Darth Vader Duck!");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero. ");

        /*** Prices ***/
        driver.findElement(By.linkText("Prices")).click();
        new Actions(driver)
                .pause(20)
                .perform();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("25");
        Select currency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        currency.selectByVisibleText("US Dollars");
        driver.findElement(By.name("prices[USD]")).sendKeys("50");
        //driver.findElement(By.name("gross_prices[USD]")).clear();

        /*** Submit data  and check the catalog ***/
        driver.findElement(By.name("save")).click();
        wait.until(presenceOfElementLocated(By.linkText(name)));
        driver.findElement(By.linkText("Rubber Ducks")).click();
        driver.findElement(By.linkText("Subcategory")).click();
        List<WebElement> newItem = driver.findElements(By.linkText(name));
        assert (newItem.size() == 2);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private String genRandom(int len, String letters){
        StringBuffer gen = new StringBuffer();
        for(int i=0; i < len; i++){
            int rand = (int) (Math.random()*letters.length());
            gen.append(letters.charAt(rand));
        }
        return gen.toString();
    }
}
