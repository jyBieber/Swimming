package com.it.swim.web.vip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;
import com.it.swim.service.CoachService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 课程缴费相关操作类-会员界面
 */
@Controller
@RequestMapping("/vip")
public class CourseRecordController {
    @Autowired
    private CourseRecordService courseRecordService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private CoursePayService coursePayService;

    /*
     * @description: 根据vipId返回游泳记录列表-返回为Layui类型
     * @param vipId
     * @return Layui
     */
    @RequestMapping(value = "/listCourseRecordByVipId", method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourseRecordByVipId(@RequestParam("vipId") Long vipId) {
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordByVipId(vipId);
        return Layui.data(courseRecordList.size(), courseRecordList);
    }

    /*
     * @description: 添加上课记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的courseRecord实体，根据解析好的数据添加上课记录信息
    @RequestMapping(value = "/addCourseRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCourseRecord(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String courseRecordStr = HttpServletRequestUtil.getString(request, "courseRecordStr");
        if (courseRecordStr == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入选课信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        CourseRecord courseRecord;
        try {
            courseRecord = mapper.readValue(courseRecordStr, CourseRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        CoursePay coursePay = coursePayService.getCoursePayById(courseRecord.getCoursePay().getCoursePayId());
        if (coursePay.getSurplusNum() < 1) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "课程剩余次数为0");
            return modelMap;
        }

        CourseRecordExecution courseRecordExecution = courseRecordService.addCourseRecord(courseRecord);
        if (courseRecordExecution.getState() == CourseRecordStateEnum.SUCCESS.getState()) {
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", courseRecordExecution.getStateInfo());
        }
        return modelMap;
    }
}
