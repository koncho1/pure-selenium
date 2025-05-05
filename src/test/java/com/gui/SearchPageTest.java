package com.gui;

import com.gui.pages.desktop.HomePage;
import com.gui.pages.desktop.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchPageTest extends AbstractTest {

    @Test(groups = "group2")
    public void testSearchShirt() {
        String shirtSearch = "Shirt";
        HomePage homePage = new HomePage(getDriver());
        ProductsListPage productsListPage = homePage.searchKeyWord(shirtSearch);
        Assert.assertTrue(productsListPage.isSearchTextCorrect(shirtSearch), "Search text is not correct");
    }

    @Test(groups = "group2")
    public void testUnsuccessfulSearch() {
        String appleSearch = "Apple";
        HomePage homePage = new HomePage(getDriver());
        ProductsListPage productsListPage = homePage.searchKeyWord(appleSearch);
        Assert.assertTrue(productsListPage.isNoProductMessagePresent(), "No product found message is not present");
    }
}
