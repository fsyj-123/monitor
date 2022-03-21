package site.fsyj.monitor.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String id;

    private String roomId;

    private String roomName;

    /**
     * 所属楼栋
     */
    private String buildParent;
}