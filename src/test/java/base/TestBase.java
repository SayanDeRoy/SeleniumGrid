package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;
import utilities.ExtentManager;
import utilities.PropertyFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;

    public ExtentReports extentReports = ExtentManager.getExtentReportInstance();
    public ExtentTest exTest;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<WebDriver>();

    public synchronized void browserSetUpAndOpenApplication(String browser, String url) {
        PropertyFile pf = new PropertyFile();
        String executionMode = pf.getPropertyValue("ExecutionMode", Constants.propertyPath);
        if (executionMode.equalsIgnoreCase("Local") || executionMode.equals("")) {
            if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", Constants.chromeExecutablePath);
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", Constants.ffExecutablePath);
                driver = new FirefoxDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(pf.getPropertyValue("implicitWait", Constants.propertyPath)), TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            driver.get(pf.getPropertyValue(url, Constants.propertyPath));
        } else if (executionMode.equalsIgnoreCase("Grid")) {
            DesiredCapabilities desiredCapabilities = null;

            if (browser.equalsIgnoreCase("Chrome")) {
                ChromeOptions co = new ChromeOptions();
                co.setCapability("BrowserName", "chrome");
                co.setCapability("Version", "83");
                /*desiredCapabilities = DesiredCapabilities.chrome();
                desiredCapabilities.setBrowserName("chrome");
                desiredCapabilities.setPlatform(Platform.ANY);*/
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"),co);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (browser.equalsIgnoreCase("Firefox")) {
                FirefoxOptions fo = new FirefoxOptions();
                fo.setCapability("BrowserName", "firefox");
                fo.setCapability("Version", "76");
                /*desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setBrowserName("firefox");
                desiredCapabilities.setPlatform(Platform.ANY);*/
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"),fo);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(pf.getPropertyValue("implicitWait", Constants.propertyPath)), TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            driver.get(pf.getPropertyValue(url, Constants.propertyPath));

        } else {

        }
        webDriverThreadLocal.set(driver);
    }

    public void waitForPageLoad(String title) {
        WebDriverWait wait = new WebDriverWait(webDriverThreadLocal.get(), 30);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void resolveStaleElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(webDriverThreadLocal.get(), 5);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(ele)));
    }

    public void waitForAjaxByTagName(String tagName) {
        WebDriverWait wait = new WebDriverWait(webDriverThreadLocal.get(), 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(tagName)));
    }

    public void waitForAjaxElementToBeClickable(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(webDriverThreadLocal.get(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public synchronized void waitForAjaxElementToBeVisible(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(webDriverThreadLocal.get(), 10);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void selectRadioButton(List<WebElement> radioButtons, String value) {
        for (WebElement ele : radioButtons) {
            if (ele.getAttribute("value").equals(value)) {
                ele.click();
            }
        }
    }

    public void selectCheckBox(List<WebElement> checkBoxes, String value) {
        for (WebElement ele : checkBoxes) {
            if (ele.getAttribute("value").equals(value)) {
                ele.click();
            }
        }
    }

    public void selectFromDropDown(WebElement dropDown, String value) {
        Select sel = new Select(dropDown);
        sel.selectByVisibleText(value);
    }

    public void selectFromDropDownUsingValue(WebElement dropDown, String value) {
        Select sel = new Select(dropDown);
        sel.selectByValue(value);
    }

    public void selectFromMultiSelectDropDown(WebElement dropDown, String value) {
        Select sel = new Select(dropDown);
        String[] values = value.split(",");
        for (int i = 0; i < values.length; i++) {
            sel.selectByVisibleText(values[i]);
        }
    }

    public void scrollToElement(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) webDriverThreadLocal.get();
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public String getValueUsingJavaScript(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) webDriverThreadLocal.get();
        return js.executeScript("return arguments[0].value;", ele).toString();
    }

    public void mouseHoverToAnElementAndClickOnAnotherElement(WebElement ele1, WebElement ele2) {
        scrollToElement(ele1);
        Actions act = new Actions(webDriverThreadLocal.get());
        act.moveToElement(ele1);
        act.moveToElement(ele2).click().build().perform();
    }

    public synchronized String takeScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriverThreadLocal.get();
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String path = Constants.screenShotPath + fileName + ".jpg";
        File dest = new File(path);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
