package PageObjectsPackage;

import Utilities.GenericFunctions;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private GenericFunctions genericFunctions;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.genericFunctions = new GenericFunctions(driver);
    }

    public void assertCondition() {
        genericFunctions.assertTrueCondition(Elements.homePageLogo, "Home page should be loaded.");
    }
}
