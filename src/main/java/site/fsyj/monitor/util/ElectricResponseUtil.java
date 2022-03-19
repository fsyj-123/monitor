package site.fsyj.monitor.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author fsyj on 2022/3/14
 */
@Slf4j
public class ElectricResponseUtil {
    public static int getRestElectric(String body) {
        int rest = 0;
        Map<String, Object> map = JsonUtil.jsonToMap(body);
        String data = map.get("data").toString();
        String dataEntity = data.substring(1, data.length() - 1);
        String oddl = JsonUtil.jsonToMap(dataEntity).get("oddl").toString();
        rest = (int) Double.parseDouble(oddl);
        return rest;
    }
}
