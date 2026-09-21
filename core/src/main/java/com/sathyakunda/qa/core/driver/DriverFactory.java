package com.sathyakunda.qa.core.driver;

import com.sathyakunda.qa.core.driver.selenium.SeleniumDriverFactory;
import org.openqa.selenium.WebDriver;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> WEB_DRIVER =
            new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initialize(DriverType driverType) {

        if (driverType == null) {
            throw new IllegalArgumentException(
                    "Driver type cannot be null");
        }

        switch (driverType) {
            case SELENIUM -> initializeSelenium();
            case PLAYWRIGHT -> initializePlaywright();
        }
    }

    private static void initializeSelenium() {

        if (WEB_DRIVER.get() != null) {
            return;
        }

        WebDriver driver =
                SeleniumDriverFactory.createDriver();

        WEB_DRIVER.set(driver);
    }

    private static void initializePlaywright() {

        throw new UnsupportedOperationException(
                "Playwright driver is not implemented yet.");
    }

    public static WebDriver getWebDriver() {

        WebDriver driver = WEB_DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized for the current thread.");
        }

        return driver;
    }

    public static void quit() {

        WebDriver driver = WEB_DRIVER.get();

        if (driver != null) {
            try {
                driver.quit();
            } finally {
                WEB_DRIVER.remove();
            }
        }
    }
}
