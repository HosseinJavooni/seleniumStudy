package seleniumTests.testCases.boEnforcementTests;

import configs.ProjectKeyValueConfigs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import static utils.ActionsOnPage.*;
import static utils.Utils.*;
import static utils.DateUtils.*;

public class CameraTestCase extends BoEnforcementBaseTestCase{
    Logger logger =  LogManager.getLogger(CameraTestCase.class);
    String createPageEndPoint = "camera-create-1";
    String searchPageEndPoint = "camera-search-0";
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver, "Create");

        String code = getRandomNumber(1,1000000);
        setAnStringToField(driver, "camTitle",  "تعریف دوربین "  + code, 0);
        selectFromDropDownSearchBoxList(driver, "cameraTypes", "کنترل سرعت", logger, 0);
        selectNextEntryInDropDownList(driver, "cameraTypes", 0);
        System.out.println(getAllMultiSelectedInputValues(driver, "cameraTypes"));
        deleteAllMultiSelectedInputValues(driver, "cameraTypes");
        System.out.println(getAllMultiSelectedInputValues(driver, "cameraTypes"));
        selectFromDropDownSearchBoxList(driver, "cameraTypes", "کنترل سرعت", logger, 0);
        selectNextEntryInDropDownList(driver, "cameraTypes", 0);
        String latitude = getRandomNumber(1, 100);
        setAnStringToField(driver, "y", latitude, 0);
        selectFromDropDownSearchBoxList(driver, "axis", " ", logger,  0);
        selectFromDropDownSearchBoxList(driver, "isInternal", " ", logger,  0);
        selectExactValueFromDropDownSearchBoxList(driver, "isActive", " ", true, logger, 0);
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
        selectFromDropDownSearchBoxList(driver, "geoUnit", " ", logger,  0);
        selectFromDropDownSearchBoxList(driver, "cameraSupportCompany", " ", logger,  0);
        String calibrationTime = getRandomNumber(50, 200);
        setAnStringToField(driver, "calibrationTime",  calibrationTime, 0);
        setAnStringToField(driver, "briefTitle",  " عنوان مختصر دوربین"  + code, 0);
        String longitude = getRandomNumber(1, 100);
        setAnStringToField(driver, "x", longitude, 0);
        System.out.println("Organization field Label -->> " + getElementById(driver, "organization" + "-label").getText());
        selectFromDropDownSearchBoxList(driver, "organization", " ", logger,  0);

        List<String> allWebElementsTextByTheSameXpath1 = getAllWebElementsTextByTheSameXpath(driver, "//*[@id=\"CameraCreateView\"]/div[1]/div[2]/div[1]/div/div/div[9]/div/div/div/div/div/fieldset/legend/span");
        System.out.println("allWebElementsTextByTheSameXpath1: --> " + allWebElementsTextByTheSameXpath1);

        Date previousYear = addToDate(new Date(), Calendar.YEAR, -1);
        String previousYearDateString = DateUtils.fromDateToPersianString(previousYear, "yyyy/MM/dd");
        setAnStringToField(driver, "installDate", previousYearDateString, 0);
        setAnStringToField(driver, "lastCalibrationDate", previousYearDateString, 0);
        clickById(driver, "Save");
        if(!checkUrlIsOk(driver, ProjectKeyValueConfigs.boEnforcementBaseUrl + searchPageEndPoint)){
            logger.info("Error:  " + "there is a problem in page \n" + ProjectKeyValueConfigs.boEnforcementBaseUrl + createPageEndPoint);
            driver.navigate().to(ProjectKeyValueConfigs.boEnforcementBaseUrl + searchPageEndPoint);
        }


    }
}
