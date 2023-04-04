package com.it.swim.web.admin;

import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.VipExecution;
import com.it.swim.entity.Vip;
import com.it.swim.enums.VipStateEnum;
import com.it.swim.service.VipService;
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
 * @description: 会员管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class VipManagementController {
    @Autowired
    private VipService vipService;

    /*
     * @description: 列出所有会员列表-返回为Layui类型
     * @param
     * @return com.it.swim.util.Layui
     */
    @RequestMapping(value = "/listVip",method = RequestMethod.GET)
    @ResponseBody
    public Layui listVip(){
        //查询显示列表数据
        List<Vip> vipList = vipService.getVipList();
        //System.out.println(vipList);
        return Layui.data(vipList.size(),vipList);
    }

    /*
     * @description: 列出所有会员列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listVipMap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listVipMap(){
        //查询会员列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Vip> vipList = vipService.getVipList();
        modelMap.put("success",true);
        modelMap.put("vipList",vipList);
        return modelMap;
    }

    /*
     * @description: 根据vipId返回唯一的会员信息-返回为Map类型
     * @param vipId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listVipById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listVipById(@RequestParam("vipId") Long vipId){
        Vip vip = vipService.getVipById(vipId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("vip",vip);
        return modelMap;
    }

    /*
     * @description: 添加会员信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的vip实体，根据解析好的数据添加会员信息
    @RequestMapping(value = "/addVip",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addVip(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的会员信息，并将它转换成实体类；
        //同时获取前端传递过来的文件流，将它接受到vipImg里面去
        String vipStr = HttpServletRequestUtil.getString(request,"vipStr");
        if (vipStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入会员信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Vip vip;
        try {
            vip = mapper.readValue(vipStr,Vip.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile vipImg;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        vipImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("vipImg");

        //2.添加会员信息
        VipExecution vipExecution;
        try {
            ImageHolder imageHolder = null;
            if (vipImg != null){
                imageHolder = new ImageHolder(vipImg.getOriginalFilename(),vipImg.getInputStream());
            }
            vipExecution = vipService.addVip(vip,imageHolder);
            if (vipExecution.getState() == VipStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",vipExecution.getStateInfo());
            }
        } catch (IOException e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
        //3.返回结果
    }

    /*
     * @description: 修改会员信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的vip实体，根据解析好的数据修改会员信息
    @RequestMapping(value = "/modifyVip",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyCoach(HttpServletRequest request) throws IOException {
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
        try {
            ImageHolder imageHolder = null;
            // 开始进行会员信息变更操作
            if (vipImg != null){
                imageHolder = new ImageHolder(vipImg.getOriginalFilename(),vipImg.getInputStream());
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
        return modelMap;
    }

    /*
     * @description: 删除会员信息
     * @param vipId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的vipId值删除指定会员信息
    @RequestMapping(value = "/deleteVip",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteVip(@RequestParam("vipId") Long vipId){
        Map<String,Object> modelMap = new HashMap<>();
        vipService.deleteVip(vipId);
        modelMap.put("success",true);
        return modelMap;
    }
}
