package TestComponents;

import org.openqa.selenium.WebDriver;

import java.util.List;

public interface BaseTestComponent {
    void doActionOnComponent();
    List<String> getComponentValues();
    String getComponentTitle();
}
