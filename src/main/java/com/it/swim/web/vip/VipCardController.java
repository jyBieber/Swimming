package com.it.swim.web.vip;

import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.VipCard;
import com.it.swim.entity.VipCard;
import com.it.swim.enums.VipCardStateEnum;
import com.it.swim.service.VipCardService;
import com.it.swim.service.VipService;
import com.it.swim.util.HttpServletRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.swim.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 会员卡相关操作类-会员界面
 */
@Controller
@RequestMapping("/vip")
public class VipCardController {
    @Autowired
    private VipService vipService;
    @Autowired
    private VipCardService vipCardService;

    /*
     * @description: 列出所有会员卡列表-返回为Map类型
     * @param vipId
     * @param request
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
    @RequestMapping(value = "/getVipCardById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getVipCardById(@RequestParam("vipCardId") Long vipCardId){
        Map<String,Object> modelMap = new HashMap<>();
          VipCard vipCard = vipCardService.getVipCardById(vipCardId);
        modelMap.put("success",true);
        modelMap.put("vipCard",vipCard);
        return modelMap;
    }
    /*
     * @description: 根据vipId返回会员卡列表-返回为Layui类型
     * @param vipId
     */
    @RequestMapping(value = "/listVipCardByVipId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listVipCardByVipId(@RequestParam("vipId") Long vipId){
        List<VipCard> vipCardList = vipCardService.getVipCardByVipId(vipId);
        return Layui.data(vipCardList.size(),vipCardList);
    }
}
