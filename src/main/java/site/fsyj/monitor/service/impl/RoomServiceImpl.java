package site.fsyj.monitor.service.impl;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;
import site.fsyj.monitor.DO.BuildDo;
import site.fsyj.monitor.DO.RoomDo;
import site.fsyj.monitor.bean.Room;
import site.fsyj.monitor.config.HeaderConfig;
import site.fsyj.monitor.http.service.LoginService;
import site.fsyj.monitor.mapper.RoomMapper;
import site.fsyj.monitor.service.RoomService;
import site.fsyj.monitor.util.JsonUtil;
import site.fsyj.monitor.util.UrlConst;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fsyj on 2022/3/20
 */
@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

    private static Map<String, String> floorQueryMap = new HashMap<>(7);

    static {
        floorQueryMap.put("Host", HeaderConfig.HOST);
        floorQueryMap.put("Accept", HeaderConfig.ACCEPT);
        floorQueryMap.put("User-Agent", HeaderConfig.USER_AGENT);
        floorQueryMap.put("Content-type", HeaderConfig.CONTENT_TYPE);
    }

    @Resource
    private LoginService loginService;

    @Resource
    private HttpClient queryClient;

    @Resource
    private RoomMapper roomMapper;

    private HttpPost initPost(String contentLength, String url) {
        floorQueryMap.put("Content-Length", contentLength);
        floorQueryMap.put("X-Token", loginService.getLoginUser().getXToken());
        HttpPost httpPost = new HttpPost(url);
        Set<Map.Entry<String, String>> set = floorQueryMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        return httpPost;
    }

    private String execute(HttpPost post) {
        String res = null;
        try {
            res = queryClient.execute(post, new BasicHttpClientResponseHandler());
        } catch (IOException e) {
            log.error("房间查询出错：{}", e.getMessage());
        }
        return res;
    }

    @Override
    public List<BuildDo> getFloors(String areaId, String projectId) {
        HttpPost post = initPost("61", UrlConst.FLOOR_URL);
        HashMap<String, String> map = new HashMap<>(2);
        map.put("areaid", areaId);
        map.put("projectId", projectId);
        post.setEntity(new StringEntity(JsonUtil.mapToStr(map)));
        String response = execute(post);
        return respToList(response, BuildDo.class);
    }

    @Override
    public List<RoomDo> getRooms(String areaId, String buildId, String projectId) {
        HttpPost post = initPost("75", UrlConst.ROOM_URL);
        HashMap<String, String> map = new HashMap<>(3);
        map.put("areaid", areaId);
        map.put("buildid", buildId);
        map.put("projectId", projectId);
        post.setEntity(new StringEntity(JsonUtil.mapToStr(map)));
        String resp = execute(post);
        return respToList(resp, RoomDo.class);
    }

    private  static <T> List<T> respToList(String response, Class<T> aClass) {
        Map<String, Object> info = JsonUtil.getAllInfo(response);
        JSONArray floors = (JSONArray) info.get("data");
        return floors.toJavaList(aClass);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return roomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Room record) {
        return roomMapper.insert(record);
    }

    @Override
    public int insertSelective(Room record) {
        return roomMapper.insertSelective(record);
    }

    @Override
    public Room selectByPrimaryKey(String id) {
        return roomMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Room record) {
        return roomMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Room record) {
        return roomMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Room> selectByParent(String buildId) {
        return roomMapper.selectAllByBuildParent(buildId);
    }
}


