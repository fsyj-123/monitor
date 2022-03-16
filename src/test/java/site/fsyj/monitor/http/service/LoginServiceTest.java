package site.fsyj.monitor.http.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Resource
    LoginService loginService;

    @Test
    void login() throws IOException {
        loginService.login();
    }
}
