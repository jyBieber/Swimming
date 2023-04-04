package com.it.swim.web.admin;

import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;
import com.it.swim.service.CourseRecordService;
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
 * @description: 上课记录管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class CourseRecordManagementController {
    @Autowired
    private CourseRecordService courseRecordService;

    /*
     * @description: 列出所有上课记录列表-返回为Layui类型
     * @param
     * @return com.it.swim.util.Layui
     */
    @RequestMapping(value = "/listCourseRecord",method = RequestMethod.GET)
    @ResponseBody
    public Layui listCourseRecord(){
        //查询上课记录列表数据
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordList();
        return Layui.data(courseRecordList.size(),courseRecordList);
    }

    /*
     * @description: 根据courseRecordId返回唯一的上课记录信息-返回为Map类型
     * @param courseRecordId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseRecordById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listCourseRecordById(@RequestParam("courseRecordId") Long courseRecordId){
          CourseRecord courseRecord = courseRecordService.getCourseRecordById(courseRecordId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("courseRecord",courseRecord);
        return modelMap;
    }

    /*
     * @description: 添加上课记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的courseRecord实体，根据解析好的数据添加上课记录信息
    @RequestMapping(value = "/addCourseRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCourseRecord(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String courseRecordStr = HttpServletRequestUtil.getString(request,"courseRecordStr");
        if (courseRecordStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入上课记录信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
          CourseRecord courseRecord;
        try {
            courseRecord = mapper.readValue(courseRecordStr,   CourseRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        CourseRecordExecution courseRecordExecution = courseRecordService.addCourseRecord(courseRecord);
        if (courseRecordExecution.getState() == CourseRecordStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg",courseRecordExecution.getStateInfo());
        }
        return modelMap;
    }

    /*
     * @description: 修改上课记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的courseRecord实体，根据解析好的数据修改上课记录信息
    @RequestMapping(value = "/modifyCourseRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyCourseRecord(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String courseRecordStr = HttpServletRequestUtil.getString(request,"courseRecordStr");
        if (courseRecordStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入上课记录信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
          CourseRecord courseRecord;
        try {
            courseRecord = mapper.readValue(courseRecordStr,   CourseRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        if (courseRecord != null && courseRecord.getCourseRecordId() != null){
            CourseRecordExecution courseRecordExecution = courseRecordService.modifyCourseRecord(courseRecord);
            if (courseRecordExecution.getState() == CourseRecordStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",courseRecordExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的上课记录信息Id号");
            return modelMap;
        }
    }

    /*
     * @description: 删除上课记录信息
     * @param courseRecordId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的courseRecordId值删除指定上课记录信息
    @RequestMapping(value = "/deleteCourseRecord", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteCourseRecord(@RequestParam("courseRecordId") Long courseRecordId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (courseRecordId != null){
            CourseRecordExecution courseRecordExecution = courseRecordService.deleteCourseRecord(courseRecordId);
            if (courseRecordExecution.getState() == CourseRecordStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",courseRecordExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的上课记录信息");
        }
        return modelMap;
    }
}
