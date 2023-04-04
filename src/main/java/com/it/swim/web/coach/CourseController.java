package com.it.swim.web.coach;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CourseExecution;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.Course;
import com.it.swim.enums.CourseStateEnum;
import com.it.swim.service.CoursePayService;
import com.it.swim.service.CourseService;
import com.it.swim.util.HttpServletRequestUtil;
import com.it.swim.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 课程相关操作类-教练界面
 */
@Controller
@RequestMapping("/coach")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /*
     * @description: 根据coachId返回游泳记录列表-返回为Layui类型
     * @param coachId
     */
    @RequestMapping(value = "/listCourseByCoachId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourseByCoachId(@RequestParam("coachId") Long coachId){
        List<Course> courseList = courseService.getCourseByCoachId(coachId);
        return Layui.data(courseList.size(),courseList);
    }
}
