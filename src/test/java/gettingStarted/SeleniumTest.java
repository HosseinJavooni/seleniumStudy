package gettingStarted;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.nio.file.Paths;

public class SeleniumTest {
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
                                Paths.get("drivers", "chromedriver").toFile())
                        .build();


        //1. Create a WebDriver with ChromeDriver object
        //2. Launch the browser
        WebDriver driver = new ChromeDriver(service);

        //3. Navigate to a specific URL
        driver.navigate().to("https://www.google.com");
    }
}
