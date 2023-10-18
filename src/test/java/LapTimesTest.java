import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LapTimes;

public class LapTimesTest {
    WebDriver driver;
    LapTimes laptimes;

    @BeforeTest
    public void beforeEveryMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/laptimes");
    }

    @Test(priority = 1)
    public void validateHeading() {
        laptimes = new LapTimes(driver);
        laptimes.validateHeading();
    }

    @Test(priority = 2)
    public void validateAddNewLaptimeButtonIsVisible() {
        laptimes = new LapTimes(driver);
        laptimes.validateAddNewLaptimeButtonIsVisible();
    }

    @Test(priority = 3)
    public void validateTextAddNewLaptimeButton() {
        laptimes = new LapTimes(driver);
        laptimes.validateTextAddNewLaptimeButton();
    }

    @Test(priority = 4)
    public void validUserNavigateToAddLapTimePage() throws InterruptedException {
        laptimes = new LapTimes(driver);
        laptimes.validUserNavigateToAddLapTimePage();
    }

    @Test(priority = 5)
    public void validateSaveButtonIsVisible() {
        laptimes = new LapTimes(driver);
        laptimes.validateSaveButtonIsVisible();
    }

    @Test(priority = 6)
    public void validateUserIsAbleToSaveLapTimes() {
        laptimes = new LapTimes(driver);
        laptimes.inputLapsTime("1").clickSaveButton();
    }

    @Test(priority = 7)
    public void validateUserIsAbleToDeleteLapTimes() throws InterruptedException {
        laptimes = new LapTimes(driver);
        laptimes.validateUserIsAbleToDeleteLapTimes();
        Thread.sleep(2000);
    }

    @Test(priority = 8)
    public void validateIfListIsInAscendingOrder() throws InterruptedException {
        laptimes = new LapTimes(driver);
        driver.get("http://localhost:8080/laptimes");
        laptimes.validUserNavigateToAddLapTimePage()
                .inputLapsTime("6400")
                .clickSaveButton();
        driver.navigate().back();
        laptimes
                .inputLapsTime("2300")
                .clickSaveButton();
        driver.navigate().back();
        laptimes
                .inputLapsTime("8020")
                .clickSaveButton();
        driver.navigate().back();
        laptimes
                .inputLapsTime("200")
                .clickSaveButton();
        driver.navigate().back();
        laptimes
                .inputLapsTime("1000")
                .clickSaveButton();
        driver.navigate().back();
        laptimes
                .inputLapsTime("0")
                .clickSaveButton()
                .validateIfListIsInAscendingOrder();
    }

    @Test(priority = 9)
    public void validateOnlyFiveLapTimesAreDisplayed() throws InterruptedException {
        laptimes = new LapTimes(driver);
        laptimes.validateOnlyFiveLapTimesAreDisplayed();
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}



