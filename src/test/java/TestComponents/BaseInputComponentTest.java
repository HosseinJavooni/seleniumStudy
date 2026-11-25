package TestComponents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static utils.ActionsOnPage.*;

@AllArgsConstructor
@Getter
public abstract class BaseInputComponentTest /*implements BaseTest*/ {
    protected WebDriver driver;
    protected String elementId;
    protected Logger logger;
    protected int index;
    @Setter
    protected String key;
    @Setter
    protected List<String> content;

    public BaseInputComponentTest(WebDriver driver, String elementId, Logger logger, int index, String key) {
        this.driver = driver;
        this.elementId = elementId;
        this.logger = logger;
        this.index = index;
        this.key = key;
    }

    protected abstract void sendKeyToComponent(String sendKey);
    protected abstract void manipulateTheComponent();
    public abstract BaseInputComponentTest doAction();
    public abstract List<String> getComponentContent();
    public String getComponentTitle(){
        return getInputElementTitle(driver, elementId, index);
    }
    public String getComponentMessage(){
        return getElementErrorMessage(driver, elementId ,index);
    }
    public List<ComponentReportTemplate> getComponentReports() {
        List<ComponentReportTemplate> componentReports = new ArrayList<>();
        ComponentReportTemplate componentReportTemplate = new ComponentReportTemplate();
        componentReportTemplate.setComponentValues(getComponentContent());
        componentReportTemplate.setComponentContentAfterLatestAction(content.toString());
        componentReportTemplate.setComponentTitle(getComponentTitle());
        componentReportTemplate.setComponentMessage(getComponentMessage());
        componentReports.add(componentReportTemplate);
        return componentReports;
    }

    public void recordTheComponentReport() {
        try {
            ComponentReportTemplate componentReportTemplate = getComponentReports().getFirst();
            logger.info("The component report is: " + componentReportTemplate);
        } catch (NoSuchElementException e){
            logger.error(e.getMessage());
        }
    }
}
