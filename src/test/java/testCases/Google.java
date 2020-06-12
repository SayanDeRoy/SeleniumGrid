package testCases;

import base.TestBase;
import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.GooglePO;
import utilities.BrowserInitialization;

import java.util.Hashtable;

public class Google extends TestBase{

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_all_the_options_displayed_in_Google_suggestion_box(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        GooglePO googlePO = new GooglePO();
        googlePO.verifyGoogleSearch(data.get("SearchText"));
        //webDriverThreadLocal.get().quit();
    }

    @AfterMethod
    public void tearDown() {
        try{
            webDriverThreadLocal.get().quit();
        } catch (NullPointerException e) {}

    }
}
