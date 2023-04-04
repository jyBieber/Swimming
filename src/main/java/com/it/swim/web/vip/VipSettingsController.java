package com.it.swim.web.vip;

import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.VipExecution;
import com.it.swim.entity.Vip;
import com.it.swim.enums.VipStateEnum;
import com.it.swim.service.VipService;
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
 * @description: 设置管理类-会员界面
 */
@Controller
@RequestMapping("/vip")
public class VipSettingsController {
    @Autowired
    private VipService vipService;
    /*
     * @description: 获取会员信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getVipInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getVipInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long vipId = (long) request.getSession().getAttribute("vipId");
        Vip vip = vipService.getVipById(vipId);
        modelMap.put("success",true);
        modelMap.put("vip",vip);
        return modelMap;
    }

    /*
     * @description: 修改会员信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/modifyVip", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyVip(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        Vip vip;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile vipImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            vipImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("vipImg");
        }

        try {
            String vipStr = HttpServletRequestUtil.getString(request, "vipStr");
            // 尝试获取前端传过来的表单string流并将其转换成Vip实体类
            vip = mapper.readValue(vipStr, Vip.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // 非空判断
        if (vip != null) {
            try {
                ImageHolder imageHolder = null;
                // 开始进行教练信息变更操作
                if (vipImg != null){
                    imageHolder = new ImageHolder(vipImg.getOriginalFilename(), vipImg.getInputStream());
                }
                VipExecution vipExecution = vipService.modifyVip(vip, imageHolder);
                if (vipExecution.getState() == VipStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", vipExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的会员信息");
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
        long vipId = (long) request.getSession().getAttribute("vipId");
        Vip vip = vipService.getVipById(vipId);
        if (!vip.getPassword().equals(oldPassword)){
            modelMap.put("success",false);
            modelMap.put("errMsg", "原密码不正确");
            return modelMap;
        }
        vip.setPassword(newPassword);
        VipExecution vipExecution = vipService.modifyVip(vip,null);
        if (vipExecution.getState() == VipStateEnum.SUCCESS.getState()){
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", vipExecution.getStateInfo());
        }
        return modelMap;
    }
}
