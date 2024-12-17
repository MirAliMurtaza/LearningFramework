package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import BasePackage.baseTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver webDriver = baseTest.getDriver();

        if (webDriver != null && webDriver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
            try {
                String screenshotAsBase64 = takesScreenshot.getScreenshotAs(OutputType.BASE64);
                baseTest.getTest().fail("Test failed",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotAsBase64).build());
            } catch (Exception e) {
                baseTest.getTest().fail("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            baseTest.getTest().fail("Driver does not support screenshots or is null");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Can log success or perform other actions
        baseTest.getTest().pass("Test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Can log skipped tests or perform other actions
        baseTest.getTest().skip("Test skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // Can initialize things before start of Test Suite
    }

    @Override
    public void onFinish(ITestContext context) {
        // Can clean up things after all tests are executed
    }
}
