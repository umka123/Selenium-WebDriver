import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

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
        String firstName, lastName, email, postcode;
        final String letters = "abcdefghigklmnopqrstuvwxyz";
        final String digits = "0123456789";
        // Generate input data
        int len = (int) (Math.random()*7 + 3);
        firstName = genRandom(len,letters);
        len = (int) (Math.random()*10 + 5);
        lastName = genRandom(len,letters);
        email = firstName + "." + lastName + "@gmail.com";
        postcode=genRandom(5,digits);
        System.out.println(firstName + " " + lastName + " " + email + " " + postcode);

        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("address1")).sendKeys("Address is not unique, 123");
        driver.findElement(By.name("postcode")).sendKeys(postcode);
        
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
