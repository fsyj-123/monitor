package site.fsyj.monitor.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author fsyj on 2022/3/15
 */
@Data
@Slf4j
@EnableConfigurationProperties(LoginConfig.class)
@ConfigurationProperties(value = "user", ignoreInvalidFields = true)
public class LoginConfig {
    public final static String LOGIN_SITE = "https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/";

    private String userName;

    private String password;

    /**
     * 前端rsa加密公钥
     */
    public final static String PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyor3CX6A6U4EoSHawtAL\n" +
            "iJoB0CkJnb/wmVkcVT5EmNupGVrVSeJo80ZAxsgd9S1CZVXxTXtJ7XjsqnzR64Qv\n" +
            "rn+tdvj9Ck5k/6Tnp6HoKU/AQxA3tQ5Zqw6D6ihPOyVV4z4cdK5wjzEBNPhJuTjj\n" +
            "zP4VQ4h4VseWNbfhXGK3vSes8oNn5Wwor9r1UbEJP/ZMHrDJxAcwe0GPvebAqEp4\n" +
            "O5ZcTtWnq+/qkoUB6z/52EnCMltoPmuMC+o3fWdICBf4q70oSDClfuhLVi4mRT2K\n" +
            "5UUH8fsxEe6oPtkvk9vVCCOZRmo0MXpXZiIqdZOtgcBzn/0mzoNd58KxeIy0ginj\n" +
            "fwIDAQAB\n";

    public final static String PREFIX = "__RSA__";

    /**
     * 不用手动加密，直接登录时抓包，获取密码
     * cdut统一登录平台前端会对密码进行sra加密
     * 这样可能会导致配置文件过于臃肿
     * @return paw
     */
    public String getPassword()  {
        return password;
    }
}
