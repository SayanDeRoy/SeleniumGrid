package testCases;

import base.TestBase;
import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.Godaddy;


import java.util.Hashtable;

public class AutomateBrowserActions extends TestBase {

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Launch_browser_and_Open_Godaddy(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Open_Godaddy_and_Validate_it_Page_title(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        Godaddy godaddy = new Godaddy();
        godaddy.verifyPageTitle();
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Open_Godaddy_and_Validate_current_url(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        Godaddy godaddy = new Godaddy();
        godaddy.verifyCurrentURL();
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Open_Godaddy_and_Validate_Page_Title_Present_in_Page_Source(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        Godaddy godaddy = new Godaddy();
        godaddy.verifyTitleAvailableInPageSource();
        //webDriverThreadLocal.get().quit();
    }

    @AfterMethod
    public void tearDown() {
        try{
            webDriverThreadLocal.get().quit();
        } catch (NullPointerException e) {}
    }
}
