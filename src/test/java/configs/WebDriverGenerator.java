package configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.nio.file.Paths;

import static utils.Utils.sleep;

public class WebDriverGenerator {
    private static WebDriverGenerator webDriverGenerator;
    private static WebDriver driver;
    private WebDriverGenerator() {
        ChromeDriverService service =
                new ChromeDriverService.Builder()
                        .usingDriverExecutable(
                                Paths.get("drivers","chromedriver-linux64", "chromedriver").toFile())
                        .build();

        driver = new ChromeDriver(service);

//        driver.navigate().to("http://127.0.0.1:8081/");

        driver.manage().window().maximize();
    }
    private static WebDriver getDriver() {
        if (webDriverGenerator == null) {
            webDriverGenerator = new WebDriverGenerator();
        }
        return webDriverGenerator.driver;
    }
    public static WebDriver goToTheEndPoint(String baseUrl, String endPoint) {
        getDriver().navigate().to(baseUrl + endPoint);
        return driver;
    }

    public static void closeDriver(int AfterSeconds) {
        sleep(AfterSeconds);
        getDriver().close();
    }
}
