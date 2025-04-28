package com.gui.pages.desktop;

import com.gui.pages.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterPage extends AbstractPage {

    @FindBy(id = "AccountFrm_firstname")
    private WebElement firstNameField;

    @FindBy(id = "AccountFrm_lastname")
    private WebElement lastNameField;

    @FindBy(id = "AccountFrm_email")
    private WebElement emailField;

    @FindBy(id = "AccountFrm_address_1")
    private WebElement adress1Field;

    @FindBy(id = "AccountFrm_city")
    private WebElement cityField;

    @FindBy(id = "AccountFrm_zone_id")
    private WebElement regionSelector;

    @FindBy(xpath = "//select[contains(@id, 'AccountFrm_zone_id')]//option")
    private List<WebElement> regionOption;

    @FindBy(id = "AccountFrm_postcode")
    private WebElement zipCodeField;

    @FindBy(id = "AccountFrm_country_id")
    private WebElement countrySelector;

    @FindBy(id = "AccountFrm_loginname")
    private WebElement loginNameField;

    @FindBy(id = "AccountFrm_password")
    private WebElement passwordField;

    @FindBy(id = "AccountFrm_confirm")
    private WebElement passwordConfirmField;

    @FindBy(id = "AccountFrm_agree")
    private WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//button[contains(@title, 'Continue')]")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@class='login-form']//b[text()='%s']")
    private WebElement accountCreatedText;

    public void fillOutRegistrationForm(String firstname, String lastName, String email, String adress, String city, String zipCode, String login, String password) {
        enterFirstName(firstname);
        enterLastName(lastName);
        enterEmail(email);
        enterAdress1(adress);
        enterCity(city);
        chooseRegion();
        enterZipCode(zipCode);
        enterLogin(login);
        enterPassword(password);
        enterConfirmPassword(password);
        checkPrivacyPolicy();
        submitRegistration();
    }

    private void enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    private void enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    private void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    private void enterAdress1(String adress) {
        sendKeys(adress1Field, adress);
    }

    private void enterCity(String city) {
        sendKeys(cityField, city);
    }

    private void chooseRegion() {
        clickElement(regionSelector);
        clickElement(regionOption.get(2));
    }

    private void enterZipCode(String zipCode) {
        sendKeys(zipCodeField, zipCode);
    }

    private void enterLogin(String login) {
        sendKeys(loginNameField, login);
    }

    private void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    private void enterConfirmPassword(String confirmPassword) {
        sendKeys(passwordConfirmField, confirmPassword);
    }

    private void checkPrivacyPolicy() {
        clickElement(privacyPolicyCheckbox);
    }

    private void submitRegistration() {
        clickElement(registerButton);
    }

    public boolean isAccountCreatedTextPresent() {
        return accountCreatedText.isEnabled();
    }

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

}
