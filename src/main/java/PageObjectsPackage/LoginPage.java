package PageObjectsPackage;

import Utilities.GenericFunctions;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private GenericFunctions genericFunctions;

    // Constructor to initialize the WebDriver and Generic utility class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.genericFunctions = new GenericFunctions(driver);  // Pass the WebDriver instance to Generic
    }

    // Method to enter username
    public void enterUsername(String username) {
        genericFunctions.inputText(Elements.usernameField, username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        genericFunctions.inputText(Elements.passwordField, password);
    }

    // Method to simulate the login button click
    public void clickLogin() {
        genericFunctions.clickElement(Elements.loginButton);
    }

    // Method to retrieve the error message from the page
    public String getErrorMessage() {
        return driver.findElement(Elements.errorLocator).getText();
    }

    // Method to assert the error message for validation
    public void assertionOnError() {
        genericFunctions.assertText(Elements.errorLocator, Elements.errorMessage);
    }
}
