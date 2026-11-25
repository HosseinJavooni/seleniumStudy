package seleniumTests.testCases.boEnforcementTests;

import seleniumTests.testCases.BaseTestCase;
import configs.ProjectKeyValueConfigs;
import configs.WebDriverGenerator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.ActionsOnPage.getAllWebElementsByTheSameXpath;
import static utils.Utils.sleep;

public abstract class BoEnforcementBaseTestCase implements BaseTestCase {
    @Override
    public void logTestCasePageTitle(Logger logger, WebDriver driver) {
        sleep(1);
        List<WebElement> allSelectedElements = getAllWebElementsByTheSameXpath(driver, "//p[contains(@class,'Toolbar-Title')]");
        if (!allSelectedElements.isEmpty()) {
            logger.info("Page title:  " + allSelectedElements.getFirst().getText().replace("جستجوی ", "" ) + "\n" + "Page Url:  " + driver.getCurrentUrl());
        } else {
            logger.info("Page Url:  " + driver.getCurrentUrl());
        }
    }
    @Override
    public WebDriver provideTheDriver(Logger logger, String endPoint) {
        WebDriver driver = WebDriverGenerator.goToTheEndPoint(ProjectKeyValueConfigs.boEnforcementBaseUrl, endPoint);
//        logTestCasePageTitle(logger, driver);
        sleep(1);
        return driver;
    }
}
