package TestsPackage;


import BasePackage.baseTest;
import PageObjectsPackage.Elements;
import PageObjectsPackage.LoginPage;
import PageObjectsPackage.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class LoginTests extends baseTest {

    @Test
    @Description("this test is to verify that system is not letting invalid user trespass")
    @Epic("EP001")
    @Feature("01: Login")
    @Story("SD1")
    public void invalidLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(Elements.invalidUsername);
        loginPage.enterPassword(Elements.invalidPassword);
        loginPage.clickLogin();
        loginPage.assertionOnError();
    }

    @Test
    @Description("this test is to verify that system is letting the valid user login")
    @Epic("EP001")
    @Feature("02: Login")
    @Story("SD2")
    public void validLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(Elements.username);
        loginPage.enterPassword(Elements.password);
        loginPage.clickLogin();
        HomePage homePage = new HomePage(driver);
        homePage.assertCondition();
    }
}