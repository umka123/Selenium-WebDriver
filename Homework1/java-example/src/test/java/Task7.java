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
        menu = driver.findElements(By.cssSelector("li#app-"));
        for (int i=0; i < menu.size(); i++) {
            menu = driver.findElements(By.cssSelector("li#app-"));
            menu.get(i).click();
            wait.until(presenceOfElementLocated(By.cssSelector("h1")));
            // See if a cascading menu appeard
            submenu = driver.findElements(By.cssSelector("ul.docs span.name"));
            for(int j=0; j < submenu.size(); j++){
                submenu = driver.findElements(By.cssSelector("ul.docs span.name"));
                submenu.get(j).click();
                wait.until(presenceOfElementLocated(By.cssSelector("h1")));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
