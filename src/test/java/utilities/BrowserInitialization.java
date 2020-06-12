package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

public class BrowserInitialization {

    public WebDriver browserSetUpAndOpenApplication(String browser, String url) {
        PropertyFile pf = new PropertyFile();
        WebDriver driver = null;
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

        } else {

        }
        return driver;
    }
}
