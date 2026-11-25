package TestComponents;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static utils.ActionsOnPage.*;

public class SelectSearchBoxInputComponentTest extends BaseInputComponentTest {
    public SelectSearchBoxInputComponentTest(WebDriver driver, String elementId, Logger logger, int index, String key) {
        super(driver, elementId, logger, index, key);
    }

    @Override
    protected void sendKeyToComponent(String sendKey) {
        selectFromDropDownSearchBoxList(driver, elementId, sendKey, logger,  0);
    }

    @Override
    protected void manipulateTheComponent() {
        deleteDropDownSearchSelectedInputValue(driver, elementId);
        sendKeyToComponent("  ");
    }

    @Override
    public SelectSearchBoxInputComponentTest doAction() {
        sendKeyToComponent(key);
        manipulateTheComponent();
        return this;
    }

    @Override
    public List<String> getComponentContent() {
        List<String> values = new ArrayList<>();
        values.add(getDropDownSearchSelectedInputValue(driver, elementId));
        return values;
    }
}
