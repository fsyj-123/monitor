package site.fsyj.monitor.util;


import java.util.List;
import java.util.UUID;

/**
 * @author fsyj on 2022/1/23
 */
public class IDUtils {

    private final static String LOGIN_KEY_PREFIX = "login:";
    /**
     * 判断给定的id是否合法有效
     * @param ids
     * @return
     */
    public static boolean isIdValid(String... ids) {
        for (String id : ids) {
            if (id == null || id.length() != 32) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIdValid(List<String> parId) {
        for (String id : parId) {
            if (id == null || id.length() != 32) {
                return false;
            }
        }
        return true;
    }


    public static String redisLoginKeyGenerate(String loginId) {
        return LOGIN_KEY_PREFIX + loginId;
    }

    public static String getUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");

    }
}
