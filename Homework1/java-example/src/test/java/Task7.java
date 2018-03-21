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

public class Task7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test1() {
        /* perform login */
        driver.get("http://localhost/litecart/admin");
        WebElement id = driver.findElement(By.name("username"));
        id.sendKeys("admin");
        WebElement pw = driver.findElement(By.name("password"));
        pw.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        /* See if login was fine */
        wait.until(urlToBe("http://localhost/litecart/admin/"));

        /* Click on every menu item */
        List<WebElement> menu;
        List<WebElement> submenu;
        int n = driver.findElements(By.className("name")).size();
        for (int i=0; i < n; i++) {
            menu = driver.findElements(By.className("name"));
            menu.get(i).click();
            // See if a cascading menu appeard
            submenu = driver.findElements(By.cssSelector("ul.docs"));
            if(submenu.size() > 1){
               // several objects found: either a bug or a bad CSS selector
                System.out.println("Something is wrong!");
            }
            if(submenu.size() == 0){
                // No cascading menu: don't worry, go on
                continue;
            }
            // There is exactly 1 submenu found
            submenu.get(0).findElements()

            //wait.until(presenceOfElementLocated(By.name("h1")))
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
