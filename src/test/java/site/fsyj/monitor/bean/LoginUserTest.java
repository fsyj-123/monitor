package site.fsyj.monitor.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginUserTest {

    @Autowired
    LoginUser loginUser;

    @Test
    void parse() {
        loginUser.parse("datalook_reimbursement_token=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwYWFkZjhlNjFhODI0NzE0YWQzYjJlNzMxNWE0YTM4OSIsImlzcyI6InNlbnlpbnQiLCJpYXQiOjE2NDc0MjY5MDYsImV4cCI6MTY0NzUxMzMwNn0.nKU8HwPgOv3uj0FgFuCJzfqYHd1DCK3_OKmLif5y3Ow; datalook_login_status=false");
        Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwYWFkZjhlNjFhODI0NzE0YWQzYjJlNzMxNWE0YTM4OSIsImlzcyI6InNlbnlpbnQiLCJpYXQiOjE2NDc0MjY5MDYsImV4cCI6MTY0NzUxMzMwNn0.nKU8HwPgOv3uj0FgFuCJzfqYHd1DCK3_OKmLif5y3Ow",loginUser.getXToken());
    }
}
