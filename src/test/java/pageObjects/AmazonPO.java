package pageObjects;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AmazonPO extends TestBase {

    private String expectedHomePageTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
    private String expectedAmazonPayTitle = "Amazon Pay";
    private List<String> linkTitle = new ArrayList<>();

    public AmazonPO() {
        PageFactory.initElements(webDriverThreadLocal.get(), this);
    }

    @FindBy(how = How.LINK_TEXT, using = "Amazon Pay")
    WebElement amazonPayLink;

    @FindBy(how = How.XPATH, using = "//a[@class = 'nav-logo-link']")
    WebElement homeLink;

    @FindBy(how = How.XPATH, using = "//div[@id='nav-xshop']/a")
    List<WebElement> navLinks;

    public void clickOnAmazonHome() {
        homeLink.click();
        extentTest.get().log(LogStatus.INFO,"Amazon Home Link Clicked");
    }

    public void clickOnAmazonPay() {
        amazonPayLink.click();
        extentTest.get().log(LogStatus.INFO,"Amazon Pay Link Clicked");
    }

    public void verifyHomePageTitle() {
        waitForPageLoad(expectedHomePageTitle);
        String actualHomePageTitle = webDriverThreadLocal.get().getTitle();
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
        extentTest.get().log(LogStatus.INFO,"Amazon Home Page Title Verified Successfully");
    }

    public void verifyAmazonPayPageTitle() {
        waitForPageLoad(expectedAmazonPayTitle);
        String actualAmazonPayPageTitle = webDriverThreadLocal.get().getTitle();
        Assert.assertEquals(actualAmazonPayPageTitle, expectedAmazonPayTitle);
        extentTest.get().log(LogStatus.INFO,"Amazon Pay Page Title Verified Successfully");
    }

    public void clickOnAllNavLinksAndVerifyTitle() throws InterruptedException {
        linkTitle.add("Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in");
        linkTitle.add("Amazon.in Bestsellers: The most popular items on Amazon");
        linkTitle.add("Amazon Pantry: The Online Grocery Shopping Store- Shop Daily Grocery Items and Get delivered in Next Day- Amazon.in");
        linkTitle.add("Computers & Accessories: Buy Computers & Accessories Online at Low Prices in India - Amazon.in");
        linkTitle.add(expectedAmazonPayTitle);
        linkTitle.add("Amazon.in Hot New Releases: The bestselling new and future releases on Amazon");
        linkTitle.add("Book Store Online : Buy Books Online at Best Prices in India | Books Shopping @ Amazon.in");
        linkTitle.add("Amazon.in Help: Help");
        linkTitle.add("Amazon.in: Selling on Amazon - Start Selling Now");
        linkTitle.add("Gifts for Everyone | Amazon.in Gift Finder");
        linkTitle.add("Baby Products: Buy New Born Baby Products online at best prices in India - Amazon.in");
        linkTitle.add("AmazonBasics India | iPhone Cables, Travel Bags, Tripods, Batteries, Dinner Sets, Car Chargers & more");
        linkTitle.add("Amazon Coupons: Checkout Latest Amazon Discount Coupons Online Across Categories from Top Brands at Amazon.in");

        List<WebElement> navLinks = webDriverThreadLocal.get().findElements(By.xpath("//div[@id='nav-xshop']/a"));
        for (int i = 0; i < navLinks.size(); i++) {
            navLinks.get(i).click();
            waitForPageLoad(linkTitle.get(i));
            String actualTitle = webDriverThreadLocal.get().getTitle();

            Assert.assertEquals(actualTitle, linkTitle.get(i));

            navLinks = webDriverThreadLocal.get().findElements(By.xpath("//div[@id='nav-xshop']/a"));
        }
        extentTest.get().log(LogStatus.INFO,"Amazon All Page Titles Verified Successfully");
    }
}
