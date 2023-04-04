package com.it.swim.web.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CourseExecution;
import com.it.swim.entity.Course;
import com.it.swim.enums.CourseStateEnum;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 课程管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class CourseManagementController {
    @Autowired
    private CourseService courseService;

    /*
     * @description: 列出所有课程列表-返回为Layui类型
     * @param
     * @return Layui
     */
    @RequestMapping(value = "/listCourse",method = RequestMethod.GET)
    @ResponseBody
    public Layui listCourse(){
        //查询课程列表数据
        List<Course> courseList = courseService.getCourseList();
        return Layui.data(courseList.size(),courseList);
    }

    /*
     * @description: 列出所有课程缴费列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCourseMap(){
        //查询课程缴费列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Course> courseList = courseService.getCourseList();
        modelMap.put("success",true);
        modelMap.put("courseList",courseList);
        return modelMap;
    }
    
    /*
     * @description: 根据courseId返回唯一的课程信息-返回为Map类型
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listCourseById(@RequestParam("courseId") Long courseId){
        Course course = courseService.getCourseById(courseId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("course",course);
        return modelMap;
    }

    /*
     * @description: 添加课程信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的course实体，根据解析好的数据添加课程信息
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCourse(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String courseStr = HttpServletRequestUtil.getString(request,"courseStr");
        if (courseStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入课程信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Course course;
        try {
            course = mapper.readValue(courseStr,   Course.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        CourseExecution courseExecution = courseService.addCourse(course);
        if (courseExecution.getState() == CourseStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg",courseExecution.getStateInfo());
        }
        return modelMap;
    }

    /*
     * @description: 修改课程信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的course实体，根据解析好的数据修改课程信息
    @RequestMapping(value = "/modifyCourse", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyCourse(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String courseStr = HttpServletRequestUtil.getString(request,"courseStr");
        if (courseStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入课程信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Course course;
        try {
            course = mapper.readValue(courseStr, Course.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        if (course != null && course.getCourseId() != null){
            CourseExecution courseExecution = courseService.modifyCourse(course);
            if (courseExecution.getState() == CourseStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",courseExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的课程信息Id号");
            return modelMap;
        }
    }
    
    /*
     * @description: 删除课程信息
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的courseId值删除指定课程信息
    @RequestMapping(value = "/deleteCourse",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteCourse(@RequestParam("courseId") Long courseId){
        Map<String,Object> modelMap = new HashMap<>();
        courseService.deleteCourse(courseId);
        modelMap.put("success",true);
        return modelMap;
    }
}
