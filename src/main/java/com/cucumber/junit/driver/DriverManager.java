package com.cucumber.junit.driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;

public class DriverManager {
    static final Logger logger = LogManager.getLogger(DriverManager.class);

    private final static String CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";
    private final static int IMPLICIT_WAIT_TIMEOUT = 5;
    private final static int PAGELOAD_TIMEOUT = 20;
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();


    private DriverManager() {
    }

    public static void setupDriver(){
        logger.trace("Start setup driver");
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
        webDriverThreadLocal.set(driver);
        logger.trace("End setup driver");
    }

    public static WebDriver getDriver(){
//        logger.trace("Get driver");
        return webDriverThreadLocal.get();

    }

    public static void quitDriver(){
        Optional.ofNullable(getDriver()).ifPresent(webDriver -> {
            logger.trace("Quit driver");
            webDriver.quit();
            webDriverThreadLocal.remove();
        });

    }
}
