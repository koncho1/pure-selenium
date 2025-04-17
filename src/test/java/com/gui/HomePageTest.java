
package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageTest extends AbstractTest {

    @Test
    public void test(){
        HomePage homePage =new HomePage(getDriver());
        ProductsListPage productsListPage = homePage.searchKeyWord("shirt");
        productsListPage.printAllItems();
    }

    @Test(groups = "group1")
    public void testCurrencyChange() {
        HomePage homePage =new HomePage(getDriver());
        homePage.changeCurrency("£");
        Assert.assertTrue(homePage.isCurrencyInCartCorrect("£"), "Currency is incorrect");
    }

    @Test(groups = "group1")
    public void testCartItemCount(){
        HomePage homePage =new HomePage(getDriver());
        homePage.addItemsToCart(3);
        Assert.assertTrue(homePage.isCartItemCountCorrect("3"),"Item count is incorrect");
    }

    @Test(groups = "group1")
    public void testCartTotal(){
        HomePage homePage =new HomePage(getDriver());
        homePage.clickAddToCartButton();
        Assert.assertTrue(homePage.isCartTotalCorrect("$29.50"),"Cart total is incorrect.");
    }




}
