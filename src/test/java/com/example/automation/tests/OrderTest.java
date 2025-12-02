package com.example.automation.tests;

import com.example.automation.core.ConfigReader;
import com.example.automation.pages.CartPage;
import com.example.automation.pages.CheckoutCompletePage;
import com.example.automation.pages.CheckoutOverviewPage;
import com.example.automation.pages.CheckoutPage;
import com.example.automation.pages.LoginPage;
import com.example.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {

    @Test
    public void testOrderBookingFlow() {

        // Login
        LoginPage loginPage = new LoginPage();
        loginPage.open(ConfigReader.get("baseUrl"));
        loginPage.login("standard_user", "secret_sauce");

        // Products page
        ProductsPage productsPage = new ProductsPage();
        Assert.assertTrue(productsPage.isLoaded(), "Products page failed to load");

        // Add product to cart and go to cart
        productsPage.addBackpackToCart();
        productsPage.clickCartButton();

        // Cart page -> Checkout
        CartPage cartPage = new CartPage();
        cartPage.clickCheckout();

        // Checkout info page -> fill details and continue
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");

        // Overview page -> wait for it, then finish
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        overviewPage.waitForOverviewPage();   // ✅ just wait, no assert
        overviewPage.clickFinish();

        // Complete page -> verify order success
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        Assert.assertTrue(completePage.isOrderSuccessful(), "Order was not successful");

        String successMessage = completePage.getSuccessMessage();
        System.out.println("Order success message: " + successMessage);
        Assert.assertEquals(successMessage, "Thank you for your order!");
    }
}
