package com.example.automation.tests;

import com.example.automation.core.CSVUtils;
import com.example.automation.core.ConfigReader;
import com.example.automation.pages.LoginPage;
import com.example.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        // reads from src/test/resources/testdata/login-data.csv
        return CSVUtils.readCsvData("testdata/login-data.csv");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        LoginPage loginPage = new LoginPage();
        loginPage.open(ConfigReader.get("baseUrl"));
        loginPage.login(username, password);

        ProductsPage productsPage = new ProductsPage();

        if ("success".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(
                    productsPage.isLoaded(),
                    "User should be navigated to Products page for valid credentials"
            );
            Assert.assertFalse(
                    loginPage.isErrorDisplayed(),
                    "Error message should not be displayed for valid credentials"
            );
        } else if ("error".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "Error message should be displayed for invalid credentials"
            );
            Assert.assertFalse(
                productsPage.isLoaded(),
                "Products page should not be loaded for invalid credentials"
            );
        } else {
            Assert.fail("Unexpected expectedResult value in CSV: " + expectedResult);
        }
    }
}
