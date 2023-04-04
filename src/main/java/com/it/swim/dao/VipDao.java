package com.it.swim.dao;

import com.it.swim.entity.Vip;

import java.util.List;

/*
 * @description: 会员实体类dao层接口
 */
public interface VipDao {
    /*
     * @description: 查询所有会员列表
     * @param 
     * @return java.util.List< Vip>
     */
    List<Vip> queryVip();

    /*
     * @description: 通过vipId查询指定会员信息
     * @param vipId
     * @return  Vip
     */
    Vip queryVipById(long vipId);
    
    /*
     * @description: 新增会员信息
     * @param vip
     * @return int
     */
    int addVip(Vip vip);
    
    /*
     * @description: 修改会员信息
     * @param vip
     * @return int
     */
    int modifyVip(Vip vip);
    
    /*
     * @description: 通过vipId删除指定会员
     * @param vipId
     * @return int
     */
    int deleteVip(long vipId);
}
