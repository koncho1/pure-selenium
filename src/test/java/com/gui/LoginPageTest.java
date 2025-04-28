package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.LoginPage;
import com.gui.pages.desktop.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends AbstractTest {

    @Test(groups = "group13")
    public void testLoginFunctionality() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.openLoginPage();
        loginPage.enterLogin("abcdee");
        loginPage.enterPassword("Z6FBty@R2N4NWN2");
        loginPage.submitCredentials();
        Assert.assertTrue(loginPage.isUserOnMyAccountPage(), "User is not on My Account page");
    }

    @Test(groups = "group1")
    public void testIncorrectLogin() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.openLoginPage();
        loginPage.enterLogin("abcdee");
        loginPage.submitCredentials();
        Assert.assertTrue(loginPage.isALoginErrorPresent(), "Login error is not present");
    }

    @Test(groups = "group13")
    public void testUserRegistration() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.openLoginPage();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.fillOutRegistrationForm(
                "John",
                "Doe",
                "exeample@mail.com",
                "Some Street 5/43",
                "Aberdeen",
                "AB10",
                "Jonhjohne",
                "Z6FBty@R2N4NWN2");
        Assert.assertTrue(registerPage.isAccountCreatedTextPresent(), "Account has not been created");
    }
}
