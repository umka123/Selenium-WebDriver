import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task8 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test1() {
        /* Start page */
        driver.get("http://localhost/litecart");

        /* Get the list of all items */
        List<WebElement> duck;
        duck = driver.findElements(By.cssSelector("li.product"));
        for (WebElement i : duck) {
            // check if there is exactly 1 sticker for each duck
            assert(i.findElements(By.cssSelector("div.sticker")).size() == 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
