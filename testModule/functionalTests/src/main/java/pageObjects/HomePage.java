package pageObjects;

import utils.TestWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static org.openqa.selenium.support.How.ID;
import static org.openqa.selenium.support.How.NAME;

public class HomePage extends Page {
    @FindBy(how = ID, using = "hplogo")
    private static WebElement logo = null;

    @FindBy(how = NAME, using = "q")
    private static WebElement searchBar = null;

    public HomePage(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(TestWebDriver.getDriver(), 1), this);
    }

    public boolean isLogoDisplayed() {
        testWebDriver.waitForElementToAppear(logo);
        return testWebDriver.isDisplayed(logo);
    }

    public void enterSearchParameter(String searchParam) {
        testWebDriver.waitForElementToAppear(searchBar);
        searchBar.clear();
        searchBar.sendKeys(searchParam);
    }

    public void hitEnter() {
        searchBar.sendKeys(Keys.ENTER);
    }

    public boolean isTextDisplayed(String text) {
        testWebDriver.sleep(1000);
        List<WebElement> elements = testWebDriver.findElements(By.xpath("//a[contains(text(),'" + text + "')]"));
        return elements.size()!=0;
    }
}