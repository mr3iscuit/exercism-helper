package org.exidehelper.GroupArgs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigCommandFlags {
    private Boolean show;
    private Boolean help;
    private Boolean noVerify;
    private String workspace;
    private String token;
    private String api;
}
