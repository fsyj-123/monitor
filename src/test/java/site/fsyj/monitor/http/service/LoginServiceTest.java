package site.fsyj.monitor.http.service;

class LoginServiceTest {

//    private static final Pattern urlPattern = Pattern.compile(
//            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
//                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
//                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
//            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//
//
//    @Test
//    void login() throws Exception {
//        BasicCookieStore store = new BasicCookieStore();
//        HttpClient client = HttpClients.custom().disableRedirectHandling().setDefaultCookieStore(store).build();
//        // 获取SessionId
//        Cookie sessionId = getSessionId(client, store);
//        // 获取publicKey，这里先不用，如果日后密码变了再说
////        String publicKey = getPublicKey(client);
////        String encodePaw = RSAEncrypt.encrypt("LOVE@WL.com", publicKey);
////       获取最终的loginUrl
//        String doLogin = doLogin(null, store, sessionId);
//    }
//
//    private String getPublicKey(HttpClient client) {
//        HttpGet get = new HttpGet("https://cas.paas.cdut.edu.cn/cas/jwt/publicKey");
//        get.addHeader("Host", "cas.paas.cdut.edu.cn");
//        String res = null;
//        try {
//            String response = client.execute(get, new BasicHttpClientResponseHandler());
//            String[] split = response.split("-----");
//            res = split[2].trim();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
//
//    private String doLogin(String encodePaw, BasicCookieStore store, Cookie sessionId) throws IOException {
//        String loginUrl = null;
//
//
//        CloseableHttpClient client = HttpClients.custom().addRequestInterceptorLast(new RequestContent(true)).build();
//        HttpPost post = new HttpPost("https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/");
//
//        post.addHeader("Host", " cas.paas.cdut.edu.cn");
//        post.addHeader("Connection", " keep-alive");
//        post.addHeader("Content-Length","543");
//        post.addHeader("Cache-Control","max-age=0");
//        post.addHeader("sec-ch-ua","\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
//        post.addHeader("sec-ch-ua-mobile","?0");
//        post.addHeader("sec-ch-ua-platform","\"Windows\"");
//        post.addHeader("Upgrade-Insecure-Requests","1");
//        post.addHeader("Origin","https://cas.paas.cdut.edu.cn");
//        post.addHeader("Content-Type","application/x-www-form-urlencoded");
//        post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
//        post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        post.addHeader("Sec-Fetch-Site","same-origin");
//        post.addHeader("Sec-Fetch-Mode","navigate");
//        post.addHeader("Sec-Fetch-User","?1");
//        post.addHeader("Sec-Fetch-Dest","document");
//        post.addHeader("Referer","https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/");
//        post.addHeader("Accept-Encoding","gzip, deflate, br");
//        post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
//        post.addHeader("Cookie", "SESSION=" + sessionId.getValue());
//
//
//        List<NameValuePair> list = new ArrayList<>(9);
//        list.add(new BasicNameValuePair("username","201901130210"));
//        list.add(new BasicNameValuePair("password","__RSA__NCxjgXnFHkSSl0CHGphlErh0dnqo2lPDgs3BAz+kBAW5GDZkIbYdhUANJff82EUwXsQ0Oy3l7NkzCg5z2G9QTXEON201bOu439DuP13IVaEBa022f6jQKx849DnZ08bU7z705EivrISR5dH2Vmy+7o9F+/ACled4HEmKl45gNixzSIC+85iJu/cv3DfFZYp0+aolX9YMVaHiPJS4ynotLV34GxQ5qZf8kZbb6r91SAb/IqIYj8rJecMcZbjwLICaJyoVnGr/rhz+1aKsfwZKZhrKTGDrZI3zIhY8jmvqN4F2hcR7QfHYI6j1wFthKlMFQb61JufsJaiDzK1WONx5Rg=="));
//        list.add(new BasicNameValuePair("captcha",""));
//        list.add(new BasicNameValuePair("currentMenu","1"));
//        list.add(new BasicNameValuePair("failN","0"));
//        list.add(new BasicNameValuePair("execution","e1s1"));
//        list.add(new BasicNameValuePair("_eventId","submit"));
//        list.add(new BasicNameValuePair("geolocation",""));
//        list.add(new BasicNameValuePair("submit","稍等片刻……"));
//        post.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));
//
//        String resp = client.execute(post, new BasicHttpClientResponseHandler());
//        System.out.println("++++");
//        Matcher matcher = urlPattern.matcher(resp);
//        while (matcher.find()) {
//            String matchStr = matcher.group();
//            String str = matchStr.substring(1);
//            if (str.endsWith("/2")) {
//                loginUrl = str;
//            }
//        }
//        return loginUrl;
//    }
//
//    private Cookie getSessionId(HttpClient client, BasicCookieStore store) {
//        HttpGet get = new HttpGet("https://cas.paas.cdut.edu.cn/cas/login?service=http://paym.cdut.edu.cn/casLogin/");
//        try {
//            client.execute(get);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return store.getCookies().get(0);
//    }
}
