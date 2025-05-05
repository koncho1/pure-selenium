
package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HomePageTest extends AbstractTest {


    @Test(groups = "group1", dataProvider = "currencyData")
    public void testCurrencyChange(String selectedCurrencySymbol, String expectedCurrencySymbol) {
        HomePage homePage = new HomePage(getDriver());
        homePage.changeCurrency(selectedCurrencySymbol);
        Assert.assertTrue(homePage.isCurrencyInCartCorrect(expectedCurrencySymbol), String.format("Currency in cart is incorrect. Expected: %s", expectedCurrencySymbol));
    }

    @Test(groups = "group13", dataProvider = "itemCountData")
    public void testCartItemCount(int itemsAddedToCart, String expectedNumberOfItemsInCart) {
        HomePage homePage = new HomePage(getDriver());
        homePage.addItemsToCart(itemsAddedToCart);
        Assert.assertTrue(homePage.isCartItemCountCorrect(expectedNumberOfItemsInCart), String.format("Item count is incorrect. Expected: %s", expectedNumberOfItemsInCart));
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
