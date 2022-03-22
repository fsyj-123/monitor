package site.fsyj.monitor.http.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.protocol.RequestContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.fsyj.monitor.bean.LoginUser;
import site.fsyj.monitor.config.AppConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模拟登录后，获取登录用户的x-token
 * @author fsyj
 */
@Slf4j
@Service
public class LoginService {

    /**
     * 用于获取字符串中的url
     */
    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


    private LoginUser loginUser;
    private Long lastLoginTime = 0L;

    @Autowired
    private AppConfig appConfig;

    public void login() {
        // 获取当前时间，如果如果当前时间距离上次登录时间不足，10分钟则直接返回，否则重新登录
        long interval = System.currentTimeMillis() - lastLoginTime;
        if (interval > appConfig.getLoginInterval() || loginUser == null) {
            BasicCookieStore store = new BasicCookieStore();
            HttpClient client = HttpClients.custom().disableRedirectHandling().setDefaultCookieStore(store).build();
            // 获取SessionId
            Cookie sessionId = getSessionId(client, store);
            String doLoginUrl = null;
            try {
                doLoginUrl = doLogin(null, store, sessionId);
                finalLogin(doLoginUrl);
            } catch (IOException e) {
                log.error("登录错误：{}", e.getMessage());
            }
        }
    }

    private String getPublicKey(HttpClient client) {
        HttpGet get = new HttpGet("https://cas.paas.cdut.edu.cn/cas/jwt/publicKey");
        get.addHeader("Host", "cas.paas.cdut.edu.cn");
        String res = null;
        try {
            String response = client.execute(get, new BasicHttpClientResponseHandler());
            String[] split = response.split("-----");
            res = split[2].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private String doLogin(String encodePaw, BasicCookieStore store, Cookie sessionId) throws IOException {
        String loginUrl = null;


        CloseableHttpClient client = HttpClients.custom().addRequestInterceptorLast(new RequestContent(true)).build();
        HttpPost post = new HttpPost("https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/");

        // 不想改了
        post.addHeader("Host", " cas.paas.cdut.edu.cn");
        post.addHeader("Connection", " keep-alive");
        post.addHeader("Content-Length", "543");
        post.addHeader("Cache-Control", "max-age=0");
        post.addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        post.addHeader("sec-ch-ua-mobile", "?0");
        post.addHeader("sec-ch-ua-platform", "\"Windows\"");
        post.addHeader("Upgrade-Insecure-Requests", "1");
        post.addHeader("Origin", "https://cas.paas.cdut.edu.cn");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
        post.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        post.addHeader("Sec-Fetch-Site", "same-origin");
        post.addHeader("Sec-Fetch-Mode", "navigate");
        post.addHeader("Sec-Fetch-User", "?1");
        post.addHeader("Sec-Fetch-Dest", "document");
        post.addHeader("Referer", "https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/");
        post.addHeader("Accept-Encoding", "gzip, deflate, br");
        post.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        post.addHeader("Cookie", "SESSION=" + sessionId.getValue());


        List<NameValuePair> list = new ArrayList<>(9);
        // 这里本应该采用配置文件中的配置项，原项目懒得改
        list.add(new BasicNameValuePair("username", "xxx"));
        // 这里的登录密码可以抓包获取，密码是通过rsa加密算法加密
        list.add(new BasicNameValuePair("password", "xxx"));
        list.add(new BasicNameValuePair("captcha", ""));
        list.add(new BasicNameValuePair("currentMenu", "1"));
        list.add(new BasicNameValuePair("failN", "0"));
        list.add(new BasicNameValuePair("execution", "e1s1"));
        list.add(new BasicNameValuePair("_eventId", "submit"));
        list.add(new BasicNameValuePair("geolocation", ""));
        list.add(new BasicNameValuePair("submit", "稍等片刻……"));
        post.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));

        String resp = client.execute(post, new BasicHttpClientResponseHandler());
        Matcher matcher = urlPattern.matcher(resp);
        while (matcher.find()) {
            String matchStr = matcher.group();
            String str = matchStr.substring(1);
            if (str.endsWith("/2")) {
                loginUrl = str;
            }
        }
        return loginUrl;
    }

    private Cookie getSessionId(HttpClient client, BasicCookieStore store) {
        HttpGet get = new HttpGet("https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/");
        try {
            client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return store.getCookies().get(0);
    }

    private void finalLogin(String finalUrl) {
        try {
            Document document = Jsoup.connect(finalUrl).get();
            String location = document.location();
            String[] split = location.split("token=");
            String token = split[1];
            loginUser = new LoginUser(token);
            lastLoginTime = System.currentTimeMillis();
        } catch (IOException e) {
            log.error("登录失败：{}", e.getMessage());
        }
    }

    public LoginUser getLoginUser() {
        login();
        return loginUser;
    }
}
