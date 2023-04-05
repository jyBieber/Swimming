package com.it.swim.web.vip;

import com.it.swim.entity.CardRecord;
import com.it.swim.entity.VipCard;
import com.it.swim.service.CardRecordService;
import com.it.swim.service.VipCardService;
import com.it.swim.service.VipService;
import com.it.swim.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 会员卡相关操作类-会员界面
 */
@Controller
@RequestMapping("/vip")
public class CardRecordController {
    @Autowired
    private CardRecordService cardRecordService;

    /*
     * @description: 根据vipCardId返回游泳记录列表-返回为Map类型
     * @param vipCardId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    // @RequestMapping(value = "/listCardRecordMapByVipCardId",method = RequestMethod.GET)
    // @ResponseBody
    // private Map<String,Object> listCardRecordMapByVipCardId(@RequestParam("vipCardId") Long vipCardId){
    //     Map<String,Object> modelMap = new HashMap<>();
    //     List<CardRecord> cardRecordList = cardRecordService.getCardRecordByVipCardId(vipCardId);
    //     modelMap.put("success",true);
    //     modelMap.put("cardRecordList",cardRecordList);
    //     return modelMap;
    // }

    /*
     * @description: 根据vipCardId返回游泳记录列表-返回为Layui类型
     * @param vipCardId
     * @return Layui
     */
    // @RequestMapping(value = "/listCardRecordByVipCardId",method = RequestMethod.GET)
    // @ResponseBody
    // private Layui listCardRecordByVipCardId(@RequestParam("vipCardId") Long vipCardId){
    //     List<CardRecord> cardRecordList = cardRecordService.getCardRecordByVipCardId(vipCardId);
    //     return Layui.data(cardRecordList.size(),cardRecordList);
    // }

    /*
     * @description: 根据vipId返回游泳记录列表-返回为Layui类型
     * @param vipId
     * @return Layui
     */
    @RequestMapping(value = "/listCardRecordByVipId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCardRecordByVipId(@RequestParam("vipId") Long vipId){
        List<CardRecord> cardRecordList = cardRecordService.getCardRecordByVipId(vipId);
        return Layui.data(cardRecordList.size(),cardRecordList);
    }
}
