package com.it.swim.web.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.dto.CardRecordExecution;
import com.it.swim.entity.CardRecord;
import com.it.swim.entity.VipCard;
import com.it.swim.enums.CardRecordStateEnum;
import com.it.swim.service.CardRecordService;
import com.it.swim.service.VipCardService;
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
 * @description: 游泳记录管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class CardRecordManagementController {
    @Autowired
    private CardRecordService cardRecordService;
    @Autowired
    private VipCardService vipCardService;
    /*
     * @description: 列出所有游泳记录列表-返回为Layui类型
     * @return com.it.swim.util.Layui
     */
    @RequestMapping(value = "/listCardRecord",method = RequestMethod.GET)
    @ResponseBody
    public Layui listCardRecord(){
        //查询游泳记录列表数据
        List<CardRecord> cardRecordList = cardRecordService.getCardRecordList();
        return Layui.data(cardRecordList.size(),cardRecordList);
    }

    /*
     * @description: 根据cardRecordId返回唯一的游泳记录信息-返回为Map类型
     * @param cardRecordId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCardRecordById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listCardRecordById(@RequestParam("cardRecordId") Long cardRecordId){
        CardRecord cardRecord = cardRecordService.getCardRecordById(cardRecordId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("cardRecord",cardRecord);
        return modelMap;
    }

    /*
     * @description: 添加游泳记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的cardRecord实体，根据解析好的数据添加游泳记录信息
    @RequestMapping(value = "/addCardRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCardRecord(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String cardRecordStr = HttpServletRequestUtil.getString(request,"cardRecordStr");
        if (cardRecordStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入游泳记录信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        CardRecord cardRecord;
        try {
            cardRecord = mapper.readValue(cardRecordStr, CardRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        VipCard vipCard = vipCardService.getVipCardById(cardRecord.getVipCard().getVipCardId());

        if(!"已激活".equals(vipCard.getState())){
            modelMap.put("success", false);
            modelMap.put("errMsg", "会员卡未激活");
            return modelMap;
        }

        if (vipCard.getSurplusNum() < 1) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "会员卡剩余次数为0");
            return modelMap;
        }
        CardRecordExecution cardRecordExecution = cardRecordService.addCardRecord(cardRecord);
        if (cardRecordExecution.getState() == CardRecordStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg",cardRecordExecution.getStateInfo());
        }
        return modelMap;
    }

    /*
     * @description: 修改游泳记录信息
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的cardRecord实体，根据解析好的数据修改游泳记录信息
    @RequestMapping(value = "/modifyCardRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyCardRecord(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String cardRecordStr = HttpServletRequestUtil.getString(request,"cardRecordStr");
        if (cardRecordStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入游泳记录信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        CardRecord cardRecord;
        try {
            cardRecord = mapper.readValue(cardRecordStr, CardRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        if (cardRecord != null && cardRecord.getCardRecordId() != null){
            CardRecordExecution cardRecordExecution = cardRecordService.modifyCardRecord(cardRecord);
            if (cardRecordExecution.getState() == CardRecordStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",cardRecordExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的游泳记录信息ID");
            return modelMap;
        }
    }

    /*
     * @description: 删除游泳记录信息
     * @param cardRecordId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的cardRecordId值删除指定游泳记录信息
    @RequestMapping(value = "/deleteCardRecord", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteCardRecord(@RequestParam("cardRecordId") Long cardRecordId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cardRecordId != null){
            CardRecordExecution cardRecordExecution = cardRecordService.deleteCardRecord(cardRecordId);
            if (cardRecordExecution.getState() == CardRecordStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",cardRecordExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的游泳记录信息");
        }
        return modelMap;
    }
}
