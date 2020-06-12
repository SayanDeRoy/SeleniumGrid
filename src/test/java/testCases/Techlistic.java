package testCases;

import base.TestBase;
import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.TechlisticPO;
import utilities.BrowserInitialization;

import java.util.Hashtable;

public class Techlistic extends TestBase{

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Enter_value_in_selenium_practice_form(Hashtable<String, String> data){
        browserSetUpAndOpenApplication(data.get("Browser"),data.get("URL"));

        TechlisticPO techlisticPO = new TechlisticPO();
        techlisticPO.enterAllDetails(data);
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_Selenium_Practice_Form_Page_Title(Hashtable<String, String> data){
        browserSetUpAndOpenApplication(data.get("Browser"),data.get("URL"));

        TechlisticPO techlisticPO = new TechlisticPO();
        techlisticPO.verifyPageTitle();
        //webDriverThreadLocal.get().quit();
    }

    @AfterMethod
    public void tearDown() {
        try{
            webDriverThreadLocal.get().quit();
        } catch (NullPointerException e) {}
    }
}
