package site.fsyj.monitor.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "t_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorJob {
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

    /**
     * 自动任务是否启动，默认true
     */
    @ApiModelProperty(value = "自动任务是否启动，默认true")
    private Boolean enable;

    /**
     * 任务执行状态，0：未执行，1：已执行
     */
    @ApiModelProperty(value = "任务执行状态，0：未执行，1：已执行")
    private Boolean status;

    /**
     * 推送链接：可以是邮箱或第三方链接
     */
    @ApiModelProperty(value = "推送链接：可以是邮箱或第三方链接")
    private String webhook;


    public Map<String, String> getPostBody() {
        HashMap<String, String> map = new HashMap<>(8);
        map.put("areaid", areaId);
        map.put("buildid", buildId);
        map.put("projectId", projectId);
        map.put("roomid", roomId);
        return map;
    }
}
