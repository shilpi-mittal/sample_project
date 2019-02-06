package pageObjects;

import utils.TestConstants;
import utils.TestWebDriver;

public class Page {
    public TestWebDriver testWebDriver;
//    private static String baseUrl = "https://www.google.co.in";

    public Page(TestWebDriver testWebDriver) {
        this.testWebDriver = testWebDriver;
    }

    public HomePage accessHomePage() {
        testWebDriver.navigateTo(TestConstants.BASE_URL);
        return PageObjectFactory.getHomePage(testWebDriver);
    }

    public InsurancePolicyFormPage accessInsurancePolicyFormPage() {
        testWebDriver.navigateTo(TestConstants.BASE_URL);
        return PageObjectFactory.getInsurancePolicyFormPage(testWebDriver);
    }

    public String getUrl() {
        return testWebDriver.getCurrentUrl();
    }

    public String getBaseUrl() {
        return TestConstants.BASE_URL;
    }
}