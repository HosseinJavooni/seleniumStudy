package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static utils.ActionsOnPage.*;
import static utils.Utils.getRandomNumber;
import static utils.Utils.listContains;

public class ViolationTypeSearchTestCase extends BoEnforcementBaseTestCase {
    Logger logger = LogManager.getLogger(ViolationTypeSearchTestCase.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver, "Create");
        selectMuiDropdown(driver, "isMotionless", 0, "بله");
        selectMuiDropdown(driver, "isDaily", 0, "بله");
        selectMuiDropdown(driver, "isNegpoint", 0, "بله");
        selectMuiDropdown(driver, "isDangerous", 0, "بله");
        selectMuiDropdown(driver, "isCountable", 0, "بله");
        selectFromDropDownSearchBoxList(driver, "isInternal", " ", 0);
        selectFromDropDownSearchBoxList(driver, "violationPlates", " ", 0);
        selectFromDropDownSearchBoxList(driver, "violationDeliveries", " ", 0);
//        getElementValue(driver, "violationDeliveries", 0);
        List<String> allSelectedElements = getAllWebElementsTextByTheSameXpath(driver, "//input[@id='violationDeliveries']/preceding-sibling::div[contains(@class,'MuiChip-root')]//span[contains(@class,'MuiChip-label')]");
        if(!listContains(allSelectedElements, "دوربین")) {
            selectFromDropDownSearchBoxList(driver, "violationDeliveries", "دوربین", 0);
            selectFromDropDownSearchBoxList(driver, "cameraType", " ", 0);
            selectFromDropDownSearchBoxList(driver, "policeType", " ", 0);
        } else {
            selectFromDropDownSearchBoxList(driver, "cameraType", " ", 0);
        }

        selectFromDropDownSearchBoxList(driver, "vehicleUsage", " ", 0);
        Date nowDate = new Date();
        String nowDateString = DateUtils.fromDateToPersianString(nowDate, "yyyy/MM/dd");
        String nowDatePlusOneYearString = DateUtils.fromDateToPersianString(DateUtils.addToDate(nowDate, Calendar.YEAR, 1), "yyyy/MM/dd");
        setAnStringToField(driver, "startDate", nowDateString, 0);
        setAnStringToField(driver, "endDate", nowDatePlusOneYearString, 0);
//        System.out.println("now: " + now);
//        selectFromDropDownSearchBoxList(driver, "cameraType", 0);

        String code = getRandomNumber(1, 10000);
        setAnStringToField(driver, "code", code, 0);
        setAnStringToField(driver, "violationType", "نوع تخلف" + code, 0);
        setAnStringToField(driver, "briefTitle", "عنوان مختصر" + code, 0);
        clickById(driver, "Save");

        setAnStringToField(driver, "code", code, 0);
        clickById(driver, "Search");
    }
}
