package site.fsyj.monitor.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "t_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String areaId;

    @ApiModelProperty(value = "")
    private String buildId;

    @ApiModelProperty(value = "")
    private String projectId;

    @ApiModelProperty(value = "")
    private String roomId;
}