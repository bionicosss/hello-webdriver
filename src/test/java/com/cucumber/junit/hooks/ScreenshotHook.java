package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.File;
import java.io.IOException;
import java.util.Date;


public class ScreenshotHook {
    static final Logger logger = LogManager.getLogger(ScreenshotHook.class);
    private Date today = new Date();
    private String scrFileName = today.toString().replace(" ","_").replace(":","_");

    @After
    public void takeScreenshot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {

            logger.trace("Test failed. Take screenshot: \\target\\screenshot\\" + scrFileName + ".png");

//            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "img/png", scenario.getName());

            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File destination = new File(".//target//screenshot//"+scrFileName+".png");
            FileUtils.copyFile(screenshot,destination);

        }
    }
}
