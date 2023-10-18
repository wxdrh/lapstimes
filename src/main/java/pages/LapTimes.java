package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LapTimes {
    WebDriver driver;
    WebDriverWait wait;

    @CacheLookup
    @FindBy(how = How.XPATH, using = ("//h1"))
    public WebElement heading;
    @CacheLookup
    @FindBy(how = How.XPATH, using = ("//a[@id='addtodo-button']"))
    public WebElement addNewLapTimeButton;
    @FindBy(how = How.XPATH, using = ("//h1"))
    public WebElement lapTimeFormHeading;
    @CacheLookup
    @FindBy(how = How.XPATH, using = ("//input[@id='save-button']"))
    public WebElement saveButton;
    @CacheLookup
    @FindBy(how = How.XPATH, using = ("//input[@id='input-laptime']"))
    public WebElement timeInputField;

    @CacheLookup
    @FindBy(how = How.XPATH, using = ("//*[@class='lapTime-list']/h3[contains(text(),'1')]/../button"))
    public WebElement deleteButton;

    @CacheLookup
    @FindBy(how = How.XPATH, using = ("//div[@class='lapTime-list']//h3"))
    public List<WebElement> listLapTime;


    public LapTimes(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void validateHeading(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(heading));
        Assert.assertEquals(heading.getText(), "Dina varvtider");
    }

    public void validateAddNewLaptimeButtonIsVisible(){
        Assert.assertTrue(addNewLapTimeButton.isDisplayed());
    }
    public void validateTextAddNewLaptimeButton(){
           Assert.assertEquals(addNewLapTimeButton.getText(), "Add a new laptime");
    }

    public LapTimes validUserNavigateToAddLapTimePage() {
        addNewLapTimeButton.click();
        Assert.assertEquals(lapTimeFormHeading.getText(),"Lägg till ny varvtid!");
        return this;
    }

    public LapTimes inputLapsTime(String lapTime) {
        timeInputField.clear();
        timeInputField.sendKeys(lapTime);
        return this;
    }

    public LapTimes clickSaveButton(){
        saveButton.click();
        return this;
    }

    public void validateUserIsAbleToDeleteLapTimes() {
        deleteButton.click();
    }

    public void validateOnlyFiveLapTimesAreDisplayed() {
        Assert.assertEquals(listLapTime.size(),5);
    }

    public void validateIfListIsInAscendingOrder(){
        List<String> textList = new ArrayList<>();
        for (WebElement element : listLapTime) {
            // Get the text from each element and add it to the list
            String text = element.getText().replaceAll("^(?![0-9]+$).*","");
            textList.add(text);
        }

        // Check if the text values are in ascending order
        boolean isAscending = isListInAscendingOrder(textList);

        Assert.assertTrue(isAscending);
    }

    public void validateSaveButtonIsVisible() {
        Assert.assertTrue(saveButton.isDisplayed());
    }

    public static boolean isListInAscendingOrder(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

}

