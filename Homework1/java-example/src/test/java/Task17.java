import com.sun.org.apache.xerces.internal.impl.dv.util.ByteListImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class Task17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        /* login */
        driver.get("http://localhost/litecart/admin/login.php");
        WebElement id = driver.findElement(By.name("username"));
        id.sendKeys("admin");
        WebElement pw = driver.findElement(By.name("password"));
        pw.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(urlToBe("http://localhost/litecart/admin/"));
    }

    @Test
    public void Test1() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait.until(presenceOfElementLocated(By.cssSelector("table.dataTable")));
        List<WebElement> links = driver.findElements(By.cssSelector("table.dataTable a:not([title=Edit])"));
        List<String> products = new ArrayList<String>();
        /* Get the list of products */
        for(int i = 0; i < links.size(); i++){
            if(links.get(i).getAttribute("href").contains("product_id")){
                products.add(links.get(i).getText());
            }
        }

        /* click all the products sequentially */
        for(String s: products){
            driver.findElement(By.linkText(s)).click();
            wait.until(presenceOfElementLocated(By.cssSelector("form[method=post] div.tabs")));
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            wait.until(presenceOfElementLocated(By.cssSelector("table.dataTable")));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
