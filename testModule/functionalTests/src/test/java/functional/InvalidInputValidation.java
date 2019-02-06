package functional;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.InsurancePolicyFormPage;
import pageObjects.PageObjectFactory;
import utils.TestCaseHelper;

public class InvalidInputValidation extends TestCaseHelper {

    @BeforeClass(groups = {"form", "ui"})
    public void setup() {
        super.setup();
    }

    @Test(groups = {"form", "ui"})
    public void invalidDateInput() {
        InsurancePolicyFormPage insurancePolicyFormPage = PageObjectFactory.getInsurancePolicyFormPage(testWebDriver);
        insurancePolicyFormPage.accessHomePage();
        insurancePolicyFormPage.enterAge("1");
        Assert.assertEquals(insurancePolicyFormPage.getErrorMessage(),"");
    }

    @AfterClass(groups = {"form", "ui"})
    public void tearDown() {
        tearDownSuite();
        PageObjectFactory.clearAllPageObjects();
    }
}
