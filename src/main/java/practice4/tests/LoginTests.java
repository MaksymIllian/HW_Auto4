package practice4.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice4.pages.LoginPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class LoginTests {

    private WebDriver driver; // Declare var
    private LoginPage loginPage;
    private SoftAssert softAssert;
    @BeforeSuite
    public void beforeSuite() {
        //open browser
        driver = new FirefoxDriver(); //initialize/create object/open firefox
    }
    @BeforeTest
    public void beforeTest() {

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"admin", "123", "Login", "Invalid username or password"},
                {"admins", "123", "Login", "Invalid username or password"}
        };
    }
    @DataProvider
    public Object[][] positiveLoginData() {
        return new Object[][]{
                {"admin", "123", "Players"}
        };
    }
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open(); //open poker URL
        softAssert = new SoftAssert();
    }

    @Parameters({"urs","yhiju","title"})
    @Test
    public void positiveTest(String username, String password, String title) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.open(); //open poker URL
        loginPage.login(username,password);
        softAssert.assertEquals(driver.getTitle(), title, "Wrong title after login");
        softAssert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, "You are still on login page.");
        softAssert.assertAll();
    }

    @Test
    public void negativeTestWrongPasssord(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.open(); //open poker URL
        loginPage.login("admin", "123fuck");
        String expectedMsg = "Invalid username or password";
        String actualMsg = loginPage.getErrorMessage();
        softAssert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        softAssert.assertEquals(driver.getTitle(),   "Login", "Wrong title after unsuccessful login");
        softAssert.assertEquals(actualMsg, expectedMsg, "Validation error message is not valid.");
    }

    @Test (dataProvider = "loginData")
    public void negativeTestWrongLogin(String username, String password, String title, String expectedMsg) {
        loginPage.login(username,password);
        String actualMsg = loginPage.getErrorMessage();
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(driver.getTitle(), title, "Wrong title after unsuccessful login");
        Assert.assertEquals(actualMsg, expectedMsg, "Validation error message is not valid.");
    }

    @Test
    public void negativeTestEmptyFields() {
        loginPage.login(null,null);
        String expectedMsg = "Value is required and can't be empty";
        String actualMsg = loginPage.getErrorMessage();
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(actualMsg, expectedMsg, "Validation error message is not valid.");
    }

    @AfterTest
    public void afterTest() {

    }
    @AfterSuite
    public void afterSuite() {
        //close browser
        driver.quit();
    }







}
