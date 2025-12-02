package com.example.automation.pages;

import com.example.automation.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    // Wait until we are on the overview page (step two)
    public void waitForOverviewPage() {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void clickFinish() {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until Finish button is clickable, then click
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }
}
