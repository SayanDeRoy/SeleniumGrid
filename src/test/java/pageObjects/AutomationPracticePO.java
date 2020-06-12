package pageObjects;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Hashtable;
import java.util.List;

public class AutomationPracticePO extends TestBase {

    private String expectedCreateAccountError = "Invalid email address.";
    private String expectedWelcomeMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
    private String expectedAddCartSuccessMessage = "Product successfully added to your shopping cart";
    private String emptyShoppingCartMessage = "Your shopping cart is empty.";
    private String wishListPopUpMessage = "You must be logged in to manage your wishlist.";

    public AutomationPracticePO() {
        PageFactory.initElements(webDriverThreadLocal.get(), this);
    }

    @FindBy(how = How.CLASS_NAME, using = "login")
    private WebElement logInLink;

    @FindBy(how = How.ID, using = "email_create")
    private WebElement createEmail;

    @FindBy(how = How.ID, using = "email")
    private WebElement loginEmail;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement loginPassword;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement loginButton;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(how = How.XPATH, using = "//div[@id='create_account_error']//li")
    private WebElement createAccountError;

    @FindBy(how = How.ID, using = "id_gender1")
    private WebElement radioButtonMr;

    @FindBy(how = How.ID, using = "id_gender2")
    private WebElement radioButtonMrs;

    @FindBy(how = How.ID, using = "customer_firstname")
    private WebElement customerFirstName;

    @FindBy(how = How.ID, using = "customer_lastname")
    private WebElement customerLastName;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement password;

    @FindBy(how = How.ID, using = "days")
    private WebElement days;

    @FindBy(how = How.ID, using = "months")
    private WebElement months;

    @FindBy(how = How.ID, using = "years")
    private WebElement years;

    @FindBy(how = How.ID, using = "firstname")
    private WebElement addressFirstName;

    @FindBy(how = How.ID, using = "lastname")
    private WebElement addressLastName;

    @FindBy(how = How.ID, using = "company")
    private WebElement company;

    @FindBy(how = How.ID, using = "address1")
    private WebElement address;

    @FindBy(how = How.ID, using = "city")
    private WebElement city;

    @FindBy(how = How.ID, using = "id_state")
    private WebElement state;

    @FindBy(how = How.ID, using = "postcode")
    private WebElement postCode;

    @FindBy(how = How.ID, using = "id_country")
    private WebElement country;

    @FindBy(how = How.ID, using = "phone_mobile")
    private WebElement mobileNumber;

    @FindBy(how = How.ID, using = "submitAccount")
    private WebElement register;

    @FindBy(how = How.XPATH, using = "//a[@class='account']/span")
    private WebElement loginUser;

    @FindBy(how = How.CLASS_NAME, using = "info-account")
    private WebElement welcomeMessageElement;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']//li")
    private List<WebElement> alertMessages;

    @FindBy(how = How.XPATH, using = "//a[@title='Women']")
    private WebElement womenLink;

    @FindBy(how = How.XPATH, using = "(//a[text()='T-shirts'])[1]")
    private WebElement TShirtLink;

    @FindBy(how = How.XPATH, using = "(//a[text()='T-shirts'])[2]")
    private WebElement TShirtLink2;

    @FindBy(how = How.XPATH, using = "//a[@class='product_img_link']//img")
    private List<WebElement> products;

    @FindBy(how = How.XPATH, using = "//a[@title='View']")
    private List<WebElement> productsMore;

    @FindBy(how = How.ID, using = "quantity_wanted")
    private WebElement quantity;

    @FindBy(how = How.ID, using = "group_1")
    private WebElement size;

    @FindBy(how = How.XPATH, using = "//ul[@id='color_to_pick_list']//a")
    private List<WebElement> colors;

    @FindBy(how = How.NAME, using = "Submit")
    private WebElement addToCart;

    @FindBy(how = How.XPATH, using = "//span[@class='cross']/following-sibling::h2")
    private WebElement addToCartMessage;

    @FindBy(how = How.XPATH, using = "//span[@class='cross']")
    private WebElement crossButton;

    @FindBy(how = How.XPATH, using = "//a[@title='View my shopping cart']")
    private WebElement viewMyShoppingCart;

    @FindBy(how = How.CLASS_NAME, using = "cart_quantity_delete")
    private WebElement removeProduct;

    @FindBy(how = How.XPATH, using = "//p[@class='alert alert-warning']")
    private WebElement shoppingCartMessage;

    @FindBy(how = How.CLASS_NAME, using = "logout")
    private WebElement logoutButton;

    @FindBy(how = How.ID, using = "wishlist_button")
    private WebElement wishListButton;

    @FindBy(how = How.XPATH, using = "//p[@class='fancybox-error']")
    private WebElement wishListPopUp;


    public synchronized void clickOnSignInLink() {
        logInLink.click();
        extentTest.get().log(LogStatus.INFO, "Successfully clicked on SignIn link");
    }

    public void enterEmailAndClickOnCreateAccount(String emailAddress) {
        createEmail.sendKeys(emailAddress);
        createAccountButton.click();
        extentTest.get().log(LogStatus.INFO, "Enter Email Address: " + emailAddress + " and click on Create Account button");
    }

    public void verifyCreateAccountError() {
        String actualCreateAccountError = createAccountError.getText();
        Assert.assertEquals(actualCreateAccountError, expectedCreateAccountError);
        extentTest.get().log(LogStatus.INFO, "Successfully verified Create Account Error: " + actualCreateAccountError);
    }

