
package com.gui.pages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Objects;

import com.gui.pages.common.AbstractPage;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends AbstractPage {

    private static final String POUND_SYMBOL = "£";

    private static final String EURO_SYMBOL = "€";

    private static final String DOLLAR_SYMBOL = "$";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id = "filter_keyword")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@class, 'button-in-search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[contains(@class, 'dropdown-menu currency')]")
    private List<WebElement> currencyList;

    @FindBy(xpath = "//span[contains(@class, 'cart_total')]")
    private WebElement cartTotalText;

    @FindBy(xpath = "//a[contains(@class, 'dropdown-toggle')]")
    private WebElement currencySelector;

    @FindBy(xpath = "//a[contains(text(), 'Login or register')]")
    private WebElement loginButton;

    @FindBy(xpath = "//i[contains(@class, 'fa-cart-plus')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[contains(@class, 'block_7')]//span[contains(@class , 'label-orange')]")
    private WebElement cartItemCountLabel;

    public boolean isCartTotalCorrect(String correctCartTotal) {
        return Objects.equals(cartTotalText.getText(), correctCartTotal);
    }

    public void clickCurrencySelector() {
        clickElement(currencySelector);
    }

    public void changeCurrency(String currency) {
        clickCurrencySelector();

        switch (currency) {
            case POUND_SYMBOL:
                clickElement(currencyList.get(0));
                break;
            case EURO_SYMBOL:
                clickElement(currencyList.get(1));
                break;
            case DOLLAR_SYMBOL:
                clickElement(currencyList.get(2));
                break;
            default:
                throw new NotFoundException("Unsupported currency");
        }
    }

    public boolean isCartItemCountCorrect(String correctItemCount) {
        return Objects.equals(cartItemCountLabel.getText(), correctItemCount);
    }

    public void clickAddToCartButton() {
        clickElement(addToCartButton);
    }

    public void addItemsToCart(int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            clickAddToCartButton();
        }
    }

    public boolean isCurrencyInCartCorrect(String currencySign) {
        return cartTotalText.getText().contains(currencySign);
    }

    public ProductsListPage searchKeyWord(String keyword) {
        clickElement(searchField);
        sendKeys(searchField, keyword);
        clickElement(searchButton);
        return new ProductsListPage(driver);
    }

    public LoginPage openLoginPage() {
        clickElement(loginButton);
        return new LoginPage(driver);
    }

}