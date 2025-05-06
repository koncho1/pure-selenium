package com.gui;

import com.gui.pages.common.DriverFactory;
import com.gui.service.ConfigProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public abstract class AbstractTest {

    private static final String WEBSITE_URL = "https://automationteststore.com/";

    private static final String SELENIUM_URL = "selenium_url";

    private static final String SCREENSHOT_FOLDER = "./screenshot_folder/";

    private static Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    Properties loadProperties;

    @BeforeSuite(alwaysRun = true)
    public void loadProps() {
        ConfigProvider configProvider = new ConfigProvider();
        loadProperties = configProvider.loadConfig();
    }


    @BeforeMethod(alwaysRun = true)
    @Parameters(value = "browser")
    public void setUpTest(String browser) {
        try {
            String hubURL = loadProperties.getProperty(SELENIUM_URL);
            DriverFactory driverFactory = new DriverFactory();
            WebDriver driver = driverFactory.createDriver(browser, hubURL);
            driver.get(WEBSITE_URL);
            threadLocalDriver.set(driver);
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver for browser: {}. Error: {}", browser, e.getMessage(), e);
        }
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void takeScreenshot(WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);


        try {

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formattedNow = now.format(formatter);

            String filename = SCREENSHOT_FOLDER + "screenshot_" + formattedNow + ".png";
            FileUtils.copyFile(file, new File(filename));
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        logger.info("Captured Screenshot for: " + driver.getTitle());

    }


    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}
