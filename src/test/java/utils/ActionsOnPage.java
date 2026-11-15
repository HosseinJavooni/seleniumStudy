package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static utils.Utils.getRandomNumber;
import static utils.Utils.sleep;

public class ActionsOnPage {

    public static void setAnStringToField(WebDriver driver, String elementId, String value, int index) {
        WebElement textbox = getSpecificElementById(driver, elementId, index);
//        textbox.clear();
        textbox.sendKeys(value);
        sleep(1);
    }

    public static void selectFromDropDownSearchBoxList(WebDriver driver, String elementId, int index) {
        WebElement textbox = getSpecificElementById(driver, elementId, index);
        textbox.clear();
        textbox.sendKeys(" ");
        sleep(1);
        Random random = new Random();
        for(int i=0; i < random.nextInt(100); i++) {
            textbox.sendKeys(Keys.ARROW_DOWN);
        }
        textbox.sendKeys(Keys.ENTER);
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

    public static void selectSearchableBoxItem(WebDriver driver, String elementId, int elementIdIndex, int itemIndex) {
        WebElement element = getSpecificElementById(driver, elementId, elementIdIndex);
//        WebElement dropdown = driver.findElement(By.id("country"));

        Select select = new Select(element);

// Select by visible text
//        select.selectByVisibleText("India");
//
//// OR select by value
//        select.selectByValue("IN");

// OR select by index
        select.selectByIndex(itemIndex);
    }

    public static void clickById(WebDriver driver, String id)/* throws InterruptedException*/ {

        WebElement element = getElementById(driver, id);

        if (element != null) {
            //3. Perform operation on the UI - Click in my case
            element.click();
        }
        sleep(1);
    }

    public static WebElement getElementById(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
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
        return element;
    }

    public static WebElement getSpecificElementById(WebDriver driver, String id, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//        Thread.sleep(2000);
        //1. Identify the specified flight order (First row in data table)
        By locator = By.id(id);

        List<WebElement> elementList = null;

        try {
            elementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            //2. Pass this locator to WebDriver which can help me do the operation on UI.
//            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }

        if (elementList == null || elementList.isEmpty() || index >= Objects.requireNonNull(elementList).size()) {
            return null;
        }

        return elementList.get(index);
    }

    public static void clickByText(WebDriver driver, String text){
        getSpecificElementByText(driver, text).click();
    }

    public static WebElement getSpecificElementByText(WebDriver driver, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + text + "']")));
        return  webElement;
    }

    public static void findComboBoxItems(WebDriver driver, String elementId, String itemNameContains) {
        WebElement specificElementById = getSpecificElementById(driver, elementId, 1);
        specificElementById.click();
        specificElementById.sendKeys(" ");

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        By optionLocator = By.xpath(
//                "//*[normalize-space()='" + itemNameContains + "']"
                "//ul/li" + itemNameContains + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        optionLocator
                )
        );

        option.click();

//        Thread.sleep(2000);
    }

    public static void selectDropdownItem(
            WebDriver driver,
            By searchBoxLocator,
            String expectedValue
    ) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        searchBoxLocator
                )
        );

        searchBox.click();
        searchBox.sendKeys(" ");

        By optionLocator = By.xpath(
                "//*[normalize-space(text())='"+ expectedValue + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        optionLocator
                )
        );

        option.click();
    }

    public static void selectDropdownItem(WebDriver driver, String id, String attributeName, String attributeValue) {
        WebElement element = driver.findElement(
                By.id(id)
        );
        element.clear();

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].setAttribute('"+ attributeName +"', '"+ attributeValue +"');" ,
                element
        );
    }

    public static List<String> getDropdownValue(WebDriver driver, String id) {
        WebElement searchBox = driver.findElement(By.id(id));

        searchBox.sendKeys(" ");
        sleep(5);
// Wait until dropdown items are displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul/li")
                )
        );
        List<String> itemValues = new ArrayList<>();
        for (WebElement item : items) {
            itemValues.add(item.getText());
        }
        return itemValues;
    }
}
