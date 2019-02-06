package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.TestWebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.openqa.selenium.support.How.*;

public class InsurancePolicyFormPage extends Page{

    @FindBy(how = ID, using = "")
    private static WebElement age = null;

    @FindBy(how = CSS, using = "input[name=Firstname]")
    private static WebElement firstNameField = null;

    @FindBy(how = CSS, using = "input[name=Lastname]")
    private static WebElement lastNameField = null;

    @FindBy(how = CSS, using = "input[name=Address]")
    private static WebElement addressField = null;

    @FindBy(how = CSS, using = "input[name=male]")
    private static WebElement maleRadioButton = null;

    @FindBy(how = CSS, using = "input[value=female]")
    private static WebElement femaleRadioButton = null;

    @FindBy(how = CSS, using = "input[name=Birthdate]")
    private static WebElement dob = null;

    @FindBy(how = CSS, using = "input[name=dlNumber]")
    private static WebElement dLNumber = null;

    @FindBy(how = CSS, using = "input[name=dlExpiration]")
    private static WebElement dLExpDate = null;

    @FindBy(how = ID, using = "")
    private static WebElement DLValidRadioButton = null;

    @FindBy(how = CSS, using = "input[name=trainingCourse]")
    private static WebElement TCValidRadioButton = null;

    @FindBy(how = ID, using = "")
    private static WebElement DLStatus = null;

    @FindBy(how = ID, using = "")
    private static WebElement TCStatus = null;

    @FindBy(how = CSS, using = "input[name=policyEffectiveDate]")
    private static WebElement effectiveDate = null;

    @FindBy(how = CSS, using = "input[type=submit]")
    private static WebElement submitButton = null;

    @FindBy(how = XPATH, using = "//h1[contains(text(), 'Success!')]")
    private static WebElement successMessage = null;

    @FindBy(how = CLASS_NAME, using = "alert-danger")
    private static WebElement errorMessage = null;

    public InsurancePolicyFormPage(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(TestWebDriver.getDriver(), 1), this);
    }

    public void enterDetails(String firstName, String lastName, String address, String gender) {
        testWebDriver.enterInput(firstNameField, firstName);
        testWebDriver.enterInput(lastNameField, lastName);
        testWebDriver.enterInput(addressField, address);
        if(gender.equals("female")) {
            testWebDriver.clickOnElement(femaleRadioButton);
        }
    }

    public void enterDob(String input) {
        testWebDriver.enterInput(dob, input);
    }

    public void enterPolicyEffectiveDate(String input) {
        testWebDriver.enterInput(effectiveDate, input);
    }

    public void enterLicenceDetails(String dlNum, String date) {
        testWebDriver.enterInput(dLNumber, dlNum);
        testWebDriver.enterInput(dLExpDate, date);
    }

    public void enterAge(String input) {
        testWebDriver.enterInput(age, input);
//        testWebDriver.enterInput(age, Keys.TAB);
    }

    public void selectDrivingLicenceStatus(String status) {
        if(status.equals("valid")) {
            testWebDriver.clickOnElement(DLValidRadioButton);
        }
    }

    public void checkTCStatus() {
            testWebDriver.clickOnElement(TCValidRadioButton);
    }

    public void selectEffectiveDate(int days, String futureOrPast) {
        String calculatedEffectiveDate = getDate(days, futureOrPast);
        testWebDriver.enterInput(effectiveDate,calculatedEffectiveDate);
     }

    public String getDate(int days, String futureOrPast) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 5); // Adding 5 days
        if(futureOrPast.equals("future")) {
            c.add(Calendar.DATE, days); // Adding 5 days
        }
        else {
            if(futureOrPast.equals("past")) {
                days=-days;
                c.add(Calendar.DATE, days); // Adding 5 days
            }
        }
        return sdf.format(c.getTime());
    }

    public void clickSubmit() {
        testWebDriver.clickOnElement(submitButton);
    }

    public Boolean isSuccessMessageDisplayed() {
        return testWebDriver.isDisplayed(successMessage);
    }

    public Boolean isFieldDisplayedInRed(String field) {
        switch(field) {
            case "age" :
                return testWebDriver.getAttribute(age,"class").contains("error");
            case "DLStatus" :
                return testWebDriver.getAttribute(DLStatus,"class").contains("error");
            case "TCStatus" :
                return testWebDriver.getAttribute(TCStatus,"class").contains("error");
            case "effectiveDate" :
                return testWebDriver.getAttribute(effectiveDate,"class").contains("error");
            default :
                return false;
        }
    }

    public Boolean isErrorMessageDisplayed() {
        return testWebDriver.isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        String text = testWebDriver.getText(errorMessage).trim().replaceAll("\\n+"," ");
        return text;
    }

    public String getEnteredAge() {
        return testWebDriver.getText(age);
    }

    public String getEnteredDLStatus() {
        if(testWebDriver.isSelected(DLValidRadioButton)) {
            return "valid";
        }
        else
            return "invalid";
    }

    public String getEnteredTCStatus() {
        if(!testWebDriver.isDisplayed(TCValidRadioButton)) {
            return "NA";
        }
        else {
            if(testWebDriver.isSelected(DLValidRadioButton)) {
                return "valid";
            }
            else
                return "invalid";
        }
    }

    public String getEnteredEffectiveDate() {
        return testWebDriver.getText(effectiveDate);
    }
}
