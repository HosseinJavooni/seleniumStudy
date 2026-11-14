package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionsOnPage {

    public static void setAnStringToField(WebDriver driver, String elementId, String value) {
        WebElement textbox = driver.findElement(By.id(elementId));
//        textbox.clear();
        textbox.sendKeys(value);
    }

    public static void selectComboBoxItem(WebDriver driver, String elementId, String itemNameContains) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("flight-audit-crews-3-5")).click();

        // Get all list items
        List<WebElement> items = driver.findElements(By.xpath("//ul/li"));

        // Select the required item
        for (WebElement item : items) {
            if (item.getText().contains(itemNameContains)) {
                item.click();
                break;
            }
        }
    }

    public static void clickById(WebDriver driver, String id)/* throws InterruptedException*/ {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        Thread.sleep(2000);
        //1. Identify the specified flight order (First row in data table)
        By locator = By.id(id);

        WebElement element = null;

        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            //2. Pass this locator to WebDriver which can help me do the operation on UI.
//            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }

        if (element != null) {
            //3. Perform operation on the UI - Click in my case
            element.click();
        }
    }

}
