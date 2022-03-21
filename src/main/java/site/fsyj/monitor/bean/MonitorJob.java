package site.fsyj.monitor.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.fsyj.monitor.util.IDUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorJob {
    private String id;

    /**
    * 寝室名，自定义
    */
    private String name;

    private String areaId;

    private String buildId;

    private String projectId;

    private String roomId;

    /**
    * 自动任务是否启动，默认true
    */
    private Boolean enable;

    /**
    * 任务执行状态，0：未执行，1：已执行
    */
    private Boolean status;

    /**
    * 推送链接：可以是邮箱或第三方链接
    */
    private String webhook;

    public MonitorJob(Map<String, String> params) {
        id = IDUtils.getUUID();
        name = params.get("name");
        areaId = params.get("areaId");
        buildId = params.get("buildId");
        projectId = params.get("projectId");
        roomId = params.get("roomId");
        enable = true;
        status = Boolean.valueOf(params.get("status"));
        webhook = params.get("mail");
    }


    public Map<String, String> getPostBody() {
        HashMap<String, String> map = new HashMap<>(8);
        map.put("areaid", areaId);
        map.put("buildid", buildId);
        map.put("projectId", projectId);
        map.put("roomid", roomId);
        return map;
    }
}
