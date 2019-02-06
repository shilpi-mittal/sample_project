package functional;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.PageObjectFactory;
import utils.TestCaseHelper;

import static utils.TestWebDriver.loadPropertiesFile;

//@Listeners(TakeScreenshot.class)

public class BasicSearch extends TestCaseHelper {

    @BeforeClass(groups = {"testNG"})
    public void setup() {
        super.setup();
    }

    HomePage homePage;

    @Test(groups = {"testNG"})
    public void verifyBasicSearch() {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.accessHomePage();
        Assert.assertTrue(homePage.isLogoDisplayed());
        loadPropertiesFile("testData.txt");
        homePage.enterSearchParameter(TestData.SEARCH_PARAM);
        homePage.hitEnter();
        Assert.assertNotEquals(homePage.getBaseUrl(), homePage.getUrl());
        Assert.assertTrue(homePage.isTextDisplayed(TestData.SEARCH_PARAM));
    }

    @AfterClass(groups = {"testNG"})
    public void tearDown() {
        tearDownSuite();
        PageObjectFactory.clearAllPageObjects();
    }
}