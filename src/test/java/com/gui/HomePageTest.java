
package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HomePageTest extends AbstractTest {


    @Test(groups = "group1", dataProvider = "currencyData")
    public void testCurrencyChange(String enteredCurrency, String expectedCurrency) {
        HomePage homePage = new HomePage(getDriver());
        homePage.changeCurrency(enteredCurrency);
        Assert.assertTrue(homePage.isCurrencyInCartCorrect(expectedCurrency), "Currency in cart doesn't match the");
    }

    @Test(groups = "group13", dataProvider = "itemCountData")
    public void testCartItemCount(int itemsAddedToCart, String expectedNumberOfItemsInCart) {
        HomePage homePage = new HomePage(getDriver());
        homePage.addItemsToCart(itemsAddedToCart);
        Assert.assertTrue(homePage.isCartItemCountCorrect(expectedNumberOfItemsInCart), "Item count is incorrect");
    }

    @Test(groups = "group13")
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
                {3, "3"},
                {2, "2"},
                {5, "5"}
        };

    }

}
