package practice4.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice4.data.classes.Player;
import practice4.pages.EditPlayerPage;
import practice4.pages.LoginPage;
import practice4.pages.PlayersPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 05.12.2016.
 */
public class CRUDUsersTests {

    WebDriver driver;
    LoginPage loginPage;
    PlayersPage playersPage;
    EditPlayerPage editPlayerPage;
    Player player  = new Player();
    SoftAssert softAssert;
    /**
     * 1. Create browser
     * 2. Manage browser
     * 3. Create pages
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        playersPage = new PlayersPage(driver);
        editPlayerPage = new EditPlayerPage(driver);
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(){
        softAssert = new SoftAssert();
    }
    /**
     * 1. Login: set user name "admin", set password "123"
     * 2. Click on insert button
     * 3. Set parameters in textboxes on EditPlayerPage from Player class with random parameters
     * 4. Click on save button
     * 5. Assert current title with expected title "Players"
     * 6. Assert current url with expected "http://80.92.229.236:81/Players"
     */
    @DataProvider
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"admin", "123", player.getUserName(), player.getPassword(), player.getEmail(), player.getfName(),
                        player.getlName(), player.getPhone(), player.getCity(), player.getAddress(), "Players"}
        };
    }
    @DataProvider
    public Object[][] editPositiveData() {
        return new Object[][]{
                {"admin", "123", player.getUserName(), "Players", player.getlName(), player.getfName(), player.getAddress()}
        };
    }
    @DataProvider
    public Object[][] deletePositiveData() {
        return new Object[][]{
                {"admin", "123", player.getUserName(), "Players", "Player has been deleted"}
        };
    }

    @Test (dataProvider = "createPositiveData")
    public void createPositiveTest(String login, String password, String playerName, String playerPassword,
                                   String email, String fName, String lName, String phone, String city,
                                   String address, String title) {
        loginPage.open();
        loginPage.login(login, password);
        playersPage.insertPlayer();
        editPlayerPage.setUserName(playerName);
        editPlayerPage.setPassword(playerPassword);
        editPlayerPage.setConfirmPassword(playerPassword);
        editPlayerPage.setEMail(email);
        editPlayerPage.setFName(fName);
        editPlayerPage.setLName(lName);
        editPlayerPage.setPhone(phone);
        editPlayerPage.setCity(city);
        editPlayerPage.setAddress(address);
        editPlayerPage.save();
        softAssert.assertEquals(driver.getTitle(), title, "Wrong title after create");
        softAssert.assertNotEquals(driver.getCurrentUrl(), EditPlayerPage.URL, "You are still on edit page.");
        softAssert.assertAll();
    }
    /**
     * 1. Login: set user name "admin", set password "123"
     * 2. Set user name of created player in login textbox
     * 3. Click on search button
     * 4. Click on edit button finding player
     * 5. Set another data to texboxes
     * 6. Click on save button
     * 7. Assert current title with expected title "Players"
     * 8. Assert current url with expected "http://80.92.229.236:81/Players"
     */
    @Test(dependsOnMethods = { "createPositiveTest" }, dataProvider = "editPositiveData")
    public void editPositiveTest(String login, String password, String playerName, String title,
                                 String editFName, String editLName, String editAddress) {
      //  loginPage.open();
      //  loginPage.login(login,password);
        editPlayerPage.edit(playerName);
        editPlayerPage.setFName(editFName);
        editPlayerPage.setLName(editLName);
        editPlayerPage.setAddress(editAddress);
        editPlayerPage.save();
        softAssert.assertEquals(driver.getTitle(), title, "Wrong title after edit");
        softAssert.assertNotEquals(driver.getCurrentUrl(), EditPlayerPage.URL, "You are still on edit page.");
        softAssert.assertAll();
    }
    /**
     * 1. Login: set user name "admin", set password "123"
     * 2. Set user name of created player in login textbox
     * 3. Click on search button
     * 4. Click on delete button finding player
     * 6. Click on accept
     * 7. Assert current title with expected title "Players"
     * 8. Assert current url with expected "http://80.92.229.236:81/Players"
     */
    @Test (dependsOnMethods = { "editPositiveTest" }, dataProvider = "deletePositiveData")
    public void deletePositiveTest(String login, String password, String playerName, String title, String expectedMsg) {
     //   loginPage.open();
     //   loginPage.login(login, password);
        editPlayerPage.delete(playerName);
        String actualMsg = editPlayerPage.getActualMsg();
        softAssert.assertEquals(driver.getTitle(), title, "Wrong title after delete");
        softAssert.assertEquals(actualMsg, expectedMsg ,"Player has not been deleted.");
        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() {
        //close browser
        driver.quit();
    }
}
