import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        // General
        driver.findElement(By.name("status")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Black Duck");
        driver.findElement(By.name("code")).sendKeys("rd999");
        // pick all categories
        List<WebElement> check = driver.findElements(By.name("categories[]"));
        for(WebElement i: check){
            i.click();
        }
        // select subcategory as default
        Select category = new Select(driver.findElement(By.name("default_category_id")));
        category.selectByVisibleText("Subcategory");
        driver.findElement(By.name("quantity")).sendKeys("10");
        Select sold = new Select(driver.findElement(By.name("sold_out_status_id")));
        sold.selectByVisibleText("Temporary sold out");

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
