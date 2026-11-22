package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

import static utils.ActionsOnPage.*;
import static utils.Utils.getRandomNumber;

public class ViolationRule extends BoEnforcementBaseTestCase{
    Logger logger = LogManager.getLogger(ViolationRule.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver,"Create");
        String code = getRandomNumber(1, 1000000);
        setAnStringToField(driver, "title", "عنوان تستی" + code, 0);
        Date nowDate = new Date();
        String nowDateString = DateUtils.fromDateToPersianString(nowDate, "yyyy/MM/dd");
        String nowDatePlusOneYearString = DateUtils.fromDateToPersianString(DateUtils.addToDate(nowDate, Calendar.YEAR, 1), "yyyy/MM/dd");
        setAnStringToField(driver, "startDate", nowDateString, 0);
        setAnStringToField(driver, "endDate", nowDatePlusOneYearString, 0);
        selectFromDropDownSearchBoxList(driver, "violationType", " ", 0);
        selectFromDropDownSearchBoxList(driver, "axis", " ", 0);
        selectFromDropDownSearchBoxList(driver, "cameraType", " ", 0);
        selectFromDropDownSearchBoxList(driver, "geoUnit", " ", 0);
        selectFromDropDownSearchBoxList(driver, "camera", " ", 0);
        selectFromDropDownSearchBoxList(driver, "organization", " ", 0);
        clickById(driver, "Save");

        setAnStringToField(driver, "title", "عنوان تستی" + code, 0);
        clickById(driver, "Search");
    }
}
