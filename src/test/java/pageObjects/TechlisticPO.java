package pageObjects;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Hashtable;
import java.util.List;

public class TechlisticPO extends TestBase{

    private String expectedTitle = "Selenium Practice Form";

    public TechlisticPO() {
        PageFactory.initElements(webDriverThreadLocal.get(), this);
    }

    @FindBy(how = How.XPATH, using = "//span[text()='First name:']/following-sibling::input")
    private WebElement firstName;

    @FindBy(how = How.XPATH, using = "//span[text()='Last name:']/following-sibling::input")
    private WebElement lastName;

    @FindBy(how = How.XPATH, using = "//b[text()='Gender']/following-sibling::input")
    private List<WebElement> gender;

    @FindBy(how = How.XPATH, using = "//span[text()='Years of Experience']/following-sibling::input")
    private List<WebElement> yearOfExp;

    @FindBy(how = How.ID, using = "datepicker")
    private WebElement date;

    @FindBy(how = How.XPATH, using = "//span[text()='Profession']/following-sibling::input")
    private List<WebElement> profession;

    @FindBy(how = How.XPATH, using = "//span[text()='Automation Tools']/following-sibling::input")
    private List<WebElement> automationTool;

    @FindBy(how = How.ID, using = "continents")
    private WebElement continents;

    @FindBy(how = How.ID, using = "selenium_commands")
    private WebElement seleniumCommands;

    public void enterAllDetails(Hashtable<String, String> data) {
        data.forEach((key, value) -> {
            switch (key) {
                case "FirstName":
                    firstName.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO,"Successfully entered: "+value+" as "+key);
                    break;
                case "LastName":
                    lastName.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO,"Successfully entered: "+value+" as "+key);
                    break;
                case "Gender":
                    selectRadioButton(gender, value);
                    extentTest.get().log(LogStatus.INFO,"Successfully selected: "+value+" as "+key);
                    break;
                case "YearsOfExp":
                    selectRadioButton(yearOfExp, value);
                    extentTest.get().log(LogStatus.INFO,"Successfully selected: "+value+" as "+key);
                    break;
                case "Date":
                    date.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO,"Successfully entered: "+value+" as "+key);
                    break;
                case "Profession":
                    selectCheckBox(profession, value);
                    extentTest.get().log(LogStatus.INFO,"Successfully selected: "+value+" as "+key);
                    break;
                case "AutomationTool":
                    selectCheckBox(automationTool, value);
                    extentTest.get().log(LogStatus.INFO,"Successfully selected: "+value+" as "+key);
                    break;
                case "Continents":
                    selectFromDropDown(continents, value);
                    extentTest.get().log(LogStatus.INFO,"Successfully selected: "+value+" as "+key);
                    break;
                case "SeleniumCommand":
                    selectFromMultiSelectDropDown(seleniumCommands, value);
                    extentTest.get().log(LogStatus.INFO,"Successfully selected: "+value+" as "+key);
                    break;
            }
        });
    }

    public void verifyPageTitle() {
        String actualTitle = webDriverThreadLocal.get().getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        extentTest.get().log(LogStatus.INFO,"Verified page title");
    }
}
