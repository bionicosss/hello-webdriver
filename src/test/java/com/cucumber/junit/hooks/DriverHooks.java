package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.DriverManager;
//import org.junit.After;
import io.cucumber.java.After;
import io.cucumber.java.Before;
//import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverHooks {
    @Before
    public void setupDriver() {
        DriverManager.setupDriver();
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        assertEquals("src/main/resources/chromedriver.exe", System.getProperty("webdriver.chrome.driver"));
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    @After
    public void quitDriver(){
        DriverManager.quitDriver();
    }

}
