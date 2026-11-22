package seleniumTests.testCases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public interface BaseTestCase {
    void testPage(String endPoint);
    void logTestCasePageTitle(Logger logger, WebDriver driver);
    WebDriver provideTheDriver(Logger logger, String endPoint);
    default void startTheTest(Logger logger, WebDriver driver, String endPoint) {
        try{

        } catch (Throwable e){
            e.printStackTrace();
        }
    }
}
