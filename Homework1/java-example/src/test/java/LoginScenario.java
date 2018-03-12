package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class LoginScenario {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test1() {
        driver.get("http://localhost/litecart/admin/login.php");
        WebElement id = driver.findElement(By.name("username"));
        id.sendKeys("admin");
        WebElement pw = driver.findElement(By.name("password"));
        pw.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(vFunction)
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
