package com.sathyakunda.qa.core.driver.selenium;

import com.sathyakunda.qa.core.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class SeleniumDriverFactory {

    private SeleniumDriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser = ConfigManager.get("browser")
                .trim()
                .toLowerCase();

        boolean headless = ConfigManager.getBoolean("headless");

        return switch (browser) {

            case "chrome" ->
                    createChromeDriver(headless);

            case "firefox" ->
                    createFirefoxDriver(headless);

            case "edge" ->
                    createEdgeDriver(headless);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported Selenium browser: " + browser);
        };
    }

    private static WebDriver createChromeDriver(boolean headless) {

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {

        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
        }

        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {

        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        return new EdgeDriver(options);
    }
}
