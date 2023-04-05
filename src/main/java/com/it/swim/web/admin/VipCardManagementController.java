package com.it.swim.web.admin;

import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.VipCard;
import com.it.swim.enums.VipCardStateEnum;
import com.it.swim.enums.VipCardStateEnum;
import com.it.swim.service.VipCardService;
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
 * @description: 会员卡管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class VipCardManagementController {
    @Autowired
    private VipCardService vipCardService;

    /*
     * @description: 列出所有会员卡列表-返回为Layui类型
     * @param
     * @return com.it.swim.util.Layui
     */
    @RequestMapping(value = "/listVipCard",method = RequestMethod.GET)
    @ResponseBody
    private Layui listVipCard(){
        //查询会员卡列表数据
        List<VipCard> vipCardList = vipCardService.getVipCardList();
        return Layui.data(vipCardList.size(),vipCardList);
    }

    /*
     * @description: 列出所有会员卡列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listVipCardMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listVipCardMap(){
        //查询会员卡列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<VipCard> vipCardList = vipCardService.getVipCardList();
        modelMap.put("success",true);
        modelMap.put("vipCardList",vipCardList);
        return modelMap;
    }

    /*
     * @description: 根据vipCardId返回唯一的会员卡信息-返回为Map类型
     * @param vipCardId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listVipCardById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listVipCardById(@RequestParam("vipCardId") Long vipCardId){
        VipCard vipCard = vipCardService.getVipCardById(vipCardId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("vipCard",vipCard);
        return modelMap;
    }

    /*
     * @description: 添加会员卡信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的vipCard实体，根据解析好的数据添加会员卡信息
    @RequestMapping(value = "/addVipCard", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addVipCard(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String vipCardStr = HttpServletRequestUtil.getString(request,"vipCardStr");
        if (vipCardStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入会员卡信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        VipCard vipCard;
        try {
            vipCard = mapper.readValue(vipCardStr, VipCard.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        VipCardExecution vipCardExecution = vipCardService.addVipCard(vipCard);
        if (vipCardExecution.getState() == VipCardStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",vipCardExecution.getStateInfo());
        }
        return modelMap;
    }

    /*
     * @description: 修改会员卡信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的vipCard实体，根据解析好的数据修改会员卡信息
    @RequestMapping(value = "/modifyVipCard", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyVipCard(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //1.接受并转化相应的参数，包括会员卡信息
        //获取前端传过来的会员卡信息，并将它转换成VipCard实体类；
        String vipCardStr = HttpServletRequestUtil.getString(request,"vipCardStr");
        ObjectMapper mapper = new ObjectMapper();
        VipCard vipCard;
        try {
            vipCard = mapper.readValue(vipCardStr,VipCard.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //2.修改会员卡
        if(vipCard != null && vipCard.getVipCardId() != null){
            VipCardExecution vipCardExecution;
            vipCardExecution = vipCardService.modifyVipCard(vipCard);
            if (vipCardExecution.getState() == VipCardStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",vipCardExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的会员卡信息");
            return modelMap;
        }
        //3.返回结果
    }

    /*
     * @description: 删除会员卡信息
     * @param vipCardId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的vipCardId值删除指定会员卡信息
    @RequestMapping(value = "/deleteVipCard", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteVipCard(@RequestParam("vipCardId") Long vipCardId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (vipCardId != null) {
            VipCardExecution vipCardExecution = vipCardService.deleteVipCard(vipCardId);
            if (vipCardExecution.getState() == VipCardStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", vipCardExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的会员卡");
        }
        return modelMap;
    }
}
