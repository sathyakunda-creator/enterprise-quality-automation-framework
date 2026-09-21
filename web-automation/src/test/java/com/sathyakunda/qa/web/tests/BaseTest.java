package com.sathyakunda.qa.web.tests;

import com.sathyakunda.qa.core.config.ConfigManager;
import com.sathyakunda.qa.core.driver.DriverFactory;
import com.sathyakunda.qa.core.driver.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initialize(DriverType.SELENIUM);
        getDriver().get(ConfigManager.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quit();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getWebDriver();
    }
}
