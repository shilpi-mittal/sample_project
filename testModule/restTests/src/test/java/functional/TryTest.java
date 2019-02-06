package functional;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RestUtility;

import static utils.TestWebDriver.loadPropertiesFile;

public class TryTest extends JsonUtility {
    @BeforeClass
    public void setup() {
        loadPropertiesFile("testData.txt");
    }

    @Test
    public void postTest() {
        Response response = RestUtility.postResponse(TestData.URL1, TryTestJson.getTryTestJson(TestData.SALARY));

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.asString());
        Assert.assertTrue(response.toString().contains("<title>"+TestData.QUERY_PARAM1+" - Google Search</title>"));
    }

    @Test
    public void getTest() {
        Response response = RestUtility.getResponse(TestData.URL1);
        System.out.println(response.asString());
    }

    @Test
    public void getTest1() {
        Response response = RestUtility.getResponse(TestData.URL1, 10526);
        System.out.println(response.asString());
    }

}





