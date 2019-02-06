package functional;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ResponseEntity;

import java.io.IOException;

import static utils.TestWebDriver.loadPropertiesFile;

public class searchApiTest extends JsonUtility {
    @BeforeClass
    public void setup() {
        loadPropertiesFile("testData.txt");
    }

    @Test
    public void testSearchGetAPIResponse() throws IOException {
        ResponseEntity responseEntity = getResponse(urlBuilder(TestData.URL1, TestData.QUERY_PARAM1), true);
        Assert.assertEquals(responseEntity.getStatus(), 200);
        System.out.println(responseEntity.getResponse());
        Assert.assertTrue(responseEntity.getResponse().contains("<title>"+TestData.QUERY_PARAM1+" - Google Search</title>"));
    }
}
