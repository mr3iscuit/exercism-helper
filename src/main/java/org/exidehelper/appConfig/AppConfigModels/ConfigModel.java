package org.exidehelper.appConfig.AppConfigModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigModel {
    @Builder.Default
    private String exercismExecutablePath = "exercism";

    @Builder.Default
    private EditorConfigModel editorConfig = new EditorConfigModel();
}
