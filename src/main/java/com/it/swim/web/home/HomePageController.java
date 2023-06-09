package com.it.swim.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @description: 跳转界面相关类-登录界面
 */
@Controller
@RequestMapping("home")
public class HomePageController {
    /*
     * 会员主界面路由
     */
    @RequestMapping(value = "/vipIndex",method = RequestMethod.GET)
    private String vipIndex() {
        return "vip/vip_index";
    }

    /*
     * 教练主界面路由
     */
    @RequestMapping(value = "/coachIndex",method = RequestMethod.GET)
    private String coachIndex() {
        return "coach/coach_index";
    }

    /*
     * 管理员主界面路由
     */
    @RequestMapping(value = "/adminIndex",method = RequestMethod.GET)
    private String adminIndex() {
        return "admin/admin_index";
    }

    /*
     * 退出登录返回登录界面路由
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    private String logout() { return "login"; }
}
