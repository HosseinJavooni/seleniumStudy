package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static utils.ActionsOnPage.*;
import static utils.Utils.getFirstPartAfterSplitString;

public class OppositeViolationTestCase extends BoEnforcementBaseTestCase {
    Logger logger =  LogManager.getLogger(OppositeViolationTestCase.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = giveTheDriver(logger, endPoint);
        clickById(driver, "Create");
        selectFromDropDownSearchBoxList(driver, "violationType", " ", 1);
        String violationTypeText = getSpecificElementById(driver, "violationType", 1).getAttribute("value");
        selectFromDropDownSearchBoxList(driver, "violationType2Set", " ", 0);
        List<String> violationType2SetTextList = getAllSelectedElementsText(driver, "//span[contains(@class,'MuiChip-label')]/ancestor::div[contains(@class, 'MuiButtonBase-root')]");
        Date nowDate = new Date();
        String nowDateString = DateUtils.fromDateToPersianString(nowDate, "yyyy/MM/dd");
        String nowDatePlusOneYearString = DateUtils.fromDateToPersianString(DateUtils.addToDate(nowDate, Calendar.YEAR, 1), "yyyy/MM/dd");
        setAnStringToField(driver, "fromDate", nowDateString, 0);
        setAnStringToField(driver, "toDate", nowDatePlusOneYearString, 0);
        clickById(driver, "Save");

//        selectFromDropDownSearchBoxList(driver, "violationType", violationTypeText.substring(0, violationTypeText.indexOf(":")), 0);

        selectFromDropDownSearchBoxList(driver, "violationType", getFirstPartAfterSplitString(violationTypeText, ":"), 0);
        System.out.println("violationTypeText: (at search)   " + violationTypeText);
        System.out.println("getFirstPartAfterSplitString(violationTypeText, \":\"): (at search)   " + getFirstPartAfterSplitString(violationTypeText, ":"));
//        selectFromDropDownSearchBoxList(driver, "violationType2", violationType2SetTextList.getFirst().substring(0, violationTypeText.indexOf(":")), 0);
        selectFromDropDownSearchBoxList(driver, "violationType2", getFirstPartAfterSplitString(violationType2SetTextList.getFirst(), ":"), 0);
        System.out.println("violationTypeText: (at search)   " + violationType2SetTextList.getFirst());
        System.out.println("getFirstPartAfterSplitString(violationType2SetTextList.getFirst(), \":\"): (at search)   " + getFirstPartAfterSplitString(violationType2SetTextList.getFirst(), ":"));
        clickById(driver, "Search");
    }
}
