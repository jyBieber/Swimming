package com.it.swim.web.admin;

import com.it.swim.dto.CardRecordExecution;
import com.it.swim.entity.CardRecord;
import com.it.swim.enums.CardRecordStateEnum;
import com.it.swim.service.CardRecordService;
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
import java.util.*;

/*
 * @description: 游泳记录管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class CardRecordManagementController {
    @Autowired
    private CardRecordService cardRecordService;

    /*
     * @description: 列出所有游泳记录列表-返回为Layui类型
     * @param
     * @return Layui
     */
    @RequestMapping(value = "/listCardRecord",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCardRecord(){
        //查询游泳记录列表数据
        List<CardRecord> cardRecordList = cardRecordService.getCardRecordList();
        return Layui.data(cardRecordList.size(),cardRecordList);
    }

    /*
     * @description: 列出所有游泳记录列表-返回为Map类型
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCardRecordMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCardRecordMap(){
        //查询游泳记录列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<CardRecord> cardRecordList = cardRecordService.getCardRecordList();
        modelMap.put("success",true);
        modelMap.put("cardRecordList",cardRecordList);
        return modelMap;
    }

    /*
     * @description: 根据CardRecordId返回唯一的游泳记录信息-返回为Map类型
     * @param cardRecordId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCardRecordById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCardRecordById(@RequestParam("cardRecordId") Long cardRecordId){
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
    private Map<String,Object> addCardRecord(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
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

        CardRecordExecution cardRecordExecution = cardRecordService.addCardRecord(cardRecord);
        if (cardRecordExecution.getState() == CardRecordStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
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
    private Map<String,Object> modifyCardRecord(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //1.接受并转化相应的参数，包括游泳记录信息
        //获取前端传过来的游泳记录信息，并将它转换成CardRecord实体类；
        String cardRecordStr = HttpServletRequestUtil.getString(request,"cardRecordStr");
        ObjectMapper mapper = new ObjectMapper();
        CardRecord cardRecord;
        try {
            cardRecord = mapper.readValue(cardRecordStr,CardRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //2.修改游泳记录
        if(cardRecord != null && cardRecord.getCardRecordId() != null){
            CardRecordExecution cardRecordExecution;
            cardRecordExecution = cardRecordService.modifyCardRecord(cardRecord);
            if (cardRecordExecution.getState() == CardRecordStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",cardRecordExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入游泳记录Id");
            return modelMap;
        }
        //3.返回结果
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
        if (cardRecordId != null) {
            CardRecordExecution cardRecordExecution = cardRecordService.deleteCardRecord(cardRecordId);
            if (cardRecordExecution.getState() == CardRecordStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", cardRecordExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的游泳记录");
        }
        return modelMap;
    }
}
