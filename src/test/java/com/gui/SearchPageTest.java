package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchPageTest extends AbstractTest {

    @Test(groups = "group2")
    public void testSearchShirt(){
        HomePage homePage =new HomePage(getDriver());
        ProductsListPage productsListPage = homePage.searchKeyWord("Shirt");
        Assert.assertTrue(productsListPage.isSearchTextCorrect("Shirt"));
    }

    @Test(groups = "group2")
    public void testUnsuccessfulSearch(){
        HomePage homePage =new HomePage(getDriver());
        ProductsListPage productsListPage = homePage.searchKeyWord("Apple");
        Assert.assertTrue(productsListPage.isNoProductMessagePresent());
    }
}
