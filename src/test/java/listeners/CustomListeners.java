package listeners;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.ITestListener;
import org.testng.SkipException;
import utilities.Constants;
import utilities.ExcelUtilities;

import java.util.Hashtable;


public class CustomListeners extends TestBase implements ITestListener {

    public void onTestStart(org.testng.ITestResult result) {
        Hashtable<String, String> data = (Hashtable<String, String>) result.getParameters()[0];
        String testCaseName = result.getName();
        String sheetName = result.getTestClass().getRealClass().getName().split("[.]")[1];

        exTest = extentReports.startTest(result.getName().toUpperCase()+" Browser: "+data.get("Browser"));
        extentTest.set(exTest);

        if (data.get("RunMode").equalsIgnoreCase("No")) {
            throw new SkipException(testCaseName + " is set as 'No' for Run Mode");
        }
    }

    public synchronized void onTestSuccess(org.testng.ITestResult result) {
        Hashtable<String, String> data = (Hashtable<String, String>) result.getParameters()[0];
        String testCaseName = result.getName();
        String sheetName = result.getTestClass().getRealClass().getName().split("[.]")[1];

        ExcelUtilities eu = new ExcelUtilities(Constants.excelPath, sheetName, testCaseName);
        eu.updateStatusInExcelSheet(Integer.parseInt(data.get("TestCaseRowNumber")),"Pass");
        extentTest.get().log(LogStatus.PASS, testCaseName.toUpperCase()+" Passed");
        extentReports.endTest(extentTest.get());
        extentReports.flush();
    }

    public synchronized void onTestFailure(org.testng.ITestResult result) {
        Hashtable<String, String> data = (Hashtable<String, String>) result.getParameters()[0];
        String testCaseName = result.getName();
        String sheetName = result.getTestClass().getRealClass().getName().split("[.]")[1];

        ExcelUtilities eu = new ExcelUtilities(Constants.excelPath, sheetName, testCaseName);
        eu.updateStatusInExcelSheet(Integer.parseInt(data.get("TestCaseRowNumber")),"Fail");

        String path = takeScreenshot(result.getName());

        extentTest.get().log(LogStatus.FAIL, testCaseName.toUpperCase()+" FAILED with Exception: "+result.getThrowable());
        extentTest.get().log(LogStatus.FAIL, extentTest.get().addScreenCapture("."+path));
        extentReports.endTest(extentTest.get());
        extentReports.flush();
    }

    public synchronized void onTestSkipped(org.testng.ITestResult result) {
        Hashtable<String, String> data = (Hashtable<String, String>) result.getParameters()[0];
        String testCaseName = result.getName();
        String sheetName = result.getTestClass().getRealClass().getName().split("[.]")[1];

        ExcelUtilities eu = new ExcelUtilities(Constants.excelPath, sheetName, testCaseName);
        eu.updateStatusInExcelSheet(Integer.parseInt(data.get("TestCaseRowNumber")),"Skip");

        extentTest.get().log(LogStatus.SKIP, testCaseName.toUpperCase()+" Skipped with Exception: "+result.getThrowable());
        extentReports.endTest(extentTest.get());
        extentReports.flush();
    }

    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) { /* compiled code */ }

    public void onTestFailedWithTimeout(org.testng.ITestResult result) { /* compiled code */ }

    public void onStart(org.testng.ITestContext context) { /* compiled code */ }

    public void onFinish(org.testng.ITestContext context) {

    }
}
