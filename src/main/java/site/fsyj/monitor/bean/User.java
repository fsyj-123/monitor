package site.fsyj.monitor.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "t_user")
@Data
@NoArgsConstructor
public class User {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String username;

    @ApiModelProperty(value = "")
    private String password;

    @ApiModelProperty(value = "")
    private String jobId;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
