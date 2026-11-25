package TestComponents;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComponentReportTemplate {
    private String componentTitle;
    private String componentContentAfterLatestAction;
    private List<String> componentValues;
    private String componentMessage;
}
