import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class Task14 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        /* Log in */
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
        /* Get to the countries page and click the "button" link to add the new country */
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.linkText("Add New Country")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("i.fa-external-link")));

        /* Get the main window id and the amount of the old windows */
        /* Yes, it should be 1, but lets' get it just in case :) */
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        int w = oldWindows.size();

        /* Click every external link */
        List<WebElement> links = driver.findElements(By.cssSelector("i.fa-external-link"));
        for(int i = 0; i < links.size(); i++){
            links.get(i).click();
            wait.until(numberOfWindowsToBe(w+1)); // new window opened
            /* get new window id */
            String newWindow = getNewWindow(oldWindows,driver.getWindowHandles());
            driver.switchTo().window(newWindow);
            // do nothing, just close it
            driver.close();
            driver.switchTo().window(mainWindow);
            wait.until(numberOfWindowsToBe(w)); // new window closed
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;

    }

    private String getNewWindow(Set<String> oldSet,Set<String> newSet){
        /* Return a window id that is not in the old set */
        /* Return null if there is no such id */
        Iterator<String> i = newSet.iterator();
        while(i.hasNext()) {
            String w = i.next();
            if (!oldSet.contains(w)) {
                return w;
            }
        }
        return null;
    }

}