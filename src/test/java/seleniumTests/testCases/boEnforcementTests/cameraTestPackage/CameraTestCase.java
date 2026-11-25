package seleniumTests.testCases.boEnforcementTests.cameraTestPackage;

import TestComponents.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import seleniumTests.testCases.boEnforcementTests.BoEnforcementBaseTestCase;
import utils.DateUtils;

import java.util.*;

import static utils.ActionsOnPage.*;
import static utils.DateUtils.addToDate;
import static utils.Utils.*;

public class CameraTestCase extends BoEnforcementBaseTestCase {
    Logger logger =  LogManager.getLogger(CameraTestCase.class);
    String createPageEndPoint = "camera-create-1";
    String searchPageEndPoint = "camera-search-0";
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);
        List<BaseInputComponentTest> componentList = new ArrayList<>();

        clickById(driver, "Create");

        String code = getRandomNumber(1,1000000);
//        setAnStringToField(driver, "camTitle",  "تعریف دوربین "  + code, 0);
//        selectFromDropDownSearchBoxList(driver, "cameraTypes", "کنترل سرعت", logger, 0);

        componentList.add(new TextFieldInputComponentTest(driver, "camTitle", logger, 0,"تعریف دوربین "  + code).doAction());
        componentList.add(new MultiSelectSearchBoxInputComponentTest(driver, "cameraTypes", logger, 0, "کنترل سرعت").doAction());
//        componentList.add(new SelectSearchBoxInputComponentTest(driver, "isInternal", logger, 0, " "));
        String latitude = getRandomNumber(1, 100);
        componentList.add(new TextFieldInputComponentTest(driver, "y", logger, 0, latitude).doAction());
        componentList.add(new SelectSearchBoxInputComponentTest(driver, "axis", logger, 0, " ").doAction());
        componentList.add(new SelectSearchBoxInputComponentTest(driver, "isInternal", logger, 0, " ").doAction());
        BaseInputComponentTest isActiveComponent = new SelectSearchBoxInputComponentTest(driver, "isActive", logger, 0, " ").doAction();
        componentList.add(isActiveComponent);
        if(checkIsActiveComponentValue(isActiveComponent, "غیر فعال")){
            componentList.add(new TextFieldInputComponentTest(driver, "inactivationReason", logger, 0, "دلیل غیر فعال شدن inaction reason" + code).doAction());
            String ysterdayDateString = getYesterdayStringValue();
            componentList.add(new TextFieldInputComponentTest(driver, "inactiveDate", logger, 0, ysterdayDateString).doAction());
        } else if(checkIsActiveComponentValue(isActiveComponent, "فعال")){
            String ysterdayDateString = getYesterdayStringValue();
            componentList.add(new TextFieldInputComponentTest(driver, "activeDate", logger, 0, ysterdayDateString).doAction());
        }
        componentList.add(new TextFieldInputComponentTest(driver, "cameraCode", logger, 0, code).doAction());
        componentList.add(new TextFieldInputComponentTest(driver, "legalSpeed", logger, 0, getRandomNumber(30, 120)).doAction());
        componentList.add(new SelectSearchBoxInputComponentTest(driver, "geoUnit", logger, 0, " ").doAction());
        componentList.add(new SelectSearchBoxInputComponentTest(driver, "cameraSupportCompany", logger, 0, " ").doAction());
        componentList.add(new TextFieldInputComponentTest(driver, "calibrationTime", logger, 0, getRandomNumber(50, 200)).doAction());
        componentList.add(new TextFieldInputComponentTest(driver, "briefTitle", logger, 0, " عنوان مختصر دوربین"  + code).doAction());
        componentList.add(new TextFieldInputComponentTest(driver, "x", logger, 0, getRandomNumber(1, 100)).doAction());
        componentList.add(new SelectSearchBoxInputComponentTest(driver, "organization", logger, 0, " ").doAction());
        componentList.add(new TextFieldInputComponentTest(driver, "installDate", logger, 0, getExactOneYearsBeforeTodayDateString()).doAction());




//        clickById(driver, "Save");
//        if(!checkUrlIsOk(driver, ProjectKeyValueConfigs.boEnforcementBaseUrl + searchPageEndPoint)){
//            logger.info("Error:  " + "there is a problem in page \n" + ProjectKeyValueConfigs.boEnforcementBaseUrl + createPageEndPoint);
//            driver.navigate().to(ProjectKeyValueConfigs.boEnforcementBaseUrl + searchPageEndPoint);
//        }
        clickById(driver, "Save");
        for (BaseInputComponentTest component : componentList) {
//            component.doAction();
//            System.out.println("$$ ComponentTitle:  " + component.getComponentTitle());
//            System.out.println("$$ ComponentValues:  " + component.getComponentContent());
//            System.out.println("$$ Message:  " + component.getComponentMessage());
            if(!component.getComponentMessage().isBlank()) {
                logger.warn("THERE IS A MESSAGE IN THE PAGE: " + component.getComponentMessage());
                component.recordTheComponentReport();
            }
        }
        System.out.println("$$ Elements Title:  " + getAllPageElementsTitle(driver));
        System.out.println("$$ Elements Messages:  " + getAllPageElementsMessages(driver));
    }

    private String getYesterdayStringValue() {
        Date yesterday = addToDate(new Date(), Calendar.DAY_OF_WEEK, -1);
        return DateUtils.fromDateToPersianString(yesterday, "yyyy/MM/dd");
    }

    private String getExactOneYearsBeforeTodayDateString(){
        Date previousYear = addToDate(new Date(), Calendar.YEAR, -1);
        return DateUtils.fromDateToPersianString(previousYear, "yyyy/MM/dd");
    }

    private boolean checkIsActiveComponentValue(BaseInputComponentTest isActiveComponent, String expectedValue) {
        return !isActiveComponent.getComponentContent().isEmpty() &&
                isActiveComponent.getComponentContent().getFirst() != null &&
                normalizePersianCharacters(isActiveComponent.getComponentContent().getFirst()).equals(normalizePersianCharacters(expectedValue));
    }
}
