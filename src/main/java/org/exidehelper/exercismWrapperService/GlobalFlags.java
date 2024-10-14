package org.exidehelper.exercismWrapperService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalFlags {
    Integer timeout;
    boolean unmaskToken;
    boolean verbose;
}
