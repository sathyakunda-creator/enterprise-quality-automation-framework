package com.sathyakunda.qa.web.tests;

import com.sathyakunda.qa.web.pages.ExamplePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ExamplePageTest extends BaseTest {

    @Test
    public void verifyExamplePage() {

        ExamplePage examplePage =
                new ExamplePage(getDriver());

        Assertions.assertThat(examplePage.getPageTitle())
                .isNotBlank();

        Assertions.assertThat(examplePage.getCurrentUrl())
                .contains("example.com");
    }
}
