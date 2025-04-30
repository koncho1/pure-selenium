package com.gui.pages.desktop.components;

import com.gui.pages.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchItem extends AbstractPage {

    @FindBy(xpath = "//a[contains(@class, 'prdocutname')]")
    private WebElement productNameLabel;

    public String getText() {
        return productNameLabel.getText();
    }

    public SearchItem(WebDriver driver) {
        super(driver);
    }

}
