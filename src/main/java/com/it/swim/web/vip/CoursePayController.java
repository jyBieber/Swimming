package com.it.swim.web.vip;

import com.it.swim.entity.CoursePay;
import com.it.swim.service.CoursePayService;
import com.it.swim.service.VipService;
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
public class CoursePayController {
    @Autowired
    private VipService vipService;
    @Autowired
    private CoursePayService coursePayService;

    /*
     * @description: 列出所有课程缴费列表-返回为Map类型
     * @param vipId
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCoursePayMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCoursePayMap(HttpServletRequest request){
        //查询课程列表数据
        Map<String,Object> modelMap = new HashMap<>();
        long vipId = (long) request.getSession().getAttribute("vipId");
        List<CoursePay> coursePayList = coursePayService.getCoursePayByVipId(vipId);
        modelMap.put("success",true);
        modelMap.put("coursePayList",coursePayList);
        return modelMap;
    }

    /*
     * @description: 根据coursePayId返回唯一的课程缴费信息-返回为Map类型
     * @param coursePayId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getCoursePayById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getCoursePayById(@RequestParam("coursePayId") Long coursePayId){
        Map<String,Object> modelMap = new HashMap<>();
          CoursePay coursePay = coursePayService.getCoursePayById(coursePayId);
        modelMap.put("success",true);
        modelMap.put("coursePay",coursePay);
        return modelMap;
    }
    /*
     * @description: 根据vipId返回课程缴费列表-返回为Layui类型
     * @param vipId
     */
    @RequestMapping(value = "/listCoursePayByVipId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCoursePayByVipId(@RequestParam("vipId") Long vipId){
        List<CoursePay> coursePayList = coursePayService.getCoursePayByVipId(vipId);
        return Layui.data(coursePayList.size(),coursePayList);
    }
}
