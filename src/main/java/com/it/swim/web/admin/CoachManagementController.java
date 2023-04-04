package com.it.swim.web.admin;

import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.CoachExecution;
import com.it.swim.entity.Coach;
import com.it.swim.enums.CoachStateEnum;
import com.it.swim.service.CoachService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 教练管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class CoachManagementController {
    @Autowired
    private CoachService coachService;

    /*
     * @description: 列出所有教练列表-返回为Layui类型
     * @param
     * @return   Layui
     */
    @RequestMapping(value = "/listCoach",method = RequestMethod.GET)
    @ResponseBody
    public Layui listCoach(){
        //查询教练列表数据
        List<Coach> coachList = coachService.getCoachList();
        return Layui.data(coachList.size(), coachList);
    }

    /*
     * @description: 列出所有教练列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCoachMap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listCoachMap(){
        //查询教练列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<  Coach> coachList = coachService.getCoachList();
        modelMap.put("success",true);
        modelMap.put("coachList",coachList);
        return modelMap;
    }

    /*
     * @description: 根据coachId返回唯一的教练信息-返回为Map类型
     * @param coachId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCoachById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listCoachById(@RequestParam("coachId") Long coachId){
          Coach coach = coachService.getCoachById(coachId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("coach",coach);
        return modelMap;
    }

    /*
     * @description: 添加教练信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的coach实体，根据解析好的数据添加教练信息
    @RequestMapping(value = "/addCoach",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addCoach(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的教练信息，并将它转换成实体类；
        //同时获取前端传递过来的文件流，将它接受到coachImg里面去
        String coachStr = HttpServletRequestUtil.getString(request,"coachStr");
        ObjectMapper mapper = new ObjectMapper();
        Coach coach;
        try {
            coach = mapper.readValue(coachStr, Coach.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile coachImg;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            coachImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("coachImg");
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }

        //2.添加教练信息
        if(coach != null && coachImg != null){
            CoachExecution coachExecution;
            try {
                ImageHolder imageHolder = new ImageHolder(coachImg.getOriginalFilename(),coachImg.getInputStream());
                coachExecution = coachService.addCoach(coach,imageHolder);
                if (coachExecution.getState() == CoachStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",coachExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入教练信息");
            return modelMap;
        }
        //3.返回结果
    }

    /*
     * @description: 修改教练信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的coach实体，根据解析好的数据修改教练信息
    @RequestMapping(value = "/modifyCoach",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyCoach(HttpServletRequest request) throws IOException {
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
                    imageHolder = new ImageHolder(coachImg.getOriginalFilename(),coachImg.getInputStream());
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
     * @description: 删除教练信息
     * @param coachId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的coachId值删除指定教练信息
    @RequestMapping(value = "/deleteCoach",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteCoach(@RequestParam("coachId") Long coachId){
        Map<String,Object> modelMap = new HashMap<>();
        coachService.deleteCoach(coachId);
        modelMap.put("success",true);
        return modelMap;
    }
}
