package testCases;

import base.TestBase;
import dataProvider.CustomDataProvider;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.AutomationPracticePO;

import java.util.Hashtable;

public class AutomationPractice extends TestBase{

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Automate_User_Registration_Process(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AutomationPracticePO automationPracticePO = new AutomationPracticePO();
        automationPracticePO.clickOnSignInLink();
        automationPracticePO.enterEmailAndClickOnCreateAccount(data.get("EmailAddress"));
        automationPracticePO.enterAllValidValuesAndClickOnRegister(data);
        automationPracticePO.verifyRegisterSuccessful(data.get("FirstName"), data.get("LastName"));
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_invalid_email_address_error(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AutomationPracticePO automationPracticePO = new AutomationPracticePO();
        automationPracticePO.clickOnSignInLink();
        automationPracticePO.enterEmailAndClickOnCreateAccount(data.get("EmailAddress"));
        automationPracticePO.verifyCreateAccountError();
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_error_messages_for_mandatory_fields(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AutomationPracticePO automationPracticePO = new AutomationPracticePO();
        automationPracticePO.clickOnSignInLink();
        automationPracticePO.enterEmailAndClickOnCreateAccount(data.get("EmailAddress"));
        automationPracticePO.enterAllValidValuesAndClickOnRegister(data);
        automationPracticePO.verifyMandatoryFieldAlert(8);
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_error_messages_for_entering_incorrect_values_in_fields(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AutomationPracticePO automationPracticePO = new AutomationPracticePO();
        automationPracticePO.clickOnSignInLink();
        automationPracticePO.enterEmailAndClickOnCreateAccount(data.get("EmailAddress"));
        automationPracticePO.enterAllValidValuesAndClickOnRegister(data);
        automationPracticePO.verifyMandatoryFieldAlert(7);
        //webDriverThreadLocal.get().quit();
    }

    @AfterMethod
    public void tearDown() {
        try{
            webDriverThreadLocal.get().quit();
        } catch (NullPointerException e) {}
    }
}
