package utils;

import org.apache.logging.log4j.Logger;
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
        if (textbox != null) {
            textbox.clear();
            textbox.sendKeys(value);
        }
    }

    public static void clearATextField(WebDriver driver, String elementId, int index) {
        WebElement textbox = getSpecificElementById(driver, elementId, index);
        if (textbox != null) {
            textbox.clear();
            String value = getTheValueOfATextField(driver, elementId, index);
            if (value != null) {
                for (int i = 0; i < value.length(); i++) {
                    textbox.sendKeys(Keys.BACK_SPACE);
                }
            }
        }
    }

    public static String getTheValueOfATextField(WebDriver driver, String elementId, int index) {
        WebElement textbox = getSpecificElementById(driver, elementId, index);
        if (textbox != null) {
            String value = textbox.getAttribute("value");
            return (value == null || value.isBlank()) ? textbox.getText() : textbox.getAttribute("value");
        }
        return null;
    }

    public static void selectFromDropDownSearchBoxListWithTestComponents(WebDriver driver, String elementId, String sendKey, Logger logger, int index) {
        selectFromDropDownSearchBoxList(driver, elementId, " ", logger,  0);
        deleteDropDownSearchSelectedInputValue(driver, elementId);
        selectFromDropDownSearchBoxList(driver, elementId, sendKey.isBlank() ? "  " : sendKey, logger,  0);
    }

    public static String getElementErrorMessage(WebDriver driver, String elementId, int index) {
        try {
            WebElement element = getSpecificElementById(driver, elementId + "-helper-text", index);
            if (element != null) {
                return element.getText();
            }
        } catch (NoSuchElementException| TimeoutException e) {
            return "";
        }
        return "";
    }

    public static String getInputElementTitle(WebDriver driver, String elementId, int index) {
        List<WebElement> elements =
                getAllWebElementsByTheSameXpath(driver, "//input[@id='" + elementId + "']/following-sibling::fieldset/legend/span");
        if (!elements.isEmpty() && elements.getFirst().getAttribute("outerHTML") != null) {
            return elements.getFirst().getAttribute("outerHTML").replace("<span>", "").replace("</span>", "").replace("&nbsp;", " ");
        }
        return null;
    }

    public static List<String> getAllPageElementsTitle(WebDriver driver){
        List<WebElement> elements =
                getAllWebElementsByTheSameXpath(driver, "//fieldset/legend/span");
        List<String> pageElementsTitle = new ArrayList<>();
        for (WebElement element : elements) {
            if (element != null && element.getAttribute("outerHTML") != null) {
                pageElementsTitle.add(element.getAttribute("outerHTML").replace("<span>", "").replace("</span>", "").replace("&nbsp;", " "));
            }
        }
        return pageElementsTitle;
    }

    public static List<String> getAllPageElementsMessages(WebDriver driver){
        List<WebElement> elements =
                getAllWebElementsByTheSameXpath(driver, "//*[contains(@id, '-helper-text')]");
//        WebElement element = getSpecificElementById(driver, elementId + "-helper-text", index)
        List<String> pageElementsMessages = new ArrayList<>();
        for (WebElement element : elements) {
            if (element != null) {
                pageElementsMessages.add("(" + element.getText() + ")");
            }
        }
        return pageElementsMessages;
    }

    public static void selectFromDropDownSearchBoxList(WebDriver driver, String elementId, String sendKey, Logger logger, int index) {
        selectExactValueFromDropDownSearchBoxList(driver, elementId, sendKey, false, logger, index);
    }

    public static void selectExactValueFromDropDownSearchBoxList(WebDriver driver, String elementId, String sendKey, boolean isSentKeyTheWholeValue, Logger logger, int index) {
//        String elementTitle = getElementById(driver, elementId + "-label").getText();
//        logger.info("Element title: " + elementTitle);
        WebElement textbox = openADropDownSearchBoxList(driver, elementId, sendKey, index);
        List<String> allOptions = getAllWebElementsTextByTheSameXpath(driver, "//*[contains(@id, '" + elementId + "-Option-')]");
        Random random = new Random();
        int loopCount = random.nextInt(0, allOptions.isEmpty() ? 1 : allOptions.size());
        if (!sendKey.isBlank()) {
            System.out.println(allOptions);
            for (String option : allOptions) {
                if (option.contains(sendKey)) {
                    if (determineStringContainsOrEqualsToAnotherString(option, sendKey, isSentKeyTheWholeValue)) {
                        loopCount = allOptions.indexOf(sendKey);
                    }
                }
            }
//            System.out.println("loopCount:   " + loopCount);

//        for(int i=0; i <= loopCount; i++) {
//            textbox.sendKeys(Keys.ARROW_DOWN);
//        }
//        textbox.sendKeys(Keys.ENTER);
        }
        sendArrowDownAndEnterKeysToWebElement(textbox, loopCount);
//        if(!getInputWebElementValueById(driver, elementId).isBlank()) {
//            logger.info("The element [" +  elementTitle + "] is filled with: \n " + getInputWebElementValueById(driver, elementId));
//        } else {
//            if(elementTitle.contains("*")) {
//                logger.error("The element [" +  elementTitle + "] is mandatory!");
//            }
//        }
//        sleep(1);
    }

    public static WebElement openADropDownSearchBoxList(WebDriver driver, String elementId, String sendKey, int index) {
        WebElement element = getSpecificElementById(driver, elementId, index);
        element.clear();
        element.sendKeys(sendKey);
        sleep(1);
        return element;
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

    public static boolean checkUrlIsOk(WebDriver driver, String url) {
        if(driver == null || url.isEmpty()){
            return false;
        }
        return driver.getCurrentUrl().equals(url);
    }

    public static boolean isFilled(WebDriver driver, String elementId) {
        return getInputWebElementValueById(driver, elementId).isEmpty();
    }

    public static void deleteAllMultiSelectedInputValues(WebDriver driver, String elementId) {
        List<WebElement> elements = getAllWebElementsByTheSameXpath(driver, "//div[contains(@class, 'MuiAutocomplete-root')]/descendant::input[@id='" + elementId + "']/preceding-sibling::div");
        boolean criteria = checkMultiSelectInputContainsValues(driver, elementId);
        while (criteria) {
            for (WebElement element : elements) {
                List<WebElement> children = element.findElements(By.tagName("svg"));
                if (!children.isEmpty()) {
//                    sleep(1);
                    children.get(0).click();
                    break;
                }
            }
            criteria = checkMultiSelectInputContainsValues(driver, elementId);
        }
    }

    public static String getDropDownSearchSelectedInputValue(WebDriver driver, String elementId) {
        List<WebElement> elements = getAllWebElementsByTheSameXpath(driver, "//div[contains(@class, 'MuiAutocomplete-root')]/descendant::input[@id='" + elementId + "']");
        if (!elements.isEmpty()) {
            return elements.getFirst().getAttribute("value");
        }
        return "";
    }

    public static void deleteDropDownSearchSelectedInputValue(WebDriver driver, String elementId) {
        List<WebElement> elements = getAllWebElementsByTheSameXpath(driver, "//div[contains(@class, 'MuiAutocomplete-root')]/descendant::input[@id='" + elementId + "']/following-sibling::div/button");
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }

    public static List<String> getAllMultiSelectedInputValues(WebDriver driver, String elementId) {
        List<WebElement> elements = getAllWebElementsByTheSameXpath(driver, "//div[contains(@class, 'MuiAutocomplete-root')]/descendant::input[@id='" + elementId + "']/preceding-sibling::div");
        List<String> result = new ArrayList<>();
        for (WebElement element : elements) {
            List<WebElement> children = element.findElements(By.tagName("span"));
            if (!children.isEmpty()) {
                if(!children.get(0).getText().isBlank()) {
                    result.add(children.get(0).getText());
                }
            }
        }
        return result;
    }

    public static boolean checkMultiSelectInputContainsValues(WebDriver driver, String elementId) {
        List<WebElement> elements = getAllWebElementsByTheSameXpath(driver, "//div[contains(@class, 'MuiAutocomplete-root')]/descendant::input[@id='" + elementId + "']/preceding-sibling::div");
        boolean criteria = false;
        if (!elements.isEmpty()) {
            for (WebElement element : elements) {
                List<WebElement> children = element.findElements(By.tagName("svg"));
                if (!children.isEmpty()) {
                    criteria = true;
                    break;
                }
            }
        }
        return criteria;
    }

}
