package com.gui.pages.desktop;

import com.gui.pages.common.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    private static final int WAIT_TIME = 3;

    @FindBy(xpath = "//div[contains(@class, 'alert alert-error alert-danger')]")
    private WebElement loginErrorDiv;

    @FindBy(id = "loginFrm_loginname")
    private WebElement loginField;

    @FindBy(id = "loginFrm_password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@title, 'Login')]")
    private WebElement loginButton;

    @FindBy(xpath = "//button[contains(@title, 'Continue')]")
    private WebElement registerButton;

    @FindBy(xpath = "//span[contains(@class, 'maintext')]")
    private WebElement myAccountText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void enterLogin(String login) {
        sendKeys(loginField, login);
    }

    public void submitCredentials() {
        clickElement(loginButton);
    }

    public boolean isUserOnMyAccountPage() {
        return myAccountText.isEnabled();
    }

    public boolean isALoginErrorPresent() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
            wait.until(ExpectedConditions.visibilityOf(loginErrorDiv));
            return loginErrorDiv.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Login error not visible");
            return false;
        }

    }

    public RegisterPage clickRegisterButton() {
        clickElement(registerButton);
        return new RegisterPage(driver);
    }

}
