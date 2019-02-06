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
import pageObjects.InsurancePolicyFormPage;
import pageObjects.PageObjectFactory;
import utils.TestCaseHelper;
import utils.TestWebDriver;

@CucumberOptions(plugin = { "html:target/cucumber-html-report",
        "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
        "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" })
public class InsuranceFormSubmissionSteps extends TestCaseHelper {


    @Before
    public void setup() {
        super.setup();
    }

    private InsurancePolicyFormPage insurancePolicyFormPage;

    @Given("^I access auto insurance policy form page$")
    public void i_access_insurance_policy_page() {
        insurancePolicyFormPage = PageObjectFactory.getInsurancePolicyFormPage(testWebDriver);
        insurancePolicyFormPage.accessInsurancePolicyFormPage();
    }

    @And("^I enter \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and select gender as \"([^\"]*)\"$")
    public void i_enter_details(String first_name, String last_name, String address, String gender) {
        insurancePolicyFormPage.enterDetails(first_name, last_name, address, gender);
    }

    @And("^I select driving license number as \"([^\"]*)\" and expiry date as \"([^\"]*)\"$")
    public void i_enter_licence_details(String licenceNumber, String expDate) {
        insurancePolicyFormPage.enterLicenceDetails(licenceNumber, expDate);
    }

    @When("^I enter \"([^\"]*)\"$")
    public void i_enter_dob(String dob) {
        insurancePolicyFormPage.enterDob(dob);
    }

    @When("^I select effective date as \"([^\"]*)\"$")
    public void i_enter_effective_date(String dob) {
        insurancePolicyFormPage.enterPolicyEffectiveDate(dob);
    }

    @When("^I enter age as \"([^\"]*)\"$")
    public void i_enter_age(String age) {
        insurancePolicyFormPage.enterAge(age);
    }

    @And("^I select driving license status as \"([^\"]*)\"$")
    public void i_select_driving_licence_status(String status) {
        insurancePolicyFormPage.selectDrivingLicenceStatus(status);
    }

    @And("^I select training certification status as \"([^\"]*)\"$")
    public void i_select_training_certification_status(String status) {
        if(status.equals("valid")) {
            insurancePolicyFormPage.checkTCStatus();
        }
    }

    @And("^I select effective date as ([^\"]*) in \"(future|past)\"$")
    public void i_select_effective_date(int days, String futureOrPast) {
        insurancePolicyFormPage.selectEffectiveDate(days, futureOrPast);
    }

    @And("^I click on submit$")
    public void i_click_submit() {
        insurancePolicyFormPage.clickSubmit();
    }

    @And("^I should see a success message$")
    public void i_see_success_message() {
        Assert.assertTrue(insurancePolicyFormPage.isSuccessMessageDisplayed());
    }

    @And("^\"([^\"]*)\" should be highlighted in red$")
    public void should_be_highlighted_in_red(String field) {
        Assert.assertTrue(insurancePolicyFormPage.isFieldDisplayedInRed(field));
    }

    @And("^details as age \"([^\"]*)\", DLStatus as \"([^\"]*)\", TCStatus as \"([^\"]*)\" and effective date as \"([^\"]*)\" days in \"([^\"]*)\"$")
    public void should_verify_details(String age, String dLStatus, String tCStatus, int days, String futureOrPast) {
        Assert.assertEquals(insurancePolicyFormPage.getEnteredAge(),""+Integer.parseInt(age));
        Assert.assertEquals(insurancePolicyFormPage.getEnteredDLStatus(),dLStatus);
        Assert.assertEquals(insurancePolicyFormPage.getEnteredTCStatus(),tCStatus);
        Assert.assertEquals(insurancePolicyFormPage.getEnteredEffectiveDate(),insurancePolicyFormPage.getDate(days,futureOrPast));
    }

    @Then("^I should see a error message as \"([^\"]*)\"$")
    public void i_should_see_error_message(String errorMessage) {
        Assert.assertTrue(insurancePolicyFormPage.isErrorMessageDisplayed());
        Assert.assertEquals(insurancePolicyFormPage.getErrorMessage(),errorMessage);
    }

    @After
    public void embedScreenshot(Scenario scenario) {
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

}
