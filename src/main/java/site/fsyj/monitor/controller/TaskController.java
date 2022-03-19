package site.fsyj.monitor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fsyj.monitor.bean.MonitorJob;
import site.fsyj.monitor.bean.User;
import site.fsyj.monitor.service.JobService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author fsyj on 2022/3/19
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private JobService jobServiceImpl;

    @PostMapping("/add")
    public void add() {

    }

    @GetMapping("/get")
    public ResponseEntity<MonitorJob> getTask(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            return ResponseEntity.ok(jobServiceImpl.selectByUser(loginUser));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
