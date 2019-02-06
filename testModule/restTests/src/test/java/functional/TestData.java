package functional;

import utils.TestWebDriver;

public class TestData {
    public static final String URL1 = TestWebDriver.getProperty("URL1");
    public static final String QUERY_PARAM1 = TestWebDriver.getProperty("QUERY_PARAM1");
    public static final int SALARY = Integer.parseInt(TestWebDriver.getProperty("SALARY"));
    public static final String POST_URL = TestWebDriver.getProperty("POST_URL");
    public static final String FIRST_NAME = TestWebDriver.getProperty("FIRST_NAME");
    public static final String LAST_NAME = TestWebDriver.getProperty("LAST_NAME");
    public static final String ADDRESS = TestWebDriver.getProperty("ADDRESS");
    public static final String GENDER = TestWebDriver.getProperty("GENDER");
    public static final String DOB = TestWebDriver.getProperty("DOB");
    public static final String DL_NUMBER = TestWebDriver.getProperty("DL_NUMBER");
    public static final String DL_EXP = TestWebDriver.getProperty("DL_EXP");
    public static final String EFFD_DATE = TestWebDriver.getProperty("EFFD_DATE");
}