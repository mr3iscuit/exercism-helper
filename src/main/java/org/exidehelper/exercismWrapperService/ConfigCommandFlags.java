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
    boolean show;
    boolean help;
    boolean noVerify;

    String workspace;
    String token;
    String api;
}
