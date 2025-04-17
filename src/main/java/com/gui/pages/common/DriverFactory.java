package com.gui.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    protected URL startStandaloneGrid() {
        int port = PortProber.findFreePort();
        try {
            Main.main(
                    new String[] {
                            "standalone",
                            "--port",
                            String.valueOf(port),
                            "--selenium-manager",
                            "true",
                            "--enable-managed-downloads",
                            "true",
                            "--log-level",
                            "WARNING"
                    });
            return new URL("http://localhost:" + port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebDriver createDriver(String browser){



        WebDriver driver=null;
        URL gridUrl=startStandaloneGrid();

        switch (browser) {
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.setEnableDownloads(true);
                driver = new RemoteWebDriver(gridUrl, chromeOptions);
                break;
            case "Firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.setEnableDownloads(true);
                driver = new RemoteWebDriver(gridUrl, firefoxOptions);
                break;
        }
        return driver;
    }
}
