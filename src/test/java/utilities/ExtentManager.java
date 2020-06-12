package utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReportInstance()
    {
        if(extent == null)
        {
            extent = new ExtentReports(Constants.extentReportPath,true, DisplayOrder.OLDEST_FIRST);
            extent.loadConfig(new File(Constants.extentConfigPath));
        }
        return extent;
    }
}
