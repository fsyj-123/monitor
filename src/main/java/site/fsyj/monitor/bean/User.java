package site.fsyj.monitor.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;

    private String username;

    private String password;

    private String jobId;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
