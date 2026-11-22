package seleniumTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import seleniumTests.testCases.boEnforcementTests.*;

import java.util.Scanner;

import static configs.WebDriverGenerator.closeDriver;

public class BoEnforcementTestClass {
    private static final Logger logger = LogManager.getLogger(BoEnforcementTestClass.class);
    public static void main(String[] args) {
//    logger.info("Starting BoEnforcementTestClass");
//        ChromeDriverService service =
//                new ChromeDriverService.Builder()
//                        .usingDriverExecutable(
//                                Paths.get("drivers","chromedriver-linux64", "chromedriver").toFile())
//                        .build();
//
//        WebDriver driver = new ChromeDriver(service);
//
//        driver.navigate().to("http://127.0.0.1:8081/#/path-search-0");
//        sleep(3);
//        driver.navigate().to("http://127.0.0.1:8081/#/catalog-search-0");
//
//        driver.manage().window().maximize();
//        BaseTestCase testCase;
        /*testCase = */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Test other pages? (0: No, 1: Yes)");
        int i = scanner.nextInt();
        if(i == 1) {
            new CatalogTestCase().testPage("catalog-search-0"); // انواع اطلاعات پایه عمومی
            new CatalogDetailTestCase().testPage("catalog-detail-search-0"); // اطلاعات پایه عمومی
            new ViolationTypeSearchTestCase().testPage("violation-type-search-0"); // نوع تخلف
            new ViolationRule().testPage("violation-rule-search-0"); // قواعد تخلف دوربین
            new OppositeViolationTestCase().testPage("opposite-violation-search-0"); // تخلف های غیر مجاز جهت ثبت همزمان
            new CameraTypeTestCase().testPage("camera-type-search-0"); // نوع دوربین
        }
        new CameraTestCase().testPage("camera-search-0"); //
//        sleep(5);
//        WebDriverGenerator.getDriver().close();


//        driver.close();
//        closeDriver(5);


    }
}
