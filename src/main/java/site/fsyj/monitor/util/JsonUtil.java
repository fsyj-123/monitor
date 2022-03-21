package site.fsyj.monitor.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author fsyj
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = new HashMap<>(0);
        if (!StringUtils.hasLength(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (String s : params) {
            String[] p = s.split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }


    public static String mapToStr(Map<String, ?> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        return jsonObject.toJSONString();
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     *
     * @param object java对象
     * @return JSON数据
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return List<T>
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param jsonData JSON数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * json string 转换为 map 对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> jsonToMap(String jsonStr) {
        return JSON.parseObject(jsonStr, Map.class);
    }


    public static Map<String, Object> getAllInfo(String jsonStr) {
        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        return map == null ? new HashMap() : map;
    }


    public static <T> List<T> jsonToList(String jsonList, Class<T> tClass) {
        ArrayList<T> list = new ArrayList<>();
        try {
            T tNode = null;
            JsonNode jsonNode = objectMapper.readTree(jsonList);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    tNode = jsonToObject(node.toString(), tClass);
                    list.add(tNode);
                }
            }
        } catch (IOException e) {
            log.error("JSON格式错误");
        }
        return list;
    }

    public static List<String> json2strList(String jsonList, String name) {
        JsonNode jsonNode = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            jsonNode = objectMapper.readTree(jsonList).get(name);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    list.add(node.textValue());
                }
            }
        } catch (IOException e) {
            log.error("JSON格式错误");
        }
        return list;
    }

    /**
     * 字符串转换为自定义对象
     * @param node 要转换的字符串
     * @param tClass 自定义对象的class对象
     * @return 自定义对象
     */
    public static <T> T jsonToObject(String node, Class<T> tClass) {
        T value = null;
        try {
            value = objectMapper.readValue(node, tClass);
        } catch (IOException e) {
            log.error("JSON格式错误");
        }
        return value;
    }
}
