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
}
