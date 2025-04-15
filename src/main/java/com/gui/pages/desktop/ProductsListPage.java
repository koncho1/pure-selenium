package com.gui.pages.desktop;

import com.gui.pages.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class ProductsListPage extends AbstractPage {
    protected WebDriver driver;

    @FindBy (xpath = "//div[contains(@class, 'thumbnails grid row list-inline')]//a[contains(@class, 'prdocutname')]")
    private List<WebElement> itemContainerList;

    @FindBy (xpath = "//div[contains(@class, 'input-group')]//input")
    private WebElement keywordField;

    @FindBy (xpath = "//div[contains(text(), 'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;


    public boolean isSearchTextCorrect(String correctText){
        String keywordText=keywordField.getAttribute("value");
        return Objects.equals(keywordText,correctText);
    }

    public boolean isNoProductMessagePresent(){
        return !(noProductMessage==null);
    }

    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    public void printAllItems(){
        for (WebElement item : itemContainerList){
            System.out.println(item.getText());
        }
    }
}
