package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static utils.ActionsOnPage.*;
import static utils.Utils.*;

public class CameraTypeTestCase extends BoEnforcementBaseTestCase{
    Logger logger =  LogManager.getLogger(CameraTypeTestCase.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver, "Create");
        String code = getRandomNumber(1, 1000000);
        setAnStringToField(driver, "cameraTypeName",  "نوع دوربین "  + code, 1);

        clickById(driver, "Save");
        setAnStringToField(driver, "cameraTypeName",  "نوع دوربین "  + code, 0);
        clickById(driver, "Search");
        checkElementContainValue(driver, "DataTable-1-1",  "نوع دوربین "  + code);
    }
}
