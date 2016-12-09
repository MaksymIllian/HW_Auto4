package practice4.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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

    /**
     * 1. Create browser
     * 2. Manage browser
     * 3. Create pages
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        playersPage = new PlayersPage(driver);
        editPlayerPage = new EditPlayerPage(driver);
        loginPage = new LoginPage(driver);
    }

    /**
     * 1. Login: set user name "admin", set password "123"
     * 2. Click on insert button
     * 3. Set parameters in textboxes on EditPlayerPage from Player class with random parameters
     * 4. Click on save button
     * 5. Assert current title with expected title "Players"
     * 6. Assert current url with expected "http://80.92.229.236:81/Players"
     */
    @Test
    public void createPositiveTest() {
        loginPage.open();
        loginPage.login("admin","123");
        playersPage.insertPlayer();
        editPlayerPage.setUserName(player.getUserName());
        editPlayerPage.setPassword(player.getPassword());
        editPlayerPage.setConfirmPassword(player.getPassword());
        editPlayerPage.setEMail(player.getEmail());
        editPlayerPage.setFName(player.getfName());
        editPlayerPage.setLName(player.getlName());
        editPlayerPage.setPhone(player.getPhone());
        editPlayerPage.setCity(player.getCity());
        editPlayerPage.setAddress(player.getAddress());
        editPlayerPage.save();
        Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after create");
        Assert.assertNotEquals(driver.getCurrentUrl(), EditPlayerPage.URL, "You are still on edit page.");
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
    @Test(dependsOnMethods = { "createPositiveTest" })
    public void editPositiveTest() {
        loginPage.open();
        loginPage.login("admin","123");
        driver.findElement(By.id("723a925886__login")).clear();
        driver.findElement(By.id("723a925886__login")).sendKeys(player.getUserName());
        driver.findElement(By.name("search")).click();
        driver.findElement(By.xpath(".//td/a/img[@alt=\"Edit\"]/parent::a")).click();
        editPlayerPage.setFName(player.getlName());
        editPlayerPage.setLName(player.getfName());
        editPlayerPage.setAddress(player.getAddress());
        editPlayerPage.save();
        Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after edit");
        Assert.assertNotEquals(driver.getCurrentUrl(), EditPlayerPage.URL, "You are still on edit page.");
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
    @Test (dependsOnMethods = { "editPositiveTest" })
    public void deletePositiveTest() {
        loginPage.open();
        loginPage.login("admin","123");
        editPlayerPage.delete(player.getUserName());
        String expectedMessege = "Player has been deleted";
        String actualMessege = driver.findElement(By.xpath(".//div/ul/li[text()=\"Player has been deleted\"]")).getText();
        Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after delete");
        Assert.assertEquals(expectedMessege, actualMessege, "Player has not been deleted.");
    }


    @AfterTest
    public void afterTest() {
        //close browser
        driver.quit();
    }
}
