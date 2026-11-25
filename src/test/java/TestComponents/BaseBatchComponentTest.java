package TestComponents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BaseBatchComponentTest implements BaseTest {
    List<BaseInputComponentTest> components;
    public BaseBatchComponentTest(List<BaseInputComponentTest> components) {
        this.components = components;
    }
    @Override
    public abstract void doAction();
    @Override
    public abstract List<ComponentReportTemplate> getComponentReports();
}
