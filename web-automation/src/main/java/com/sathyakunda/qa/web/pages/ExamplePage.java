package com.sathyakunda.qa.web.pages;

import org.openqa.selenium.WebDriver;

public class ExamplePage {

    private final WebDriver driver;

    public ExamplePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
