package site.fsyj.monitor.config;

/**
 * @author fsyj on 2022/3/15
 */
public class HeaderConfig {
    public final static String HOST = "paym.cdut.edu.cn";
    public final static String CONTENT_LENGTH = "92";
    public final static String ACCEPT = "application/json, text/plain, */*";
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36 Edg/99.0.1150.39";
    public final static String CONTENT_TYPE = "application/json";
    public final static String QUERY_WEBSITE = "http://paym.cdut.edu.cn/api/pay/web/payEleCostController/querySydl";

    private static String X_TOKEN;
    private static String COOKIE;

    public static String getxToken() {
        return X_TOKEN;
    }

    public static void setxToken(String xToken) {
        X_TOKEN = xToken;
    }

    public static String getCOOKIE() {
        return COOKIE;
    }

    public static void setCOOKIE(String COOKIE) {
        HeaderConfig.COOKIE = COOKIE;
    }
}
