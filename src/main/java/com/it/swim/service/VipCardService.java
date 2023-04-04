package com.it.swim.service;

import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.VipCard;

import java.util.List;

public interface VipCardService {
    /*
     * @description: 查询全部会员卡列表信息
     * @param
     * @return java.util.List< VipCard>
     */
    List<VipCard> getVipCardList();

    /*
     * @description: 通过会员卡ID获取指定会员卡信息
     * @param vipCardId
     * @return  VipCard
     */
     VipCard getVipCardById(long vipCardId);
    /*
     * @description: 通过vipId查询会员卡信息列表
     * @param vipId
     * @return java.util.List<VipCard>
     */
    List<VipCard> getVipCardByVipId(long vipId);

    /*
     * @description: 通过CardId查询会员信息列表
     * @param vipId
     * @return java.util.List<VipCard>
     */
    /*List<VipCard> getVipByCardId(long CardId);*/

    /*
     * @description: 新增会员卡信息
     * @param vipCard
     * @param fileHolder
     * @return  VipCardExecution
     */
    VipCardExecution addVipCard(VipCard vipCard);

    /*
     * @description: 修改会员卡信息
     * @param vipCard
     * @param fileHolder
     * @return  VipCardExecution
     */
    VipCardExecution modifyVipCard(VipCard vipCard);

    /*
     * @description: 删除指定会员卡信息
     * @param vipCardId
     * @return  VipCardExecution
     */
    VipCardExecution deleteVipCard(long vipCardId);
}
