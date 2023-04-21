package com.it.swim.service.Impl;

import com.it.swim.dao.CardRecordDao;
import com.it.swim.dao.VipCardDao;
import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.VipCard;
import com.it.swim.enums.VipCardStateEnum;
import com.it.swim.exception.VipCardOperationException;
import com.it.swim.service.VipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/*
 * @description: VipCardService实现类
 */
@Service
public class VipCardServiceImpl implements VipCardService {
    @Autowired
    private VipCardDao vipCardDao;
    @Autowired
    private CardRecordDao cardRecordDao;

    /*
     * @return java.util.List<com.it.swim.entity.VipCard>
     * @description: 查询全部会员卡信息
     */
    @Override
    public List<VipCard> getVipCardList() {
        List<VipCard> vipCards = vipCardDao.queryVipCard();
        return getSurplusNum(vipCards);
    }

    /*
     * @param vipCardId
     * @return com.it.swim.entity.VipCard
     * @description: 通过会员卡ID获取指定会员卡信息
     */
    @Override
    public VipCard getVipCardById(long vipCardId) {
        return getSurplusNum(vipCardDao.queryVipCardById(vipCardId));
    }

    /*
     * @param vipId
     * @return com.it.swim.entity.VipCard
     * @description: 通过会员ID获取指定会员卡信息
     */
    @Override
    public List<VipCard> getVipCardByVipId(long vipId) {
        List<VipCard> vipCards = vipCardDao.queryVipCardByVipId(vipId);
        return getSurplusNum(vipCards);
    }

    /*
     * @param vipCard
     * @return com.it.swim.dto.VipCardExecution
     * @description: 新增会员卡信息
     */
    @Override
    public VipCardExecution addVipCard(VipCard vipCard) {
        //空值判断
        if (vipCard == null) {
            return new VipCardExecution(VipCardStateEnum.EMPTY);
        }
        //添加会员卡信息
        int effectedNum = vipCardDao.addVipCard(vipCard);
        //判断是否添加成功
        if (effectedNum <= 0) {
            throw new VipCardOperationException("添加会员卡信息失败");
        }
        return new VipCardExecution(VipCardStateEnum.SUCCESS, vipCard);
    }

    /*
     * @param vipCard
     * @return com.it.swim.dto.VipCardExecution
     * @description: 修改会员卡信息
     */
    @Override
    public VipCardExecution modifyVipCard(VipCard vipCard) {
        //空值判断
        if (vipCard == null || vipCard.getVipCardId() == null) {
            return new VipCardExecution(VipCardStateEnum.EMPTY);
        }
        //修改会员卡信息
        int effectedNum = vipCardDao.modifyVipCard(vipCard);
        //判断是否修改成功
        if (effectedNum <= 0) {
            throw new VipCardOperationException("修改会员卡信息失败");
        }
        return new VipCardExecution(VipCardStateEnum.SUCCESS, vipCard);
    }

    /*
     * @param vipCardId
     * @return com.it.swim.dto.VipCardExecution
     * @description: 删除指定会员卡信息
     */
    @Override
    public VipCardExecution deleteVipCard(long vipCardId) {
        //删除该会员卡信息
        int effectedNum = vipCardDao.deleteVipCard(vipCardId);
        //判断是否删除成功
        if (effectedNum <= 0) {
            throw new VipCardOperationException("会员卡信息删除失败");
        } else {
            return new VipCardExecution(VipCardStateEnum.SUCCESS);
        }
    }

    /*
     * @param vipCards
     * @return List<VipCard>
     * @description: 获取已用次数和剩余次数
     */
    private List<VipCard> getSurplusNum(List<VipCard> vipCards) {
        if (!CollectionUtils.isEmpty(vipCards)) {
            vipCards.forEach(this::getSurplusNum);
        }
        return vipCards;
    }

    /*
     * @param vipCards
     * @return List<VipCard>
     * @description: 获取已用次数和剩余次数
     */
    private VipCard getSurplusNum(VipCard vipCard) {
        vipCard.setUseNum(cardRecordDao.countByCardId(vipCard.getVipCardId()));
        if (!"年卡".equals(vipCard.getType())) {
            vipCard.setSurplusNum(vipCard.getNum() - vipCard.getUseNum());
        }else{
            vipCard.setSurplusNum(1000);
        }
        return vipCard;
    }
}
