package site.fsyj.monitor.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Api("页面跳转接口")
@Controller
public class PageController {

    /**
     * 首页
     * @return ModelAndView
     */
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 欢迎页
     * @return ModelAndView
     */
    @RequestMapping("/welcomePage")
    public ModelAndView welcomePage() {
        return new ModelAndView("welcomePage");
    }

    /**
     * 公告编辑页
     * @return ModelAndView
     */
    @RequestMapping("/system-notice-edit")
    public ModelAndView editSystemNotice() {
        return new ModelAndView("system-notice-edit");
    }

    /**
     * 登录页
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 注册页
     * @return ModelAndView
     */
    @GetMapping("/reg")
    public ModelAndView reg() {
        return new ModelAndView("reg");
    }

    /**
     * 日志查看页
     * @return ModelAndView
     */
    @GetMapping("/getlog")
    public ModelAndView getLog() {
        return new ModelAndView("myHelper/getlog");
    }

    /**
     * 我的任务页
     * @return ModelAndView
     */
    @GetMapping("/my")
    public ModelAndView myPage() {
        return new ModelAndView("myHelper/my-helper");
    }

    /**
     * 全局推送配置页
     * @return ModelAndView
     */
    @GetMapping("/my-edit")
    public ModelAndView myEditPage() {
        return new ModelAndView("myHelper/my-helper-edit");
    }

    /**
     * 推送地址生成器页面
     * @return ModelAndView
     */
    @GetMapping("/webhook-generate")
    public ModelAndView webhookGeneratePage(){
        return new ModelAndView("myHelper/webhook-generate");
    }

}
