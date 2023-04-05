package com.it.swim.service;

import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.VipCard;

import java.util.List;

public interface VipCardService {
    /*
     * @description: 查询全部会员卡信息
     * @param
     * @return java.util.List<com.it.swim.entity.VipCard>
     */
    List<VipCard> getVipCardList();

    /*
     * @description: 通过会员卡ID获取指定会员卡信息
     * @param vipCardId
     * @return com.it.swim.entity.VipCard
     */
    VipCard getVipCardById(long vipCardId);

    /*
     * @description: 通过会员ID获取指定会员卡信息
     * @param vipId
     * @return com.it.swim.entity.VipCard
     */
    List<VipCard> getVipCardByVipId(long vipId);

    /*
     * @description: 新增会员卡信息
     * @param vipCard
     * @return com.it.swim.dto.VipCardExecution
     */
    VipCardExecution addVipCard(VipCard vipCard);

    /*
     * @description: 修改会员卡信息
     * @param vipCard
     * @return com.it.swim.dto.VipCardExecution
     */
    VipCardExecution modifyVipCard(VipCard vipCard);

    /*
     * @description: 删除指定会员卡信息
     * @param vipCardId
     * @return com.it.swim.dto.VipCardExecution
     */
    VipCardExecution deleteVipCard(long vipCardId);
}
