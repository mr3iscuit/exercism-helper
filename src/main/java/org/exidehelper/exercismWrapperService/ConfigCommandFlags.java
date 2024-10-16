package org.exidehelper.exercismWrapperService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigCommandFlags {
    Boolean show;
    Boolean help;
    Boolean noVerify;

    String workspace;
    String token;
    String api;
}
