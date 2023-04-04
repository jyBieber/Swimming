package com.it.swim.web.coach;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @description: 跳转界面相关类-教练界面
 */
@Controller
@RequestMapping("coach")
public class CoachPageController {
    /*
     * 教练主界面路由
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index() { return "coach/coach_index"; }

    /*
     * 基本设置界面路由
     */
    @RequestMapping(value = "/set",method = RequestMethod.GET)
    private String set() { return "coach/set"; }

    /*
     * 课程信息界面路由
     */
    @RequestMapping(value = "/course",method = RequestMethod.GET)
    private String course() { return "coach/course_list"; }

    /*
     * 上课记录界面路由
     */
    @RequestMapping(value = "/courseRecord",method = RequestMethod.GET)
    private String courseRecord() { return "coach/courseRecord_list"; }

}
