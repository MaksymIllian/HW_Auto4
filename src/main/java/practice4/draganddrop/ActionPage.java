package practice4.draganddrop;

import com.sun.javafx.scene.layout.region.Margins;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Serhii on 07-Dec-16.
 */
public class ActionPage {

    @FindBy(xpath = ".//ul[@id='sortable']/li[1]")
    private WebElement sourceElement;

    @FindBy(xpath = ".//ul[@id='sortable']/li")
    private List<WebElement> sortableElementList;

    @FindBy(id = "drop")
    private WebElement targetElement;

    private WebDriver driver;
    private final static String Path = new File("").getAbsolutePath() + "\\src\\main\\drag_and_drop2\\drag_and_drop2\\drag_and_drop2\\index.html";
    public ActionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(Path);
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
    public int sum() {
        int sum = 0;
        for (WebElement webElement : sortableElementList)
             sum += Integer.parseInt(webElement.getText());
        return sum;
    }
    public void sort() {
        for(int i = sortableElementList.size()-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            /*Сравниваем элементы попарно,
              если они имеют неправильный порядок,
              то меняем местами*/
                if( Integer.parseInt(sortableElementList.get(j).getText()) > Integer.parseInt(sortableElementList.get(j+1).getText()) ){
                    dragAndDrop(sortableElementList.get(j+1),sortableElementList.get(j));
                }
            }
        }

    }
    public void reverse() {
        for(int i = sortableElementList.size()-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            /*Сравниваем элементы попарно,
              если они имеют неправильный порядок,
              то меняем местами*/
                if( Integer.parseInt(sortableElementList.get(j).getText()) < Integer.parseInt(sortableElementList.get(j+1).getText()) ){
                    dragAndDrop(sortableElementList.get(j+1),sortableElementList.get(j));
                }
            }
        }

    }
    public void dragAndDrop() {
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceElement, targetElement)
                .perform();
    }
    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceElement, targetElement)
                .perform();
    }
    public int[] sortableElementListToIntegerList() {
        int[] res = new int[7];
        for (int i = 0;i<sortableElementList.size();i++)
            res[i] = Integer.parseInt(sortableElementList.get(i).getText());
        return res;
    }
    public void switchToMainPage() {
        driver.switchTo().defaultContent();
    }
}
