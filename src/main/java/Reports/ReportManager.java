package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            // Set the location where report will be generated
            sparkReporter = new ExtentSparkReporter("reports/ExtentReports.html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Test Results");
            sparkReporter.config().setReportName("Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Organization", "Your Company");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    public static void closeReporter() {
        if (extent != null) {
            extent.flush();
        }
    }
}