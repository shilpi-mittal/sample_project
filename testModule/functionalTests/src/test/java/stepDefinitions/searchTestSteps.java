package stepDefinitions;


import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.PageObjectFactory;
import utils.TestCaseHelper;
import utils.TestWebDriver;

//@RunWith(ExtendedCucumber.class)
//@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
//        overviewReport = true,
//        outputFolder = "target")
@CucumberOptions(plugin = { "html:target/cucumber-html-report",
        "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
        "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" })
public class searchTestSteps extends TestCaseHelper {

    @Before
    public void setup() {
        super.setup();
    }

    @Given("^I access home page$")
    public void i_access_home_page() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.accessHomePage();
    }

    @When("^I enter search parameter \"([^\"]*)\"$")
    public void I_enter_search_parameter(String parameter) {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.enterSearchParameter(parameter);
    }

    @And("^I hit enter$")
    public void I_hit_enter() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.hitEnter();
    }

    @Then("^I verify I am taken to new page$")
    public void I_verify_I_am_taken_to_new_page() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        Assert.assertNotEquals(homePage.getBaseUrl(), homePage.getUrl());
    }

    @And("^I verify search results for \"([^\"]*)\" are displayed$")
    public void I_verify_search_results_are_displayed(String output) {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        Assert.assertTrue(homePage.isTextDisplayed(output));
    }

    @And("^I verify search results are displayed$")
    public void I_verify_search_results_are_displayed() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        Assert.assertTrue(!homePage.isTextDisplayed("hello"));
    }

    @Then("^I verify logo is displayed$")
    public void I_verify_logo_is_displayed() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        Assert.assertTrue(homePage.isLogoDisplayed());
    }

    @After
    public void embedScreenshot(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) TestWebDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();}
        }
    }

//    public void after_hook() throws Exception {
//        CucumberResultsOverview results = new CucumberResultsOverview();
//        results.setOutputDirectory("target");
//        results.setOutputName("cucumber-results");
//        results.setSourceFile("../../../../build/reports/tests/cucumber/file.json");
//        results.executeFeaturesOverviewReport();

//        CucumberResultsOverview results = new CucumberResultsOverview();
//        results.setOutputDirectory("target");
//        results.setOutputName("cucumber-results");
//        results.setSourceFile("./src/test/resources/cucumber.json");
//        results.executeFeaturesOverviewReport();

//        CucumberUsageReporting report = new CucumberUsageReporting();
//        report.setOutputDirectory("target");
//        report.setJsonUsageFile("./src/test/resources/cucumber-usage.json");
//        report.executeReport();
//    }
}
