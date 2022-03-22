package site.fsyj.monitor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.bean.User;
import site.fsyj.monitor.service.JobService;
import site.fsyj.monitor.service.UserService;
import site.fsyj.monitor.util.IDUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author fsyj on 2022/3/19
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private JobService jobServiceImpl;

    @Resource
    private UserService userServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<String> add(HttpServletRequest request, String areaid, String buildid, String projectId,
                                      String roomid, String name, Boolean status, String email, HttpServletResponse response) throws IOException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            MonitorJob job = new MonitorJob(IDUtils.getUUID(), name, areaid, buildid, projectId, roomid, true, status, email);
            System.out.println(job);
            jobServiceImpl.addTask(loginUser, job);
            response.sendRedirect("/");
            return ResponseEntity.ok("添加成功，请刷新页面");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<MonitorJob> getTask(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.badRequest().body(null);
        }
        loginUser = userServiceImpl.selectByPrimaryKey(loginUser.getId());
        if (loginUser.getJobId() != null) {
            return ResponseEntity.ok(jobServiceImpl.selectByUser(loginUser));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
