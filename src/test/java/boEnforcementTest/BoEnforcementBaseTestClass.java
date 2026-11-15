package boEnforcementTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.nio.file.Paths;
import java.util.List;


import static utils.ActionsOnPage.*;
import static utils.Utils.*;

public class BoEnforcementBaseTestClass {
    public static void main(String[] args) {

        ChromeDriverService service =
                new ChromeDriverService.Builder()
                        .usingDriverExecutable(
                                Paths.get("drivers","chromedriver-linux64", "chromedriver").toFile())
                        .build();

        WebDriver driver = new ChromeDriver(service);

        driver.navigate().to("http://127.0.0.1:8081/");

        driver.manage().window().maximize();

//        checkTabNumber01(driver, "انواع اطلاعات پایه عمومی");
        checkTabNumber02(driver, "اطلاعات پایه عمومی");


//        driver.close();


    }

    private static void checkTabNumber02(WebDriver driver, String tabTitle) {
        clickByText(driver, tabTitle);
        clickById(driver, "Create");
        selectFromDropDownSearchBoxList(driver, "catalog", 1);
        String code = getRandomNumber(1000000);
        setAnStringToField(driver, "usedCode", code, 1);
        setAnStringToField(driver, "title",  "عنوان تستی سلنیوم "  + toPersianNumber(code), 1);
        clickById(driver, "Save");
        sleep(1);
        setAnStringToField(driver, "title",  "عنوان تستی سلنیوم "  + toPersianNumber(code), 0);
        sleep(1);
        clickById(driver, "Search");
    }

    public static void checkTabNumber01(WebDriver driver, String tabTitle) {
        clickByText(driver, tabTitle);
        clickById(driver, "Create");
        String code = getRandomNumber(1000000);
        setAnStringToField(driver, "usedCode", code, 1);
        setAnStringToField(driver, "title", code +  "عنوان-تستی ", 1);
        setAnStringToField(driver, "englishTitle", "Test-Title " + code, 1);
        clickById(driver, "Save");

        setAnStringToField(driver, "usedCode", code, 0);
        clickById(driver, "Search");
        if(getSpecificElementById(driver, "DataTable-1-1", 0) == null){
            System.err.println("DataTable-1-1 is null");
            return;
        }
        if(!getSpecificElementById(driver, "DataTable-1-1", 0).getText().equals(code)){
            System.err.println("Search the created record has failed");
        } else {
            System.err.println("everything is ok");
        }

        sleep(3);
    }
}
