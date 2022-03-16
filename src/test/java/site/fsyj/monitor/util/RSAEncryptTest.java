package site.fsyj.monitor.util;

import org.junit.jupiter.api.Test;
import site.fsyj.monitor.config.LoginConfig;

import static org.junit.jupiter.api.Assertions.*;

class RSAEncryptTest {

    @Test
    void encrypt() throws Exception {
        String paw = RSAEncrypt.encrypt("LOVE@WL.com", LoginConfig.PUBLIC_KEY);
        System.out.println(paw);
    }
}
