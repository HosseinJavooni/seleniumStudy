package TestComponents;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static utils.ActionsOnPage.*;


public class MultiSelectSearchBoxInputComponentTest extends BaseInputComponentTest {

    public MultiSelectSearchBoxInputComponentTest(WebDriver driver, String elementId, Logger logger, int index, String key) {
        super(driver, elementId, logger, index, key);
    }

    @Override
    public MultiSelectSearchBoxInputComponentTest doAction() {
        sendKeyToComponent(key);
        manipulateTheComponent();
        content = getComponentContent();
        return this;
    }

    @Override
    public void manipulateTheComponent() {
        deleteAllMultiSelectedInputValues(driver, elementId);
        sendKeyToComponent(key);
        selectNextEntryInDropDownList(driver, elementId, 0);
    }

    @Override
    public void sendKeyToComponent(String sendKey) {
        selectFromDropDownSearchBoxList(driver, elementId, sendKey, logger, index);
    }

    @Override
    public List<String> getComponentContent() {
        return getAllMultiSelectedInputValues(driver, elementId);
    }
}
