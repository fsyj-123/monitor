package site.fsyj.monitor.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Build {
    private String id;

    private String buildId;

    private String buildName;

    /**
     * 所属园区
     */
    private String areaParent;
}