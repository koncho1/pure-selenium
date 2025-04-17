package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.LoginPage;
import com.gui.pages.desktop.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends AbstractTest{

    @Test(groups = "group13")
    public void LoginTest(){
        HomePage homePage =new HomePage(getDriver());
        LoginPage loginPage = homePage.getLoginPage();
        loginPage.enterLogin("abcdee");
        loginPage.enterPassword("Z6FBty@R2N4NWN2");
        loginPage.submitCredentials();
        Assert.assertTrue(loginPage.isUserOnMyAccountPage(),"User is not on My Account page");
    }

    @Test(groups = "group1")
    public void IncorrectLoginTest() throws InterruptedException {
        HomePage homePage =new HomePage(getDriver());
        LoginPage loginPage = homePage.getLoginPage();
        loginPage.enterLogin("abcdee");
        loginPage.submitCredentials();
        Assert.assertTrue(loginPage.isALoginErrorPresent(),"Login error is not present");
    }

    @Test(groups = "group13")
    public void RegisterTest(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.getLoginPage();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("exeample@mail.com");
        registerPage.enterAdress1("Some Street 5/43");
        registerPage.enterCity("Aberdeen");
        registerPage.chooseRegion();
        registerPage.enterZipCode("AB10");
        registerPage.enterLogin("Jonhjohne");
        registerPage.enterPassword("Z6FBty@R2N4NWN2");
        registerPage.enterConfirmPassword("Z6FBty@R2N4NWN2");
        registerPage.checkPrivacyPolicy();
        registerPage.submitRegistration();
        Assert.assertTrue(registerPage.isAccountCreatedTextPresent(),"Account has not been created");
    }
}
