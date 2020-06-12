package testCases;

import base.TestBase;
import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.AutomationPracticePO;

import java.util.Hashtable;

public class AutomationPracticeOrder extends TestBase{

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Automate_End_to_End_Buy_Order_functionality(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AutomationPracticePO automationPracticePO = new AutomationPracticePO();
        automationPracticePO.clickOnSignInLink();
        automationPracticePO.loginToTheApplication(data);
        automationPracticePO.selectTShirtFromWomenSection();
        automationPracticePO.clickOnProductMoreLink(1);
        automationPracticePO.selectQuantitySizeColorAndClickAddToCart(data.get("Quantity"),data.get("Size"),data.get("Color"));
        automationPracticePO.verifyAddToCartSuccessMessage();
        automationPracticePO.removeProductFromCartAndLogout();
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_that_Add_to_Wishlist_only_works_after_login(Hashtable<String, String> data) {
        browserSetUpAndOpenApplication(data.get("Browser"), data.get("URL"));

        AutomationPracticePO automationPracticePO = new AutomationPracticePO();
        automationPracticePO.selectTShirtFromWomenSection();
        automationPracticePO.clickOnProductMoreLink(1);
        automationPracticePO.addToWishList();
        automationPracticePO.verifyWishListPopUpMessageWithOutLogin();
        //webDriverThreadLocal.get().quit();
    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Verify_Total_Price_is_reflecting_correctly_after_changes_quantity_on_Shopping_Cart_Page(Hashtable<String, String> data) {

    }

    @Test(dataProviderClass = CustomDataProvider.class, dataProvider = "Generic")
    public void Automate_Search_Product_Functionality(Hashtable<String, String> data) {

    }

    @AfterMethod
    public void tearDown() {
        try{
            webDriverThreadLocal.get().quit();
        } catch (NullPointerException e) {}
    }
}
