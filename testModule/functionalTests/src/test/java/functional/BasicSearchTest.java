package functional;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.PageObjectFactory;
import utils.TestCaseHelper;

//@Listeners(TakeScreenshot.class)

public class BasicSearchTest extends TestCaseHelper {

    @BeforeClass(groups = {"testNG"})
    public void setup() {
        super.setup();
    }

    @Test (groups = {"testNG"})
    public void verifyBasicSearch() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.accessHomePage();
        Assert.assertTrue(homePage.isLogoDisplayed());
        homePage.enterSearchParameter("hello");
        homePage.hitEnter();
        Assert.assertNotEquals(homePage.getBaseUrl(), homePage.getUrl());
        Assert.assertTrue(homePage.isTextDisplayed("hello"));
    }

    @AfterClass(groups = {"testNG"})
    public void tearDown() {
        tearDownSuite();
        PageObjectFactory.clearAllPageObjects();
    }
}