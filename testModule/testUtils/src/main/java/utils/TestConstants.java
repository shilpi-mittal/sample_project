package utils;

public final class TestConstants {

    public static final String BASE_URL = TestWebDriver.getProperty("BASR_URL");
    public static final String USER_NAME = TestWebDriver.getProperty("USER_NAME");
    public static final String PASSWORD = TestWebDriver.getProperty("PASSWORD");
    public static final String DEFAULT_WAIT = TestWebDriver.getProperty("DEFAULT_WAIT");
    public static final String FILE_EXT = TestWebDriver.getProperty("FILE_EXT");

    private TestConstants() {
    }

}
