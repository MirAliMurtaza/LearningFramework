package Reports;

import BasePackage.baseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureReporting implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // This method captures the screenshot directly as a byte array
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveFailedScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting Test Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finished Test Suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting Test: " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Failed: " + getTestMethodName(iTestResult));

        Object testClass = iTestResult.getInstance();
        WebDriver driver = null;
        if (testClass instanceof baseTest) {
        }

        if (driver != null) {
            saveFailedScreenshot(driver);
            attachScreenshot(driver);
            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
        } else {
            System.out.println("Driver is not available, screenshot not captured.");
        }
    }
    public void attachScreenshot(WebDriver driver) {
        // Capture screenshot as byte array
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // Attach screenshot to Allure
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotBytes));
    }



    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within success percentage: " + getTestMethodName(result));
    }
}