    public void enterAllValidValuesAndClickOnRegister(Hashtable<String, String> data) {
        data.forEach((Key, value) -> {
            switch (Key) {
                case "Title":
                    if (value.equals("Mr.")) {
                        radioButtonMr.click();
                    } else {
                        radioButtonMrs.click();
                    }
                    extentTest.get().log(LogStatus.INFO, "Successfully selected: " + value + " Radio button");
                    break;

                case "FirstName":
                    customerFirstName.sendKeys(value);
                    String actualAddressFirstNameValue = getValueUsingJavaScript(addressFirstName);
                    Assert.assertEquals(actualAddressFirstNameValue, value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "LastName":
                    customerLastName.sendKeys(value);
                    String actualAddressLastNameValue = getValueUsingJavaScript(addressLastName);
                    Assert.assertEquals(actualAddressLastNameValue, value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "Pasword":
                    password.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "DOB":
                    String[] dateParts = value.split("-");
                    selectFromDropDownUsingValue(days, dateParts[0]);
                    selectFromDropDownUsingValue(months, dateParts[1]);
                    selectFromDropDownUsingValue(years, dateParts[2]);
                    extentTest.get().log(LogStatus.INFO, "Successfully selected: " + value + " as " + Key);
                    break;

                case "Company":
                    company.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "Address":
                    address.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "City":
                    city.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "State":
                    selectFromDropDown(state, value);
                    extentTest.get().log(LogStatus.INFO, "Successfully selected: " + value + " as " + Key);
                    break;

                case "PostCode":
                    postCode.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;

                case "Mobile":
                    mobileNumber.sendKeys(value);
                    extentTest.get().log(LogStatus.INFO, "Successfully entered: " + value + " as " + Key);
                    break;
            }
        });
        register.click();
        extentTest.get().log(LogStatus.INFO, "Successfully clicked on Register button");
    }

    public void verifyRegisterSuccessful(String firstName, String lastName) {
        String expectedLoginUser = firstName + " " + lastName;
        Assert.assertEquals(loginUser.getText(), expectedLoginUser);
        Assert.assertEquals(welcomeMessageElement.getText(), expectedWelcomeMessage);
        extentTest.get().log(LogStatus.INFO, "Verified Register Successfully performed");
    }

    public void verifyMandatoryFieldAlert(int totalError) {
        Assert.assertEquals(alertMessages.size(), totalError);
        extentTest.get().log(LogStatus.INFO, "Verified mandatory field alerts");
        for (WebElement ele : alertMessages) {
            System.out.println(ele.getText());
        }
    }

    public void loginToTheApplication(Hashtable<String, String> data) {
        loginEmail.sendKeys(data.get("EmailAddress"));
        loginPassword.sendKeys(data.get("Pasword"));
        loginButton.click();
        verifyRegisterSuccessful(data.get("FirstName"), data.get("LastName"));
        extentTest.get().log(LogStatus.INFO, "Login successfully performed");
    }

    public void selectTShirtFromWomenSection() {
        //testBase.mouseHoverToAnElementAndClickOnAnotherElement(womenLink, TShirtLink);
        TShirtLink2.click();
        extentTest.get().log(LogStatus.INFO, "Clicked on T-shirt link");
    }

    public void clickOnProductMoreLink(int productPosition) {
        int productIndex = productPosition - 1;
        WebElement product = products.get(productIndex);
        WebElement productMore = productsMore.get(productIndex);
        mouseHoverToAnElementAndClickOnAnotherElement(product, productMore);
        extentTest.get().log(LogStatus.INFO, "Clicked on More link from product catalog");
    }

    public void selectQuantitySizeColorAndClickAddToCart(String quantity, String size, String color) {
        this.quantity.clear();
        this.quantity.sendKeys(quantity);
        selectFromDropDown(this.size, size);
        for (WebElement ele : colors) {
            if (ele.getAttribute("name").equalsIgnoreCase(color)) {
                ele.click();
            }
        }
        extentTest.get().log(LogStatus.INFO, "Successfully selected Quantity " + quantity + ", Size " + size + " and Color " + color);
        addToCart.click();
        extentTest.get().log(LogStatus.INFO, "Clicked on Add To Cart button");
    }

    public void verifyAddToCartSuccessMessage() {
        waitForAjaxElementToBeClickable(crossButton);
        String actualAddCartSuccessMessage = addToCartMessage.getText().trim();
        Assert.assertEquals(actualAddCartSuccessMessage, expectedAddCartSuccessMessage);
        crossButton.click();
        extentTest.get().log(LogStatus.INFO, "Verified add to cart success message and clicked on close button");
    }

    public void removeProductFromCartAndLogout() {
        //testBase.mouseHoverToAnElementAndClickOnAnotherElement(viewMyShoppingCart, removeProduct);
        viewMyShoppingCart.click();
        removeProduct.click();
        waitForAjaxElementToBeVisible(shoppingCartMessage);
        Assert.assertEquals(shoppingCartMessage.getText(), emptyShoppingCartMessage);
        logoutButton.click();
        extentTest.get().log(LogStatus.INFO, "Remove product from cart and Performed logout");
    }

    public void addToWishList() {
        wishListButton.click();
        extentTest.get().log(LogStatus.INFO, "Clicked on wishlist button");
    }

    public void verifyWishListPopUpMessageWithOutLogin() {
        Assert.assertEquals(wishListPopUp.getText(), wishListPopUpMessage);
        extentTest.get().log(LogStatus.INFO, "Verified wishlist error without login");
    }
}
