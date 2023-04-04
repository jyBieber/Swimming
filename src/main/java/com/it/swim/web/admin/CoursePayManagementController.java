package com.it.swim.web.admin;

import com.it.swim.dto.CoursePayExecution;
import com.it.swim.entity.CoursePay;
import com.it.swim.enums.CoursePayStateEnum;
import com.it.swim.enums.CoursePayStateEnum;
import com.it.swim.service.CoursePayService;
import com.it.swim.util.HttpServletRequestUtil;
import com.it.swim.util.Layui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @description: 课程缴费管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class CoursePayManagementController {
    @Autowired
    private CoursePayService coursePayService;

    /*
     * @description: 列出所有课程缴费列表-返回为Layui类型
     * @param
     * @return Layui
     */
    @RequestMapping(value = "/listCoursePay",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCoursePay(){
        //查询课程缴费列表数据
        List<CoursePay> coursePayList = coursePayService.getCoursePayList();
        return Layui.data(coursePayList.size(),coursePayList);
    }

    /*
     * @description: 列出所有课程缴费列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCoursePayMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCoursePayMap(){
        //查询课程缴费列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<CoursePay> coursePayList = coursePayService.getCoursePayList();
        modelMap.put("success",true);
        modelMap.put("coursePayList",coursePayList);
        return modelMap;
    }

    /*
     * @description: 根据coursePayId返回唯一的课程缴费信息-返回为Map类型
     * @param coursePayId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCoursePayById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCoursePayById(@RequestParam("coursePayId") Long coursePayId){
        CoursePay coursePay = coursePayService.getCoursePayById(coursePayId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("coursePay",coursePay);
        return modelMap;
    }

    /*
     * @description: 添加课程缴费信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的coursePay实体，根据解析好的数据添加课程缴费信息
    @RequestMapping(value = "/addCoursePay", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addCoursePay(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String coursePayStr = HttpServletRequestUtil.getString(request,"coursePayStr");
        if (coursePayStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入课程缴费信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        CoursePay coursePay;
        try {
            coursePay = mapper.readValue(coursePayStr, CoursePay.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        CoursePayExecution coursePayExecution = coursePayService.addCoursePay(coursePay);
        if (coursePayExecution.getState() == CoursePayStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",coursePayExecution.getStateInfo());
        }
        return modelMap;
    }

    /*
     * @description: 修改课程缴费信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的coursePay实体，根据解析好的数据修改课程缴费信息
    @RequestMapping(value = "/modifyCoursePay", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyCoursePay(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //1.接受并转化相应的参数，包括课程缴费信息
        //获取前端传过来的游泳记录信息，并将它转换成CoursePay实体类；
        String coursePayStr = HttpServletRequestUtil.getString(request,"coursePayStr");
        ObjectMapper mapper = new ObjectMapper();
        CoursePay coursePay;
        try {
            coursePay = mapper.readValue(coursePayStr,CoursePay.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //2.修改课程缴费
        if(coursePay != null && coursePay.getCoursePayId() != null){
            CoursePayExecution coursePayExecution;
            coursePayExecution = coursePayService.modifyCoursePay(coursePay);
            if (coursePayExecution.getState() == CoursePayStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",coursePayExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的课程缴费信息");
            return modelMap;
        }
        //3.返回结果
    }

    /*
     * @description: 删除课程缴费信息
     * @param coursePayId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的coursePayId值删除指定课程缴费信息
    @RequestMapping(value = "/deleteCoursePay", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteCoursePay(@RequestParam("coursePayId") Long coursePayId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (coursePayId != null) {
            CoursePayExecution coursePayExecution = coursePayService.deleteCoursePay(coursePayId);
            if (coursePayExecution.getState() == CoursePayStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", coursePayExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的游泳记录");
        }
        return modelMap;
    }
}
