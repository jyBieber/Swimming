package com.it.swim.web.coach;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;
import com.it.swim.service.CoursePayService;
import com.it.swim.service.CourseRecordService;
import com.it.swim.util.HttpServletRequestUtil;
import com.it.swim.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 上课记录相关操作类-教练界面
 */
@Controller
@RequestMapping("/coach")
public class CoachCourseController {
    @Autowired
    private CourseRecordService courseRecordService;
    @Autowired
    private CoursePayService coursePayService;

    /*
     * @description: 根据coachId返回游泳记录列表-返回为Layui类型
     * @param coachId
     */
    @RequestMapping(value = "/listCourseRecordByCoachId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourseRecordByCoachId(@RequestParam("coachId") Long coachId){
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordByCoachId(coachId);
        return Layui.data(courseRecordList.size(),courseRecordList);
    }
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
     * @description: 修改上课记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的courseRecord实体，根据解析好的数据修改游泳记录信息
    @RequestMapping(value = "/modifyCourseRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyCourseRecord(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        CourseRecord courseRecord;

        try {
            String courseRecordStr = HttpServletRequestUtil.getString(request, "courseRecordStr");
            // 尝试获取前端传过来的表单string流并将其转换成Student实体类
            courseRecord = mapper.readValue(courseRecordStr, CourseRecord.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try {
            CourseRecordExecution courseRecordExecution = courseRecordService.modifyCourseRecord(courseRecord);
            if (courseRecordExecution.getState() == CourseRecordStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", courseRecordExecution.getStateInfo());
            }
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }
}
