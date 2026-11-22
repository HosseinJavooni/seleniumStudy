package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static utils.Utils.*;

public class ActionsOnPage {

    public static void setAnStringToField(WebDriver driver, String elementId, String value, int index) {
        WebElement textbox = getSpecificElementById(driver, elementId, index);
//        textbox.clear();
        textbox.sendKeys(value);
        sleep(1);
    }

    public static void selectFromDropDownSearchBoxList(WebDriver driver, String elementId, String sendKey, int index) {
        selectExactValueFromDropDownSearchBoxList(driver, elementId, sendKey, false, index);
    }

    public static void selectExactValueFromDropDownSearchBoxList(WebDriver driver, String elementId, String sendKey, boolean isSentKeyTheWholeValue, int index) {
        WebElement textbox = openADropDownSearchBoxList(driver, elementId, sendKey, index);
        List<String> allOptions = getAllWebElementsTextByTheSameXpath(driver, "//*[contains(@id, '" + elementId + "-Option-')]");
        Random random = new Random();
        int loopCount = random.nextInt(0, allOptions.size() + 1);
        if (!sendKey.isBlank()) {
            System.out.println(allOptions);
            for (String option : allOptions) {
                if (option.contains(sendKey)) {
                    if (determineStringContainsOrEqualsToAnotherString(option, sendKey, isSentKeyTheWholeValue)) {
                        loopCount = allOptions.indexOf(sendKey);
                    }
                }
            }
            System.out.println("loopCount:   " + loopCount);

//        for(int i=0; i <= loopCount; i++) {
//            textbox.sendKeys(Keys.ARROW_DOWN);
//        }
//        textbox.sendKeys(Keys.ENTER);
        }
        sendArrowDownAndEnterKeysToWebElement(textbox, loopCount);
        sleep(1);
    }

    public static WebElement openADropDownSearchBoxList(WebDriver driver, String elementId, String sendKey, int index) {
        WebElement textbox = getSpecificElementById(driver, elementId, index);
        textbox.clear();
        textbox.sendKeys(sendKey);
        sleep(1);
        return textbox;
    }

    public static void selectNextEntryInDropDownList(WebDriver driver, String elementId, int index) {
        WebElement dropDownList = openADropDownSearchBoxList(driver, elementId,   " ", index);
        dropDownList.sendKeys(Keys.ARROW_DOWN);
        dropDownList.sendKeys(Keys.ENTER);
    }

    public static void sendArrowDownAndEnterKeysToWebElement(WebElement webElement, int arrowKeyCount) {
        for(int i=0; i <= arrowKeyCount; i++) {
            webElement.sendKeys(Keys.ARROW_DOWN);
        }
        webElement.sendKeys(Keys.ENTER);
    }

    public static Map<WebElement, List<String>> getAllItemsOfDropDownSearchBoxList(WebDriver driver, String elementId, int index) {
        WebElement textbox = openADropDownSearchBoxList(driver, elementId, " ", index);
        Map<WebElement, List<String>> map = new HashMap<>();
        map.put(textbox, getAllWebElementsTextByTheSameXpath(driver, "//*[contains(@id, '" + elementId + "-Option-')]"));
        return map;
    }

//    public static void selectADifferentItemInDropDownSearchBoxList(WebDriver driver, String elementId, String previouslySelected, int index) {
//        Map<WebElement, List<String>> webElementListMap = getAllItemsOfDropDownSearchBoxList(driver, elementId, index);
//        if(webElementListMap.isEmpty()){
//            return;
//        }
//        List<String> allItemsOfDropDownSearchBoxList = webElementListMap.values().iterator().next();
//        WebElement textbox = webElementListMap.keySet().iterator().next();
//        int firstDifferentItemIndex = 0;
//        for(String option : allItemsOfDropDownSearchBoxList){
//            if(!option.contains(previouslySelected)){
//                firstDifferentItemIndex = allItemsOfDropDownSearchBoxList.indexOf(option);
//                break;
//            }
//        }
////        WebElement textbox = getSpecificElementById(driver, elementId, index);
//        sendArrowDownAndEnterKeysToWebElement(textbox,  firstDifferentItemIndex);
//    }

    public static String getElementValue(WebDriver driver, String elementId, int index) {
        String value = driver.findElement(
                By.id(elementId)
        ).getDomProperty("value");

        System.out.println(value);
        return value;
    }

    public static List<WebElement> getAllWebElementsByTheSameXpath(WebDriver driver, String xpath) {
        List<WebElement> selectedElements = driver.findElements(
//                By.xpath("//input[@id='violationPlates']/preceding-sibling::div[contains(@class,'MuiChip-root')]//span[contains(@class,'MuiChip-label')]")
                By.xpath(xpath)
        );
        return selectedElements;
    }

    public static List<String> getAllWebElementsTextByTheSameXpath(WebDriver driver, String xpath) {
        List<WebElement> selectedElements = getAllWebElementsByTheSameXpath(driver, xpath);
        List<String> selectedElementTexts = new ArrayList<>();
        for (WebElement element : selectedElements) {
            selectedElementTexts.add(element.getText());
        }
        return selectedElementTexts;
    }

    public static List<String> getMultiValuesSelectedInputItemsById(WebDriver driver, String elementId) {
        return getAllWebElementsTextByTheSameXpath(driver, "//span[contains(@class,'MuiChip-label')]/ancestor::div[contains(@class, 'MuiButtonBase-root')]/following-sibling::input[@id='" + elementId + "']");
    }

    public static String getInputWebElementValueById(WebDriver driver, String elementId) {
        List<WebElement> selectedElements = getAllWebElementsByTheSameXpath(driver, "//input[@id='" + elementId + "']");
        if(selectedElements.isEmpty()) {
            return "";
        }
        return selectedElements.getFirst().getAttribute("value");
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

    public static void selectMuiDropdown(WebDriver driver, String elementId, int elementIdIndex, String expectedValue) {
//        WebElement element = getSpecificElementById(driver, elementId, elementIdIndex);
//        WebElement dropdown = driver.findElement(By.id("country"));

//        Select select = new Select(element);

// Select by visible text
//        select.selectByVisibleText("India");
//
//// OR select by value
//        select.selectByValue("بله");

// OR select by index
//        select.selectByIndex(itemIndex);


            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(15)
            );

            // Open dropdown
            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id(elementId)
                    )
            );

            dropdown.click();

//            String expectedValue = "بله";

            // Select required option
            List<WebElement> options = wait.until(
//                    ExpectedConditions.elementToBeClickable(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath(
                                    "//*[@role='option' and normalize-space()='"
                                            + expectedValue + "']"
                            )
                    )
            );

            options.get(elementIdIndex).click();

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

    public static boolean checkElementContainValue(WebDriver driver, String elementId, String value) {
        if(getSpecificElementById(driver, elementId, 0) == null){
            System.err.println("Element with ID: " + elementId + " is null!");
            return false;
        }
        if(!normalizePersianCharacters(getSpecificElementById(driver, elementId, 0).getText()).equals(normalizePersianCharacters(value))){
            System.err.println("Search the created record has failed");
            return false;
        } else {
            System.err.println("everything is ok");
            return true;
        }
    }

    public static void printTabTitle(String tabTitle) {
        System.out.println();
        for (int i = 0; i < tabTitle.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println(tabTitle);
        for (int i = 0; i < tabTitle.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
