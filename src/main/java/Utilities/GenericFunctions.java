package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class GenericFunctions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GenericFunctions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));   // Assume 10 seconds wait, adjust as necessary
    }

    public void inputText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear(); // Clears any pre-existing text
        element.sendKeys(text);
    }

    public void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void selectFromDropdownByText(By locator, String visibleText) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    public void assertText(By locator, String expectedText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertEquals(element.getText(), expectedText, "Text assertion failed at locator: " + locator);
    }

    public void assertTrueCondition(By locator, String failureMessage) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(element.isDisplayed(), failureMessage);
    }
}
