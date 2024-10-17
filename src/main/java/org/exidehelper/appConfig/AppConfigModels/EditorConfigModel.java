package org.exidehelper.appConfig.AppConfigModels;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditorConfigModel {
    @Builder.Default
    Boolean openOnDownload = false;

    @Builder.Default
    EditorPathConfigModel editorPathConfig = new EditorPathConfigModel();
}
