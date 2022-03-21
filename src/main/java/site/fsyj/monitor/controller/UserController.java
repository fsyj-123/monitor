package site.fsyj.monitor.controller;

import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.fsyj.monitor.bean.User;
import site.fsyj.monitor.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author fsyj on 2022/3/16
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userServiceImpl;


    @PostMapping("/reg")
    @ResponseBody
    public ResponseEntity<String> regPost(String username, String password,
                                           String imageCode, HttpServletRequest request) {
        // 验证验证码是否正确
        log.info("user=={}", password);
        HttpSession session = request.getSession();
        String capText = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!capText.equals(imageCode)) {
            return ResponseEntity.badRequest().body("验证码错误");
        }
        User user = userServiceImpl.regUser(username, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return ResponseEntity.ok("注册成功");
        }
        return ResponseEntity.badRequest().body("未知错误，注册失败");
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(String username, String password, String imageCode, HttpServletRequest request) {
        // 验证验证码是否正确
        HttpSession session = request.getSession();
        String capText = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!capText.equals(imageCode)) {
            return ResponseEntity.badRequest().body("验证码错误");
        }
        User user = userServiceImpl.loginUser(username, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return ResponseEntity.ok().body("登录成功");
        }
        return ResponseEntity.badRequest().body("用户不存在或密码错误");
    }

    @PostMapping("/me")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        return Objects.isNull(loginUser) ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(loginUser);
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("loginUser");
        response.sendRedirect("/");
        return ResponseEntity.ok("注销成功");
    }
}
