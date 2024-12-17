package PageObjectsPackage;

import Utilities.ConfigReader;
import org.openqa.selenium.By;

public class Elements {
    static ConfigReader configReader = new ConfigReader();
    public static final String username = configReader.getProperty("Username");
    public static final String password = configReader.getProperty("Password");
    public static final String invalidUsername = configReader.getProperty("InvalidPass");
    public static final String invalidPassword = configReader.getProperty("InvalidUser");
    public static final By usernameField = By.id("user-name");
    public static final By passwordField = By.id("password");
    public static final By loginButton = By.id("login-button");
    public static final By errorLocator = By.cssSelector("h3[data-test='error']");
    public static final String errorMessage = "Epic sadface: Username and password do not match any user in this service";
    public static final By homePageLogo = By.cssSelector(".app_logo");
}
