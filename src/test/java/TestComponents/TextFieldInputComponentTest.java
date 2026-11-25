package TestComponents;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static utils.ActionsOnPage.*;

public class TextFieldInputComponentTest extends BaseInputComponentTest {
    public TextFieldInputComponentTest(WebDriver driver, String elementId, Logger logger, int index, String key) {
        super(driver, elementId, logger, index, key);
    }

    @Override
    protected void sendKeyToComponent(String sendKey) {
        setAnStringToField(driver, elementId, key, index);
    }

    @Override
    protected void manipulateTheComponent() {
        clearATextField(driver, elementId, index);
        sendKeyToComponent(key);
    }

    @Override
    public TextFieldInputComponentTest doAction() {
        sendKeyToComponent(key);
        manipulateTheComponent();
        return this;
    }

    @Override
    public List<String> getComponentContent() {
        String value = getTheValueOfATextField(driver, elementId, index);
        List<String> values = new ArrayList<>();
        values.add(value);
        return values;
    }
}
