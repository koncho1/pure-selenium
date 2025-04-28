
package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HomePageTest extends AbstractTest {


    @Test(groups = "group1", dataProvider = "currencyData")
    public void testCurrencyChange() {
        HomePage homePage = new HomePage(getDriver());
        homePage.changeCurrency("£");
        Assert.assertTrue(homePage.isCurrencyInCartCorrect("£"), "Currency in cart doesn't match the");
    }

    @Test(groups = "group1", dataProvider = "itemCountData")
    public void testCartItemCount() {
        HomePage homePage = new HomePage(getDriver());
        homePage.addItemsToCart(3);
        Assert.assertTrue(homePage.isCartItemCountCorrect("3"), "Item count is incorrect");
    }

    @Test(groups = "group1")
    public void testCartTotal() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickAddToCartButton();
        Assert.assertTrue(homePage.isCartTotalCorrect("$29.50"), "Cart total is incorrect.");
    }


    @DataProvider(name = "currencyData")
    public Object[][] currencyDataProvider() {
        return new Object[][]{
                {"£", "£"},
                {"$", "$"},
                {"€", "€"}
        };
    }

    @DataProvider(name = "itemCountData")
    public Object[][] itemCountDataProvider() {
        return new Object[][]{
                {"3", "3"},
                {"2", "2"},
                {"5", "5"}
        };

    }

}
