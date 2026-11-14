package boEnforcementTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.nio.file.Paths;

import static utils.ActionsOnPage.clickById;

public class BoEnforcementBaseTestClass {
    public static void main(String[] args) {
        // Absolut OS path:

//        ChromeDriverService service =
//                new ChromeDriverService.Builder()
//                        .usingDriverExecutable(
//                                new File("/home/hossein/chromedriver-linux64/chromedriver"))
//                        .build();

        // Project based path: (Better and recommended way)
        ChromeDriverService service =
                new ChromeDriverService.Builder()
                        .usingDriverExecutable(
                                Paths.get("drivers","chromedriver-linux64", "chromedriver").toFile())
                        .build();


        //1. Create a WebDriver with ChromeDriver object
        //2. Launch the browser
        WebDriver driver = new ChromeDriver(service);
//        WebDriver driver = new FirefoxDriver(/*service*/);

        //3. Navigate to a specific URL
        driver.navigate().to("http://127.0.0.1:8081/#/catalog-search-0");

        //4. Make browser screen status
//        driver.manage().window().fullscreen();
        driver.manage().window().maximize();

        clickById(driver, "Create");
//        driver.manage().window().minimize();

        //5. get the URL from the browser
//        System.out.println("Browser URL:  " + driver.getCurrentUrl());
//        System.out.println("Page source:  " + driver.getPageSource());
//
//        System.out.println("Window handles: " + driver.getWindowHandles());

//        driver.close();


    }
}
