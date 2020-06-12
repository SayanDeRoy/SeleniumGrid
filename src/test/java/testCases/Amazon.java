package testCases;

import base.TestBase;
import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.AmazonPO;


import java.util.Hashtable;

public class Amazon extends TestBase{

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Automate_Amazon_Pay_Menu_link_of_amazon_and_print_page_title(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AmazonPO amazonPO = new AmazonPO();
        amazonPO.verifyHomePageTitle();
        amazonPO.clickOnAmazonPay();
        amazonPO.verifyAmazonPayPageTitle();
        amazonPO.clickOnAmazonHome();
        amazonPO.verifyHomePageTitle();
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Automate_all_the_Menu_links_of_amazon_and_Verify_page_titles(Hashtable<String, String> data) throws InterruptedException {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AmazonPO amazonPO = new AmazonPO();
        amazonPO.clickOnAllNavLinksAndVerifyTitle();
        //webDriverThreadLocal.get().quit();
    }

    @AfterMethod
    public void tearDown() {
        try{
            webDriverThreadLocal.get().quit();
        } catch (NullPointerException e) {}
    }
}
