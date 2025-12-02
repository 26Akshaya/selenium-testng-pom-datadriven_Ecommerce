package com.example.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(css = ".complete-header")
    private WebElement orderSuccessMessage;

    public String getSuccessMessage() {
        return orderSuccessMessage.getText();
    }

    public boolean isOrderSuccessful() {
        try {
            return orderSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
