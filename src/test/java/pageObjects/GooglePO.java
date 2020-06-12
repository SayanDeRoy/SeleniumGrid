package pageObjects;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class GooglePO extends TestBase {

    public GooglePO() {
        PageFactory.initElements(webDriverThreadLocal.get(), this);
    }

    @FindBy(how = How.NAME, using = "q")
    private WebElement googleSearchBox;

    @FindBy(how = How.XPATH, using = "//div[@class='sbl1']/span")
    private List<WebElement> searchResult;

    public void verifyGoogleSearch(String searchText) {
        googleSearchBox.sendKeys(searchText);
        extentTest.get().log(LogStatus.INFO, "Entered Text in Google search section: " + searchText);
        waitForAjaxByTagName("b");
        for (int i = 0; i < searchResult.size() - 1; i++) {
            String actualText = searchResult.get(i).getText();
            if (!actualText.contains(searchText)) {
                Assert.assertTrue(false, searchText + " is not available in " + actualText);
            }
        }
        extentTest.get().log(LogStatus.INFO, "Verified all suggestion text contains: " + searchText);
    }
}
