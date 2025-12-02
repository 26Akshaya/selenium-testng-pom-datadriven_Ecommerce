package com.example.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement titleLabel;
    
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCart;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    public void addBackpackToCart() {
        addBackpackToCart.click();
    }

    public void clickCartButton() {
        cartButton.click();
    }


    public boolean isLoaded() {
        try {
            return titleLabel.isDisplayed() &&
                   titleLabel.getText().equalsIgnoreCase("Products");
        } catch (Exception e) {
            return false;
        }
    }
}
