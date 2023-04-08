package com.it.swim.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @description: 界面跳转管理类-管理员界面
 */
@Controller
@RequestMapping("admin")
public class AdminPageController {
    /*
     * 添加会员界面路由
     */
    @RequestMapping(value = "/vipAdd",method = RequestMethod.GET)
    private String vipAdd() { return "admin/add_vip"; }

    /*
     * 会员列表界面路由
     */
    @RequestMapping(value = "/vipList",method = RequestMethod.GET)
    private String vipList() { return "admin/vip_list"; }

    /*
     * 添加会员界面路由
     */
    @RequestMapping(value = "/vipCardAdd",method = RequestMethod.GET)
    private String vipCardAdd() { return "admin/add_vipCard"; }

    /*
     * 会员卡列表界面路由
     */
    @RequestMapping(value = "/vipCardList",method = RequestMethod.GET)
    private String vipCardList() { return "admin/vipCard_list"; }

    /*
     * 添加游泳记录界面路由
     */
    @RequestMapping(value = "/cardRecordAdd",method = RequestMethod.GET)
    private String cardRecordAdd() { return "admin/add_cardRecord"; }

    /*
     * 游泳记录列表界面路由
     */
    @RequestMapping(value = "/cardRecordList",method = RequestMethod.GET)
    private String cardRecordList() { return "admin/cardRecord_list"; }

    /*
     * 添加教练界面路由
     */
    @RequestMapping(value = "/coachAdd",method = RequestMethod.GET)
    private String coachAdd() { return "admin/add_coach"; }

    /*
     * 教练列表界面路由
     */
    @RequestMapping(value = "/coachList",method = RequestMethod.GET)
    private String coachList() { return "admin/coach_list"; }

    /*
     * 添加课程缴费界面路由
     */
    @RequestMapping(value = "/coursePayAdd",method = RequestMethod.GET)
    private String coursePayAdd() { return "admin/add_coursePay"; }

    /*
     * 课程缴费列表界面路由
     */
    @RequestMapping(value = "/coursePayList",method = RequestMethod.GET)
    private String coursePayList() { return "admin/coursePay_list"; }

    /*
     * 添加课程信息界面路由
     */
    @RequestMapping(value = "/courseAdd",method = RequestMethod.GET)
    private String courseAdd() { return "admin/add_course"; }

    /*
     * 课程列表界面路由
     */
    @RequestMapping(value = "/courseList",method = RequestMethod.GET)
    private String courseList() { return "admin/course_list"; }

    /*
     * 添加上课记录信息界面路由
     */
    @RequestMapping(value = "/courseRecordAdd",method = RequestMethod.GET)
    private String courseRecordAdd() { return "admin/add_courseRecord"; }

    /*
     * 上课记录列表界面路由
     */
    @RequestMapping(value = "/courseRecordList",method = RequestMethod.GET)
    private String courseRecordList() { return "admin/courseRecord_list"; }


    /*
     * 营业额统计
     */
    @RequestMapping(value = "/turnoverStatistics",method = RequestMethod.GET)
    private String turnoverStatistics() { return "admin/turnover_list"; }

    /*
     * 教练业绩统计
     */
    @RequestMapping(value = "/performanceStatistics",method = RequestMethod.GET)
    private String performanceStatistics() { return "admin/performance_list"; }


    /*
     * 个人信息界面路由
     */
    @RequestMapping(value = "/adminInfo",method = RequestMethod.GET)
    private String adminModify() { return "admin/info"; }

    /*
     * 修改密码界面路由
     */
    @RequestMapping(value = "/passwordModify",method = RequestMethod.GET)
    private String passwordModify() { return "admin/password"; }
}
