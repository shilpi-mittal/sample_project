package utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class TestWebDriver {
    private static int DEFAULT_WAIT_TIME;
    private static WebDriver webDriver;
    private static Properties prop = new Properties();

    public TestWebDriver(WebDriver driver) {
        webDriver = driver;
        loadPropertiesFile("config.txt");
        DEFAULT_WAIT_TIME = Integer.parseInt(TestConstants.DEFAULT_WAIT);
        maximizeWindows();
    }

    public static void setWebDriver(WebDriver newWebDriver) {
        webDriver = newWebDriver;
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public List<File> getDownloadedFilesByExtension() {
        String input = System.getProperty("user.dir");
        String projectRoot = input.substring(0, input.indexOf("instafin_test") + "instafin_test".length());
        return (List<File>) FileUtils.listFiles(new File(projectRoot  + "\\Downloads") , new String[] {TestConstants.FILE_EXT}, true);
    }

    public int getRowsCountInFile() throws IOException, EncryptedDocumentException, InvalidFormatException {
        List<File> files = getDownloadedFilesByExtension();
        File file = files.get(0);
        int rowsCount = 0;
        if(file.exists()){
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0);
            rowsCount = sheet.getLastRowNum();
            System.out.println("--------------------- rowsCount" + rowsCount);
        }else{
            System.out.println("----------------File does not exists!");
        }
        file.delete();
        return rowsCount - 5;
    }

    public boolean isFileDownloaded() {

        List<File> files = getDownloadedFilesByExtension();
        System.out.println(files.size());
        return getDownloadedFilesByExtension().size()>0;
    }

    public static void loadPropertiesFile(String propertyFileName) {
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName);
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToBaseUrl() {
        webDriver.manage().deleteAllCookies();
        navigateTo(TestConstants.BASE_URL);
    }

    public void maximizeWindows() {
        webDriver.manage().window().maximize();
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public void waitForElementToAppear(final WebElement element) {
        (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (element.isDisplayed());
            }
        });
    }

    public void waitForElementToDisappear(final WebElement element, int waitTime) {
        (new WebDriverWait(webDriver, waitTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!element.isDisplayed());
            }
        });
    }

    public void waitForElementToDisappear(final WebElement element) {
        (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!element.isDisplayed());
            }
        });
    }


    public void enterInput(final WebElement element, String input) {
        waitForElementToAppear(element);
        element.clear();
        element.sendKeys(input);
    }

    public void enterInput(final WebElement element, Keys input) {
        waitForElementToAppear(element);
        element.clear();
        element.sendKeys(input);
    }

    public void setValue(final WebElement element, String input) {
        waitForElementToAppear(element);

    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    public void quitDriver() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public void navigateTo(String url) {
        webDriver.navigate().to(url);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnElement(WebElement element) {
        waitForElementToAppear(element);
        element.click();
    }

    public String getText(WebElement element) {
        waitForElementToAppear(element);
        return element.getText().trim();
    }

    public Boolean isSelected(WebElement element) {
        waitForElementToAppear(element);
        return element.isSelected();
    }
}