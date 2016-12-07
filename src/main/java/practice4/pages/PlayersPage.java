package practice4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class PlayersPage {

    //TODO implement this page

    private WebDriver driver;

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void insertPlayer() {
        WebElement insert = driver.findElement(By.xpath(".//div/a[text()=\"Insert\"]")); // Find insert
        insert.click();
    }
}
