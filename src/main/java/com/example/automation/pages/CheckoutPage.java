package com.example.automation.pages;

import com.example.automation.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public boolean isLoaded() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("checkout-step-one");
    }

    public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {

        WebDriver webDriver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));

        wait.until(ExpectedConditions.visibilityOf(firstNameInput));

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);

        continueButton.click();
    }
}
