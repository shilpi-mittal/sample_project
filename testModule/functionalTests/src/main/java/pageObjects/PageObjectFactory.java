package pageObjects;

import utils.TestWebDriver;

public class PageObjectFactory {
    private static Page instanceOfPage;
    private static HomePage instanceOfHomePage;
    private static InsurancePolicyFormPage instanceOfInsurancePolicyFormPage;

    public static Page getPage(TestWebDriver testWebDriver) {
        if(instanceOfPage == null) {
            instanceOfPage = new Page(testWebDriver);
        }
        return instanceOfPage;
    }

    public static HomePage getHomePage(TestWebDriver testWebDriver) {
        if(instanceOfHomePage == null) {
            instanceOfHomePage = new HomePage(testWebDriver);
        }
        return instanceOfHomePage;
    }

    public static InsurancePolicyFormPage getInsurancePolicyFormPage(TestWebDriver testWebDriver) {
        if(instanceOfInsurancePolicyFormPage == null) {
            instanceOfInsurancePolicyFormPage = new InsurancePolicyFormPage(testWebDriver);
        }
        return instanceOfInsurancePolicyFormPage;
    }

    public static void clearAllPageObjects() {
//        for (Field f :PageObjectFactory.class.getDeclaredFields()) {
//            f.set();null;
//        }
        instanceOfPage=null;
        instanceOfHomePage=null;
        instanceOfInsurancePolicyFormPage=null;
    }
}