import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task11 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test1() {
        driver.get("http://localhost/litecart/en/create_account");
        String firstName, lastName, email, postcode, phone, password;
        final String letters = "abcdefghigklmnopqrstuvwxyz";
        final String digits = "0123456789";
        // Generate input data
        int len = (int) (Math.random()*7 + 3);
        firstName = genRandom(len,letters);
        len = (int) (Math.random()*10 + 5);
        lastName = genRandom(len,letters);
        email = firstName + "." + lastName + "@gmail.com";
        postcode=genRandom(5,digits);
        phone=genRandom(10,digits);
        password="Pass-Word";
        //System.out.println(firstName + " " + lastName + " " + email + " " + postcode);

        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("address1")).sendKeys("Address is not unique, 123");
        driver.findElement(By.name("postcode")).sendKeys(postcode);
        driver.findElement(By.name("city")).sendKeys("Same-City-For-All");
        //select country
        WebElement country = driver.findElement(By.name("country_code"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.opacity=1", country);
        Select select = new Select(country);
        select.selectByVisibleText("United States");
        // select geozone
        Select zone = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        int i = (int)(Math.random()*50 + 1);
        zone.selectByIndex(i);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+1" + phone);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        // submit form
        driver.findElement(By.name("create_account")).click();
        wait.until(presenceOfElementLocated(By.linkText("Logout")));

        //logout
        driver.findElement(By.linkText("Logout")).click();
        wait.until(presenceOfElementLocated(By.name("email")));

        //log in
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        //logout
        wait.until(presenceOfElementLocated(By.linkText("Logout")));
        driver.findElement(By.linkText("Logout")).click();
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
