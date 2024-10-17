package org.exidehelper.GroupArgs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalFlags {
    private Integer timeout;
    private Boolean unmaskToken;
    private Boolean verbose;
}
