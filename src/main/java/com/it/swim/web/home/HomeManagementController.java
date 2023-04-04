package com.it.swim.web.home;

import com.it.swim.entity.Admin;
import com.it.swim.entity.Coach;
import com.it.swim.entity.Vip;
import com.it.swim.service.AdminService;
import com.it.swim.service.VipService;
import com.it.swim.service.CoachService;
import com.it.swim.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*
 * @description: 登录相关管理类
 */
@Controller
@RequestMapping("/home")
public class HomeManagementController {
    @Autowired
    private VipService vipService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private AdminService adminService;

    /*
     * @description: 登录信息处理
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> login(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        int type = HttpServletRequestUtil.getInt(request,"type");
        long userId = HttpServletRequestUtil.getLong(request,"userId");
        String inputPassword = HttpServletRequestUtil.getString(request,"password");
        String realPassword;

        if (type == 0){
            Vip vip = vipService.getVipById(userId);
            realPassword = vip.getPassword();
            if (realPassword.equals(inputPassword)){
                request.getSession().setAttribute("vipId",userId);
                modelMap.put("redirect",true);
                modelMap.put("url","/home/vipIndex?vipId="+vip.getVipId());
            }else {
                modelMap.put("redirect",false);
            }
        }else if (type == 1){
            Coach coach = coachService.getCoachById(userId);
            realPassword = coach.getPassword();
            if (realPassword.equals(inputPassword)){
                request.getSession().setAttribute("coachId",userId);
                modelMap.put("redirect",true);
                modelMap.put("url","/home/coachIndex?coachId="+coach.getCoachId());
            }else {
                modelMap.put("redirect",false);
            }
        }else if (type == 2){
            Admin admin = adminService.getAdminById(userId);
            realPassword = admin.getPassword();
            if (realPassword.equals(inputPassword)){
                request.getSession().setAttribute("adminId",userId);
                modelMap.put("redirect",true);
                modelMap.put("url","/home/adminIndex?adminName="+admin.getAdminName());
            }else {
                modelMap.put("redirect",false);
            }
        }else {
            modelMap.put("redirect",false);
        }
        return modelMap;
    }
}
