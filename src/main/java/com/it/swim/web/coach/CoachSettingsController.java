package com.it.swim.web.coach;

import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.CoachExecution;
import com.it.swim.entity.Coach;
import com.it.swim.enums.CoachStateEnum;
import com.it.swim.service.CoachService;
import com.it.swim.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * @description: 设置管理类-教练界面
 */
@Controller
@RequestMapping("/coach")
public class CoachSettingsController {
    @Autowired
    private CoachService coachService;

    /*
     * @description: 获取教练信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getCoachInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getCoachInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long coachId = (long) request.getSession().getAttribute("coachId");
          Coach coach = coachService.getCoachById(coachId);
        modelMap.put("success",true);
        modelMap.put("coach",coach);
        return modelMap;
    }

    /*
     * @description: 修改教练信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/modifyCoach", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyCoach(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
          Coach coach;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile coachImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            coachImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("coachImg");
        }

        try {
            String coachStr = HttpServletRequestUtil.getString(request, "coachStr");
            // 尝试获取前端传过来的表单string流并将其转换成Coach实体类
            coach = mapper.readValue(coachStr,   Coach.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // 非空判断
        if (coach != null) {
            try {
                ImageHolder imageHolder = null;
                // 开始进行教练信息变更操作
                if (coachImg != null){
                    imageHolder = new ImageHolder(coachImg.getOriginalFilename(), coachImg.getInputStream());
                }
                CoachExecution coachExecution = coachService.modifyCoach(coach, imageHolder);
                if (coachExecution.getState() == CoachStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", coachExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的教练信息");
        }
        return modelMap;
    }

    /*
     * @description: 修改密码
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应类型，根据解析好的数据修改密码
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyPassword(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String oldPassword = HttpServletRequestUtil.getString(request,"oldPassword");
        String newPassword = HttpServletRequestUtil.getString(request,"newPassword");
        long coachId = (long) request.getSession().getAttribute("coachId");
          Coach coach = coachService.getCoachById(coachId);
        if (!coach.getPassword().equals(oldPassword)){
            modelMap.put("success",false);
            modelMap.put("errMsg", "原密码不正确");
            return modelMap;
        }
        coach.setPassword(newPassword);
        CoachExecution coachExecution = coachService.modifyCoach(coach,null);
        if (coachExecution.getState() == CoachStateEnum.SUCCESS.getState()){
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", coachExecution.getStateInfo());
        }
        return modelMap;
    }
}
