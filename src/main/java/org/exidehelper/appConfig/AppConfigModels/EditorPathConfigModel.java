package org.exidehelper.appConfig.AppConfigModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditorPathConfigModel {
    private String java;
    private String cpp;
    private String python;
    private String rust;
}
