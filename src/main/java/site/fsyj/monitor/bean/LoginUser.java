package site.fsyj.monitor.bean;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用于处理登录后的cookie字段
 * @author fsyj on 2022/3/16
 */
@Getter
@Component
public class LoginUser {
    private String cookie;

    private String xToken;

    public void parse(String loginCookie) {
        this.cookie = loginCookie;
        if (StringUtils.hasLength(loginCookie)) {
            if (!loginCookie.contains("datalook_reimbursement_token")) {
                throw new IllegalArgumentException("Cookie字段必须包含datalook_reimbursement_token");
            }
            String[] strs = loginCookie.split(";");
            for (String str : strs) {
                String trimStr = str.trim();
                if (trimStr.contains("datalook_reimbursement_token")) {
                    xToken = trimStr.split("=")[1];
                }
            }
        }
    }
}
