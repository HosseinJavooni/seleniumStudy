package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static utils.ActionsOnPage.*;
import static utils.Utils.getRandomNumber;
import static utils.Utils.sleep;

public class CatalogTestCase extends BoEnforcementBaseTestCase {
    Logger logger = LogManager.getLogger(CatalogTestCase.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver, "Create");
        String code = getRandomNumber(1, 1000000);
        setAnStringToField(driver, "usedCode", code, 1);
        setAnStringToField(driver, "title", code +  "عنوان-تستی ", 1);
        setAnStringToField(driver, "englishTitle", "Test-Title " + code, 1);
        clickById(driver, "Save");

        setAnStringToField(driver, "usedCode", code, 0);
        clickById(driver, "Search");
        checkElementContainValue(driver, "DataTable-1-1", code);

        sleep(3);
    }
}
