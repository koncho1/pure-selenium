package com.gui.pages.desktop;

import com.gui.pages.common.AbstractPage;
import com.gui.pages.desktop.components.SearchItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class ProductsListPage extends AbstractPage {
    protected WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'thumbnails grid row list-inline')]")
    private List<SearchItem> itemContainerList;

    @FindBy(xpath = "//div[contains(@class, 'input-group')]//input")
    private WebElement keywordField;

    @FindBy(xpath = "//div[contains(text(), 'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;


    public boolean isSearchTextCorrect(String correctText) {
        String keywordText = keywordField.getAttribute("value");
        return Objects.equals(keywordText, correctText);
    }

    public boolean isNoProductMessagePresent() {
        return noProductMessage.isDisplayed();
    }

    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    public void printAllItems() {
        for (SearchItem item : itemContainerList) {
            logger.info(item.getText());
        }
    }

    public boolean areItemsFound() {
        return !itemContainerList.isEmpty();
    }
}
