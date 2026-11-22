package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static utils.ActionsOnPage.*;
import static utils.Utils.*;
import static utils.DateUtils.*;

public class CameraTestCase extends BoEnforcementBaseTestCase{
    Logger logger =  LogManager.getLogger(CameraTestCase.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver, "Create");
        String code = getRandomNumber(1,1000000);
        setAnStringToField(driver, "camTitle",  "تعریف دوربین "  + code, 0);
        selectFromDropDownSearchBoxList(driver, "cameraTypes", "کنترل سرعت", 0);
//        List<String> cameraTypes = getAllItemsOfDropDownSearchBoxList(driver, "cameraTypes", 0);
//        System.out.println("cameraTypes: " + cameraTypes);
        selectNextEntryInDropDownList(driver, "cameraTypes", 0);
        List<String> allWebElementsTextByTheSameXpath = getMultiValuesSelectedInputItemsById(driver, "cameraTypes");
        System.out.println(allWebElementsTextByTheSameXpath);
        String latitude = getRandomNumber(1, 100);
        setAnStringToField(driver, "y", latitude, 0);
        selectFromDropDownSearchBoxList(driver, "axis", " ", 0);
        selectFromDropDownSearchBoxList(driver, "isInternal", " ", 0);
        selectExactValueFromDropDownSearchBoxList(driver, "isActive", " ", true, 0);
        String isActiveElementValue = getInputWebElementValueById(driver, "isActive");
        if(isActiveElementValue.equals("غیر فعال")) {
            setAnStringToField(driver, "inactivationReason", "inactivation reason " + code, 0);
            Date yesterday = addToDate(new Date(), Calendar.DAY_OF_WEEK, -1);
            String ysterdayDateString = DateUtils.fromDateToPersianString(yesterday, "yyyy/MM/dd");
            setAnStringToField(driver, "inactiveDate", ysterdayDateString, 0);
        } else if(isActiveElementValue.equals("فعال")){
            Date yesterday = addToDate(new Date(), Calendar.DAY_OF_WEEK, -1);
            String ysterdayDateString = DateUtils.fromDateToPersianString(yesterday, "yyyy/MM/dd");
            setAnStringToField(driver, "activeDate", ysterdayDateString, 0);
        }
        setAnStringToField(driver, "cameraCode",  code, 0);
        String allowedSpeedRange = getRandomNumber(30, 120);
        setAnStringToField(driver, "legalSpeed",  allowedSpeedRange, 0);
        selectFromDropDownSearchBoxList(driver, "geoUnit", " ", 0);
        selectFromDropDownSearchBoxList(driver, "cameraSupportCompany", " ", 0);
        String calibrationTime = getRandomNumber(50, 200);
        setAnStringToField(driver, "calibrationTime",  calibrationTime, 0);
        setAnStringToField(driver, "briefTitle",  " عنوان مختصر دوربین"  + code, 0);
        String longitude = getRandomNumber(1, 100);
        setAnStringToField(driver, "x", longitude, 0);
        selectFromDropDownSearchBoxList(driver, "organization", " ", 0);

        Date previousYear = addToDate(new Date(), Calendar.YEAR, -1);
        String previousYearDateString = DateUtils.fromDateToPersianString(previousYear, "yyyy/MM/dd");
        setAnStringToField(driver, "installDate", previousYearDateString, 0);
        setAnStringToField(driver, "lastCalibrationDate", previousYearDateString, 0);
        clickById(driver, "Save");


    }
}
