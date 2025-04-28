package com.gui.pages.common;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    protected URL startStandaloneGrid(String hubURL) {
        try {
            Main.main(
                    new String[]{
                            "standalone",
                            "--selenium-manager",
                            "true",
                            "--enable-managed-downloads",
                            "true",
                            "--log-level",
                            "WARNING"
                    });
            return new URL(hubURL);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start standalone Selenium Grid", e);
        }
    }

    public WebDriver createDriver(String browser, String hubURL) {

        URL gridUrl = startStandaloneGrid(hubURL);

        switch (browser) {
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.setEnableDownloads(true);
                return new RemoteWebDriver(gridUrl, chromeOptions);
            case "Firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.setEnableDownloads(true);
                return new RemoteWebDriver(gridUrl, firefoxOptions);
            default:
                throw new NotFoundException("Browser not found");
        }
    }
}
