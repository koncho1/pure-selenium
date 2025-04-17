package com.gui.pages.desktop;

import com.gui.pages.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterPage extends AbstractPage {

    @FindBy (xpath = "//input[contains(@id, 'AccountFrm_firstname')]")
    private WebElement firstNameField;

    @FindBy (id = "AccountFrm_lastname")
    private WebElement lastNameField;

    @FindBy (id ="AccountFrm_email")
    private WebElement emailField;

    @FindBy (id ="AccountFrm_address_1")
    private WebElement adress1Field;

    @FindBy (id ="AccountFrm_city")
    private WebElement cityField;

    @FindBy (id ="AccountFrm_zone_id")
    private WebElement regionSelector;

    @FindBy (xpath = "//select[contains(@id, 'AccountFrm_zone_id')]//option")
    private List<WebElement> regionOption;

    @FindBy (id ="AccountFrm_postcode")
    private WebElement zipCodeField;

    @FindBy (id ="AccountFrm_country_id")
    private WebElement countrySelector;

    @FindBy (id ="AccountFrm_loginname")
    private WebElement loginNameField;

    @FindBy (id ="AccountFrm_password")
    private WebElement passwordField;

    @FindBy (id ="AccountFrm_confirm")
    private WebElement passwordConfirmField;

    @FindBy (id ="AccountFrm_agree")
    private WebElement privacyPolicyCheckbox;

    @FindBy (xpath = "//button[contains(@title, 'Continue')]")
    private WebElement registerButton;

    @FindBy (xpath = "//span[contains(text(), ' Your Account Has Been Created!')]")
    private WebElement accountCreatedText;

    public void enterFirstName(String firstName){
        super.sendKeysElement(firstNameField,firstName, "firstNameField");
    }

    public void enterLastName(String lastName){
        super.sendKeysElement(lastNameField,lastName, "lastNameField");
    }

    public void enterEmail(String email){
        super.sendKeysElement(emailField,email, "emailField");
    }

    public void enterAdress1(String adress){
        super.sendKeysElement(adress1Field,adress, "adress1Field");
    }

    public void enterCity(String city){
        super.sendKeysElement(cityField,city, "cityField");
    }

    public void chooseRegion(){
        super.clickElement(regionSelector, "regionSelector");
        super.clickElement(regionOption.get(2),"regionOption - Aberdeen");
    }

    public void enterZipCode(String zipCode){
        super.sendKeysElement(zipCodeField,zipCode, "zipCodeField");
    }

    public void enterLogin(String login){
        super.sendKeysElement(loginNameField,login, "loginNameField");
    }

    public void enterPassword(String password){
        super.sendKeysElement(passwordField,password, "passwordField");
    }

    public void enterConfirmPassword(String confirmPassword){
        super.sendKeysElement(passwordConfirmField,confirmPassword, "passwordConfirmField");
    }

    public void checkPrivacyPolicy(){
        super.clickElement(privacyPolicyCheckbox, "privacyPolicyCheckbox");
    }

    public void submitRegistration(){
        super.clickElement(registerButton, "registerButton");
    }

    public boolean isAccountCreatedTextPresent(){
        return accountCreatedText.isEnabled();
    }

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

}
