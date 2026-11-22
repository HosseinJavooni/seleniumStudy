package seleniumTests.testCases.boEnforcementTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static utils.ActionsOnPage.checkElementContainValue;
import static utils.ActionsOnPage.clickById;
import static utils.ActionsOnPage.selectFromDropDownSearchBoxList;
import static utils.ActionsOnPage.setAnStringToField;
import static utils.Utils.*;

public class CatalogDetailTestCase extends BoEnforcementBaseTestCase {
    private static final Logger logger = LogManager.getLogger(CatalogDetailTestCase.class);
    @Override
    public void testPage(String endPoint) {
        WebDriver driver = provideTheDriver(logger, endPoint);
        logTestCasePageTitle(logger, driver);

        clickById(driver, "Create");
        selectFromDropDownSearchBoxList(driver, "catalog", " ", 1);
        String code = getRandomNumber(1, 1000000);
        setAnStringToField(driver, "usedCode", code, 1);
        setAnStringToField(driver, "title",  "عنوان تستی سلنیوم "  + toPersianNumber(code), 1);
        clickById(driver, "Save");
        sleep(1);
        setAnStringToField(driver, "title",  "عنوان تستی سلنیوم "  + toPersianNumber(code), 0);
        sleep(1);
        clickById(driver, "Search");
        checkElementContainValue(driver, "DataTable-1-1", code);
    }
}
