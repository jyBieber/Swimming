package com.it.swim.web.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CourseExecution;
import com.it.swim.entity.CoachStatis;
import com.it.swim.entity.Course;
import com.it.swim.entity.TypeStatis;
import com.it.swim.enums.CourseStateEnum;
import com.it.swim.service.CourseService;
import com.it.swim.service.StatisticsService;
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
 * @description: 统计管理类-管理员界面
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    /*
     * @description: 营业额统计
     * @param
     * @return com.it.swim.util.Layui
     */
    @RequestMapping(value = "/turnoverStatistics",method = RequestMethod.GET)
    @ResponseBody
    private Layui turnoverStatistics(){
        //营业额统计
        List<TypeStatis> courseList = statisticsService.turnoverStatistics();
        return Layui.data(courseList.size(),courseList);
    }

    /*
     * @description: 教练业绩统计
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/performanceStatistics",method = RequestMethod.GET)
    @ResponseBody
    private Layui performanceStatistics( ){
        //教练业绩统计
        List<CoachStatis> courseList = statisticsService.performanceStatistics();
        return Layui.data(courseList.size(),courseList);
    }


}
