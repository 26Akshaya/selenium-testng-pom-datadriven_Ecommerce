package com.example.automation.tests;

import com.example.automation.core.ConfigReader;
import com.example.automation.core.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        String baseUrl = ConfigReader.get("baseUrl");
        DriverFactory.getDriver().get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
