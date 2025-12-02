package com.example.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = "chrome"; // simplify for now

        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }

        driver.set(webDriver);

        webDriver.manage().window().maximize();
        // IMPORTANT: give Selenium time to find elements
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}
