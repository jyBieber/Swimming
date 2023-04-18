package com.it.swim.web.vip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @description: 跳转界面相关类-会员界面
 */
@Controller
@RequestMapping("vip")
public class VipPageController {
    /*
     * 会员主界面路由
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index() { return "vip/vip_index"; }

    /*
     * 基本设置界面路由
     */
    @RequestMapping(value = "/set",method = RequestMethod.GET)
    private String set() { return "vip/set"; }

    /*
     * 会员卡列表界面路由
     */
    @RequestMapping(value = "/vipCardList",method = RequestMethod.GET)
    private String vipCardList() { return "vip/vipCard_list"; }

    /*
     * 游泳记录界面路由
     */
    @RequestMapping(value = "/cardRecordList",method = RequestMethod.GET)
    private String cardRecordList() { return "vip/cardRecord_list"; }

    /*
     * 课程缴费列表界面路由
     */
    @RequestMapping(value = "/coursePayList",method = RequestMethod.GET)
    private String coursePayList() { return "vip/coursePay_list"; }

    /*
     * 上课记录界面路由
     */
    @RequestMapping(value = "/courseRecordList",method = RequestMethod.GET)
    private String courseRecordList() { return "vip/courseRecord_list"; }

    /*
     * 预约课程界面路由
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    private String addCourseRecord() { return "vip/add_courseRecord"; }

}
