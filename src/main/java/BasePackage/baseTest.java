package BasePackage;

import Utilities.BrowserManager;
import Utilities.ConfigReader;
import Reports.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public abstract class baseTest {
    protected WebDriver driver;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private ExtentReports extent;

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    @BeforeSuite
    public void setup() {
        extent = ReportManager.getReporter();
    }

    @BeforeMethod
    public void setUpMethod() {
        ConfigReader configReader = new ConfigReader();
        String browser = configReader.getProperty("browser");
        driver = BrowserManager.getDriver(browser);
        driver.manage().window().maximize();
        String baseUrl = configReader.getProperty("baseUrl");
        driver.get(baseUrl);

        String testName = this.getClass().getSimpleName() + " setup";
        test.set(extent.createTest(testName));
        System.out.println("Browser opened.");
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            getTest().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            getTest().pass("Test passed");
        } else {
            getTest().skip("Test skipped");
        }

        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
        test.remove();
    }

    @AfterSuite
    public void tearDownSuite() {
        ReportManager.closeReporter();
    }
}
