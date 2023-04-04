package com.it.swim.service.Impl;

import com.it.swim.dao.CardRecordDao;
import com.it.swim.dto.CardRecordExecution;
import com.it.swim.entity.CardRecord;
import com.it.swim.enums.CardRecordStateEnum;
import com.it.swim.exception.CardRecordOperationException;
import com.it.swim.service.CardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: CardRecordService实现类
 */
@Service
public class CardRecordServiceImpl implements CardRecordService {
    @Autowired
    private CardRecordDao cardRecordDao;

    /*
     * @return   CardRecordExecution
     * @description: 查询游泳记录列表
     */
    @Override
    public List<CardRecord> getCardRecordList() {
        return cardRecordDao.queryCardRecord();
    }

    /*
     * @param cardRecordId
     * @return   CardRecordExecution
     * @description: 通过游泳记录Id查询唯一的游泳记录信息
     */
    @Override
    public CardRecord getCardRecordById(long cardRecordId) {
        return cardRecordDao.queryCardRecordById(cardRecordId);
    }

    /*
     * @param vipCardId
     * @return   CardRecordExecution
     * @description: 通过vipCardId查询游泳记录信息列表
     */
    @Override
    public List<CardRecord> getCardRecordByVipCardId(long vipCardId) {
        return cardRecordDao.queryCardRecordByVipCardId(vipCardId);
    }

    /*
     * @param vipId
     * @return   CardRecordExecution
     * @description: 通过vipId查询游泳记录信息列表
     */
    @Override
    public List<CardRecord> getCardRecordByVipId(long vipId) {
        return cardRecordDao.queryCardRecordByVipId(vipId);
    }

    /*
     * @param cardRecordId
     * @return   CardRecordExecution
     * @description: 通过cardRecordId查询会员信息列表
     */
    /*@Override
    public List<CardRecord> getVipByCardRecordId(long cardRecordId) {
        return cardRecordDao.queryVipByCardRecordId(cardRecordId);
    }*/

    /*
     * @param cardRecordId
     * @return   CardRecordExecution
     * @description: 通过cardRecordId查询会员卡信息列表
     */
   /* @Override
    public List<CardRecord> getCardByCardRecordId(long cardRecordId) {
        return cardRecordDao.queryCardByCardRecordId(cardRecordId);
    }
*/
    /*
     * @param cardRecord
     * @return   CardRecordExecution
     * @description: 新增游泳记录信息
     */
    @Override
    public CardRecordExecution addCardRecord(CardRecord cardRecord){
        //判断传入的游泳记录信息是否为空
        /*if (cardRecord != null) {
            try {
                //添加游泳记录信息
                int effectedNum = cardRecordDao.addCardRecord(cardRecord);
                //判断是否添加成功
                if (effectedNum <= 0) {
                    throw new CardRecordOperationException("游泳记录创建失败");
                } else {
                    return new CardRecordExecution(CardRecordStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new CardRecordOperationException("addCardRecord error: " + e.getMessage());
            }
        }else {
            return new CardRecordExecution(CardRecordStateEnum.EMPTY_LIST);
        }*/
        //空值判断
        if (cardRecord == null){
            return new CardRecordExecution(CardRecordStateEnum.EMPTY);
        }
        //设置上课时间
        //cardRecord.setCreateTime(new Date());
        //添加课程缴费信息
        int effectedNum = cardRecordDao.addCardRecord(cardRecord);
        //判断是否添加成功
        if (effectedNum <= 0){
            throw new CardRecordOperationException("添加游泳记录信息失败");
        }
        return new CardRecordExecution(CardRecordStateEnum.SUCCESS,cardRecord);
    }

    /*
     * @param cardRecord
     * @return   CardRecordExecution
     * @description: 修改游泳记录信息
     */
    @Override
    public CardRecordExecution modifyCardRecord(CardRecord cardRecord){
        //空值判断
        if (cardRecord != null && cardRecord.getCardRecordId() != null) {
            try {
                //修改游泳记录信息
                int effectedNum = cardRecordDao.modifyCardRecord(cardRecord);
                //判断是否修改成功
                if (effectedNum <= 0) {
                    throw new CardRecordOperationException("更新游泳记录信息失败");
                }
                return new CardRecordExecution(CardRecordStateEnum.SUCCESS, cardRecord);
            } catch (Exception e) {
                throw new CardRecordOperationException("更新游泳记录信息失败:" + e.toString());
            }
        } else {
            return new CardRecordExecution(CardRecordStateEnum.EMPTY);
        }
    }

    /*
     * @param cardRecordId
     * @return   CardRecordExecution
     * @description: 删除指定游泳记录
     */
    @Override
    public CardRecordExecution deleteCardRecord(long cardRecordId){
        try {
            //删除该游泳记录信息
            int effectedNum = cardRecordDao.deleteCardRecord(cardRecordId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new CardRecordOperationException("游泳记录信息删除失败");
            } else {
                return new CardRecordExecution(CardRecordStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new CardRecordOperationException("deleteCardRecord error:" + e.getMessage());
        }
    }
}
