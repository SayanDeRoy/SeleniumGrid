package pageObjects;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Godaddy extends TestBase {

    private String expectedPageTitle = "Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy IN";
    private String expectedURL = "https://in.godaddy.com/";

    public Godaddy() {
        PageFactory.initElements(webDriverThreadLocal.get(), this);
    }

    public void verifyPageTitle() {
        String actualPageTitle = webDriverThreadLocal.get().getTitle();
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        extentTest.get().log(LogStatus.INFO,"Verified Godday page title");
    }

    public void verifyCurrentURL() {
        String actualURL = webDriverThreadLocal.get().getCurrentUrl();
        Assert.assertEquals(actualURL,expectedURL);
        extentTest.get().log(LogStatus.INFO,"Verified Godday current url");
    }

    public void verifyTitleAvailableInPageSource() {
        String pageSource = webDriverThreadLocal.get().getPageSource();
        if(!pageSource.contains(expectedPageTitle))
        {
            Assert.assertTrue(false,"Page Title is not available inside Page Source");
        }
    }
}
