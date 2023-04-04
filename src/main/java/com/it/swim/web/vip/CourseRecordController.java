package com.it.swim.web.vip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CourseRecord;
import com.it.swim.entity.CourseRecord;
import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;
import com.it.swim.service.CoachService;
import com.it.swim.service.CourseRecordService;
import com.it.swim.service.CourseRecordService;
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
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /*
     * @description: 根据coursePayId返回游泳记录列表-返回为Map类型
     * @param coursePayId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseRecordMapByCoursePayId",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCourseRecordMapByCoursePayId(@RequestParam("coursePayId") Long coursePayId){
        Map<String,Object> modelMap = new HashMap<>();
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordByCoursePayId(coursePayId);
        modelMap.put("success",true);
        modelMap.put("courseRecordList",courseRecordList);
        return modelMap;
    }

    /*
     * @description: 根据coursePayId返回游泳记录列表-返回为Layui类型
     * @param coursePayId
     * @return Layui
     */
    @RequestMapping(value = "/listCourseRecordByCoursePayId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourseRecordByCoursePayId(@RequestParam("coursePayId") Long coursePayId){
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordByCoursePayId(coursePayId);
        return Layui.data(courseRecordList.size(),courseRecordList);
    }

    /*
     * @description: 根据coursePayId返回游泳记录列表-返回为Layui类型
     * @param coursePayId
     * @return Layui
     */
    @RequestMapping(value = "/listCourseRecordByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourseRecordByCourseId(@RequestParam("courseId") Long courseId){
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordByCourseId(courseId);
        return Layui.data(courseRecordList.size(),courseRecordList);
    }
    /*
     * @description: 根据vipId返回游泳记录列表-返回为Layui类型
     * @param vipId
     * @return Layui
     */
    @RequestMapping(value = "/listCourseRecordByVipId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourseRecordByVipId(@RequestParam("vipId") Long vipId){
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordByVipId(vipId);
        return Layui.data(courseRecordList.size(),courseRecordList);
    }

    /*
     * @description: 列出所有教练列表-返回为Layui类型
     * @param
     * @return   Layui
     *//*
    @RequestMapping(value = "/listCoach",method = RequestMethod.GET)
    @ResponseBody
    public Layui listCoach(){
        //查询教练列表数据
        List<Coach> coachList = coachService.getCoachList();
        return Layui.data(coachList.size(), coachList);
    }

    *//*
     * @description: 列出所有教练列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *//*
    @RequestMapping(value = "/listCoachMap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listCoachMap(){
        //查询教练列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Coach> coachList = coachService.getCoachList();
        modelMap.put("success",true);
        modelMap.put("coachList",coachList);
        return modelMap;
    }*/

    /*
     * @description: 添加上课记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的courseRecord实体，根据解析好的数据添加上课记录信息
    @RequestMapping(value = "/addCourseRecord",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addCourseRecord(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的上课记录信息，并将它转换成实体类；
        String courseRecordStr = HttpServletRequestUtil.getString(request,"courseRecordStr");
        if (courseRecordStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入上课记录信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        CourseRecord courseRecord;
        try {
            courseRecord = mapper.readValue(courseRecordStr,CourseRecord.class);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        //2.添加上课记录信息
        CourseRecordExecution courseRecordExecution;
        courseRecordExecution = courseRecordService.addCourseRecord(courseRecord);
        if (courseRecordExecution.getState() == CourseRecordStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",courseRecordExecution.getStateInfo());
        }

        return modelMap;
    }
}
