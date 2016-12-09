package practice4.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class EditPlayerPage {
    @FindBy (id = "ff14642ac1c__us_login")
    private WebElement usernameElement;

    @FindBy (id = "ff14642ac1c__us_password")
    private WebElement passwordElement;

    @FindBy (id = "ff14642ac1c__us_email")
    private WebElement emailElement;

    @FindBy (id = "ff14642ac1c__us_lname")
    private WebElement lNameElement;

    @FindBy (id = "ff14642ac1c__us_fname")
    private WebElement fNameElement;

    @FindBy (id = "ff14642ac1c__us_phone")
    private WebElement phoneElement;

    @FindBy (id = "ff14642ac1c__us_city")
    private WebElement cityElement;

    @FindBy (id = "ff14642ac1c__us_address")
    private WebElement addressElement;

    @FindBy (name = "button_save")
    private WebElement saveButton;

    @FindBy (id = "723a925886__login")
    private WebElement loginTextBox;

    @FindBy (name = "search")
    private WebElement searchButton;

    @FindBy (xpath = ".//td/a/img[@alt=\"Delete\"]/parent::a")
    private WebElement deleteButton;
    WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/Players";
    public EditPlayerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String userName) {
        usernameElement.sendKeys(userName);
    }

    public void setPassword(String password) {
        passwordElement.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        passwordElement.sendKeys(password);
    }

    public void setEMail(String email) {
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void setFName(String fName) {

        fNameElement.clear();
        fNameElement.sendKeys(fName);
    }

    public void setPhone(String phone) {
        phoneElement.clear();
        phoneElement.sendKeys(phone);

    }

    public void setLName(String lName) {

        lNameElement.clear();
        lNameElement.sendKeys(lName);
    }

    public void setCity(String city) {

        cityElement.clear();
        cityElement.sendKeys(city);
    }

    public void setAddress(String address) {
        addressElement.clear();
        addressElement.sendKeys(address);
    }

    public void save() {
        saveButton.click();
    }

    public void search(String userName) {
        loginTextBox.clear();
        loginTextBox.sendKeys(userName);
        searchButton.click();
    }

    public void delete(String userName) {
        search(userName);
        deleteButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }


    //TODO Implement this class

}
