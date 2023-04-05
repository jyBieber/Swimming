package com.it.swim.dao;

import com.it.swim.entity.VipCard;

import java.util.List;

/*
 * @description: 会员卡实体类dao层接口
 */
public interface VipCardDao {
    /*
     * @description: 查询所有会员卡列表
     * @return java.util.List<com.it.swim.entity.VipCard>
     */
    List<VipCard> queryVipCard();

    /*
     * @description: 通过vipCardId查询指定会员卡信息
     * @param vipCardId
     * @return com.it.swim.entity.VipCard
     */
    VipCard queryVipCardById(long vipCardId);

    /*
     * @description: 通过vipId查询会员卡信息列表
     * @param vipId
     * @return java.util.List<com.it.swim.entity.VipCard>
     */
    List<VipCard> queryVipCardByVipId(long vipId);

    /*
     * @description: 新增会员卡信息
     * @param vipCard
     * @return int
     */
    int addVipCard(VipCard vipCard);

    /*
     * @description: 修改会员卡信息
     * @param vipCard
     * @return int
     */
    int modifyVipCard(VipCard vipCard);

    /*
     * @description: 通过vipCardId删除指定会员卡信息
     * @param vipCardId
     * @return int
     */
    int deleteVipCard(long vipCardId);
}
