package gettingStarted;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static utils.ActionsOnPage.*;

public class FlightOrderSeleniumTestCase {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriverService service =
                new ChromeDriverService.Builder()
                        .usingDriverExecutable(
                                Paths.get("drivers","chromedriver-linux64", "chromedriver").toFile())
                        .build();
        WebDriver driver = new ChromeDriver(service);
        driver.navigate().to("http://127.0.0.1:8081/#/flight-order-dispatch-cartable-0");

        driver.manage().window().maximize();

        //Click to the specified flight order (First row in data table)
        clickById(driver, "DataTable-1-6");

        clickById(driver, "signDelivery");


        // Enter space in the search box
        WebElement searchBox = driver.findElement(By.id("flight-audit-legs-2-3"));

        searchBox.sendKeys(" ");

// Wait until dropdown items are displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul/li")
                )
        );
        items.getFirst().sendKeys(items.getFirst().getText());

// Print all items
        for (WebElement item : items) {
//            if(item.isDisplayed()){
//                item.getText().contains("پیمانکاران");
//                item.click();
//                break;
//            }
            System.out.println(item.getText());
//            item.sendKeys(Keys.ENTER);
        }



//        clickById(driver, "flight-audit-crews-3-7");

//        clickById(driver, "flight-audit-crews-3-5");

//        selectComboBoxItem(driver, "flight-audit-crews-3-5", 1);
        // Click the combo box to display the list
        clickById(driver, "flight-audit-crews-3-7");
        selectComboBoxItem(driver, "flight-audit-crews-3-5", "(D)");
        setAnStringToField(driver, "flight-audit-crews-3-6", "0");
        clickById(driver, "flight-audit-crews-3-7");

        clickById(driver, "flight-audit-crews-2-7");
        selectComboBoxItem(driver, "flight-audit-crews-2-5", "(D)");
        setAnStringToField(driver, "flight-audit-crews-2-6", "0");
        clickById(driver, "flight-audit-crews-2-7");

        clickById(driver, "flight-audit-crews-1-7");
        selectComboBoxItem(driver, "flight-audit-crews-1-5", "(D)");
        setAnStringToField(driver, "flight-audit-crews-1-6", "0");
        clickById(driver, "flight-audit-crews-1-7");
        //
//        Thread.sleep(5000);
//        driver.close();


    }


}
