package site.fsyj.monitor.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronUtilsTest {

    @Test
    public void cronTest() {
        Assertions.assertTrue(CronUtils.isValid("0 0 0,13 * * ?"));
    }

}
