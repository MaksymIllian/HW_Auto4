package practice4.draganddrop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice4.draganddrop.ActionPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Serhii on 07-Dec-16.
 */
public class ActionTest {

    private WebDriver driver;
    private ActionPage actionPage;
    private SoftAssert softAssert;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actionPage = new ActionPage(driver);
        softAssert = new SoftAssert();
    }
    /**
     * 1. Open page.
     * 2. Check sum of elements.
     * 3. Perform drag and drop.
     * 4. Click cancel.
     * 5. Check element to delete.
     * 6. Perform drag and drop.
     * 7. Click accept.
     * 8. Check element to delete.
     */
    @Test
    public void dragAndDropTest() {
        actionPage.open();
        int sum = actionPage.sum();
        actionPage.dragAndDrop();
        actionPage.cancelAlert();
        softAssert.assertEquals(sum, actionPage.sum() ,"Error: Element is deleted!");
        actionPage.dragAndDrop();
        actionPage.acceptAlert();
        softAssert.assertNotEquals(sum, actionPage.sum() ,"Error: Element is not deleted!");
        //actionPage.switchToMainPage();
        softAssert.assertAll();
    }
    @Test
    public void sortTest() {
        actionPage.open();
        actionPage.sort();
        int[] expected  = new int[]{1,2,3,4,5,6,7};
        softAssert.assertEquals(actionPage.sortableElementListToIntegerList(),expected,"Sort is not correct!");
        actionPage.reverse();
        expected  = new int[]{7,6,5,4,3,2,1};
        softAssert.assertEquals(actionPage.sortableElementListToIntegerList(),expected,"Reverse is not correct!");
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
