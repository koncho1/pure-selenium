package com.gui;

import com.gui.pages.common.AbstractPage;
import com.gui.pages.common.DriverFactory;
import com.gui.service.ConfigProvider;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public abstract class AbstractTest {

    private static final String WEBSITE_URL = "https://automationteststore.com/";

    private static final String SELENIUM_URL = "selenium_url";

    Properties properties;

    @BeforeSuite(alwaysRun = true)
    public void loadProps() {
        ConfigProvider configProvider = new ConfigProvider();
        properties = configProvider.loadConfig();
    }

    private static Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = "browser")
    public void setUpTest(String browser) {
        try {
            String hubURL = properties.getProperty(SELENIUM_URL);
            DriverFactory driverFactory = new DriverFactory();
            WebDriver driver = driverFactory.createDriver(browser, hubURL);
            driver.get(WEBSITE_URL);
            threadLocalDriver.set(driver);
        } catch (Exception e) {
            logger.error("Error occurred during setup: {}", e.getMessage());
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
            FileUtils.copyFile(file, new File("./screenshot_folder/" + formattedNow + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String title = driver.getTitle();
        logger.info("Captured Screenshot for: " + title);

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
