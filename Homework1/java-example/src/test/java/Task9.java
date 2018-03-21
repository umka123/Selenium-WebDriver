import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

/* to do:
 a) write a procedure to check if web element list is sorted
 b) check the amount of zones, check that zones are sorted too
*/

public class Task9 {
    private WebDriver driver;
    private WebDriverWait wait;

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
        /* Start page */
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(presenceOfElementLocated(By.cssSelector("h1")));

        /* Get the list of all countries */
        List<WebElement> country = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        assert(country.size() > 0);
        for (int i = 1; i < country.size(); i++){
            // field 4 is country name
            // field 5 is amount of zones
            List<WebElement> s1 = country.get(i-1).findElements(By.cssSelector("td"));
            List<WebElement> s2 = country.get(i).findElements(By.cssSelector("td"));
            assert(s1.get(4).getText().compareTo(s2.get(4).getText()) < 0);
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
