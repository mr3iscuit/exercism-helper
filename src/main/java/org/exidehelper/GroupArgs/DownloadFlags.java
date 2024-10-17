package org.exidehelper.GroupArgs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFlags {
    private Boolean force;
    private Boolean help;
    private String exercise;
    private String track;
    private String team;
    private String uuid;
}
