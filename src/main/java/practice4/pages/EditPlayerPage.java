package practice4.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class EditPlayerPage {
    WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/Players";
    public EditPlayerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String userName) {
        driver.findElement(By.id("ff14642ac1c__us_login")).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(By.id("ff14642ac1c__us_password")).sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        driver.findElement(By.id("ff14642ac1c__confirm_password")).sendKeys(password);
    }

    public void setEMail(String email) {
        WebElement webElement = driver.findElement(By.id("ff14642ac1c__us_email"));
        webElement.clear();
        webElement.sendKeys(email);
    }

    public void setFName(String fName) {

        WebElement webElement = driver.findElement(By.id("ff14642ac1c__us_fname"));
        webElement.clear();
        webElement.sendKeys(fName);
    }

    public void setPhone(String phone) {
        WebElement webElement = driver.findElement(By.id("ff14642ac1c__us_phone"));
        webElement.clear();
        webElement.sendKeys(phone);

    }

    public void setLName(String lName) {

        WebElement webElement = driver.findElement(By.id("ff14642ac1c__us_lname"));
        webElement.clear();
        webElement.sendKeys(lName);
    }

    public void setCity(String city) {

        WebElement webElement = driver.findElement(By.id("ff14642ac1c__us_city"));
        webElement.clear();
        webElement.sendKeys(city);
    }

    public void setAddress(String address) {
        WebElement webElement = driver.findElement(By.id("ff14642ac1c__us_address"));
        webElement.clear();
        webElement.sendKeys(address);
    }

    public void save() {
        driver.findElement(By.name("button_save")).click();
    }

    public void search(String userName) {
        driver.findElement(By.id("723a925886__login")).clear();
        driver.findElement(By.id("723a925886__login")).sendKeys(userName);
        driver.findElement(By.name("search")).click();
    }

    public void delete(String userName) {
        driver.findElement(By.id("723a925886__login")).clear();
        driver.findElement(By.id("723a925886__login")).sendKeys(userName);
        driver.findElement(By.name("search")).click();
        driver.findElement(By.xpath(".//td/a/img[@alt=\"Delete\"]/parent::a")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }


    //TODO Implement this class

}
