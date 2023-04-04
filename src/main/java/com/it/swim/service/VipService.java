package com.it.swim.service;

import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.VipExecution;
import com.it.swim.entity.Vip;
import java.util.List;

public interface VipService {
    /*
     * @description: 查询全部会员信息
     * @param
     * @return java.util.List< Vip>
     */
    List<Vip> getVipList();

    /*
     * @description: 通过会员ID获取指定会员信息
     * @param vipId
     * @return  Vip
     */
    Vip getVipById(long vipId);

    /*
     * @description: 新增会员信息
     * @param vip
     * @param imageHolder
     * @return  VipExecution
     */
    VipExecution addVip(Vip vip, ImageHolder imageHolder);

    /*
     * @description: 修改会员信息
     * @param vip
     * @param imageHolder
     * @return  VipExecution
     */
    VipExecution modifyVip(Vip vip, ImageHolder imageHolder);

    /*
     * @description: 删除指定会员信息
     * @param vipId
     * @return  VipExecution
     */
    VipExecution deleteVip(long vipId);
}
