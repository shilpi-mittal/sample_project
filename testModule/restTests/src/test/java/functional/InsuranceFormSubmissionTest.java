package functional;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RestUtility;

import static utils.TestWebDriver.loadPropertiesFile;

public class InsuranceFormSubmissionTest extends JsonUtility {
    @BeforeClass(groups = {"form", "rest"})
    public void setup() {
        loadPropertiesFile("testData.txt");
    }

    @Test(groups = {"form", "rest"})
    public void postTest() {
        Response response = RestUtility.postResponse(TestData.POST_URL, InsuranceFormJson.getInsuranceFormJson(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.GENDER,
                TestData.ADDRESS, TestData.DOB, TestData.DL_NUMBER, TestData.DL_EXP, TestData.EFFD_DATE));

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.asString());
        Assert.assertTrue(response.toString().contains("Success!"));
    }

}





