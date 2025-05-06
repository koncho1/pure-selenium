package com.gui;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result){
        AbstractTest.takeScreenshot(AbstractTest.getDriver());
    }
}
