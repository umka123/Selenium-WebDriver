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
        List<WebElement> s1,s2;
        assert(country.size() > 0);
        // check the first country
        // field 5 is the amount of zones
        String s = country.get(0).findElements(By.cssSelector("td")).get(5).getText();
        int zones = Integer.parseInt(s);
        if (zones != 0){
            System.out.println(country.get(0).findElements(By.cssSelector("td")).get(5).getText() + " zones!");
            assert(isSorted(driver,wait,country.get(0)));
            country = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        }
        // check the rest of the countries:
        // a) if they are ordered
        // b) if they have multiple zones
        for (int i = 1; i < country.size(); i++){
            // field 4 is country name
            // field 5 is amount of zones
            s1 = country.get(i-1).findElements(By.cssSelector("td"));
            s2 = country.get(i).findElements(By.cssSelector("td"));
            assert(s1.get(4).getText().compareTo(s2.get(4).getText()) < 0);
            s = country.get(i).findElements(By.cssSelector("td")).get(5).getText();
            zones = Integer.parseInt(s);
            if (zones != 0){
                System.out.println(country.get(i).findElements(By.cssSelector("td")).get(5).getText() + " zones!");
                assert(isSorted(driver,wait,country.get(i)));
                country = driver.findElements(By.cssSelector("table.dataTable tr.row"));
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public static boolean isSorted (WebDriver driver, WebDriverWait wait, WebElement row){
        row.findElement(By.cssSelector("a")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("table#table-zones")));

        List<WebElement> zone = driver.findElements(By.cssSelector("table#table-zones tr"));
        System.out.println(zone.size());
        assert(zone.size() > 2); //the table will contain at least 2 rows: the header and the new line
        // check them
        for (int j=2; j < zone.size()-1; j++){
            List<WebElement> s1=zone.get(j-1).findElements(By.cssSelector("td"));
            List<WebElement> s2=zone.get(j).findElements(By.cssSelector("td"));
            if (s1.get(2).getText().compareTo(s2.get(2).getText()) >= 0){
                //get back anyway
                driver.findElements(By.cssSelector("li#app-")).get(2).click();
                wait.until(urlToBe("http://localhost/litecart/admin/?app=countries&doc=countries"));
                return false;
            }
        }
        //get back to the countries list
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(urlToBe("http://localhost/litecart/admin/?app=countries&doc=countries"));
        return true;
    }
}
