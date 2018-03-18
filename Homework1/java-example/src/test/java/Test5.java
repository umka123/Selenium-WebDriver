package com.mozilla.example;

import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

/* Run Firefox ESR */

public class Test5 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox ESR\\firefox.exe")));
        options.setCapability(FirefoxDriver.MARIONETTE, false);
        driver = new FirefoxDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test1() {
        driver.get("https://ya.ru/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
