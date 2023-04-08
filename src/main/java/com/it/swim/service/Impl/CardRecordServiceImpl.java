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
     * @return java.util.List<com.it.swim.entity.CardRecord>
     * @description: 查询全部游泳记录信息
     */
    @Override
    public List<CardRecord> getCardRecordList() {
        return cardRecordDao.queryCardRecord();
    }

    /*
     * @param cardRecordId
     * @return com.it.swim.entity.CardRecord
     * @description: 通过游泳记录ID获取指定游泳记录信息
     */
    @Override
    public CardRecord getCardRecordById(long cardRecordId) {
        return cardRecordDao.queryCardRecordById(cardRecordId);
    }

    /*
     * @param vipId
     * @return java.util.List<com.it.swim.entity.CardRecord>
     * @description: 通过vipId查询游泳记录信息列表
     */
    @Override
    public List<CardRecord> getCardRecordByVipId(long vipId) {
        return cardRecordDao.queryCardRecordByVipId(vipId);
    }

    /*
     * @param courseId
     * @return com.it.swim.entity.CardRecord
     * @description: 通过课程ID获取学生列表信息
     */
    /*@Override
    public List<CardRecord> getVipByCourseId(long courseId) {
        return cardRecordDao.queryVipByCourseId(courseId);
    }*/

    /*
     * @param cardRecord
     * @return com.it.swim.dto.CardRecordExecution
     * @description: 新增游泳记录信息
     */
    @Override
    public CardRecordExecution addCardRecord(CardRecord cardRecord) {
        //空值判断
        if (cardRecord == null){
            return new CardRecordExecution(CardRecordStateEnum.EMPTY);
        }


        //给游泳记录信息赋初始值
        //cardRecord.setCreateTime(new Date());
        //添加游泳记录信息
        int effectedNum = cardRecordDao.addCardRecord(cardRecord);
        //判断是否添加成功
        if (effectedNum <= 0){
            throw new CardRecordOperationException("添加游泳记录信息失败");
        }
        return new CardRecordExecution(CardRecordStateEnum.SUCCESS,cardRecord);
    }

    /*
     * @param cardRecord
     * @return com.it.swim.dto.CardRecordExecution
     * @description: 修改游泳记录信息
     */
    @Override
    public CardRecordExecution modifyCardRecord(CardRecord cardRecord) {
        //空值判断
        if (cardRecord == null || cardRecord.getCardRecordId() == null){
            return new CardRecordExecution(CardRecordStateEnum.EMPTY);
        }
        //给游泳记录信息赋初始值
        //cardRecord.setLastEditTime(new Date());
        //修改游泳记录信息
        int effectedNum = cardRecordDao.modifyCardRecord(cardRecord);
        //判断是否修改成功
        if (effectedNum <= 0){
            throw new CardRecordOperationException("修改游泳记录信息失败");
        }
        return new CardRecordExecution(CardRecordStateEnum.SUCCESS,cardRecord);
    }

    /*
     * @param cardRecordId
     * @return com.it.swim.dto.CardRecordExecution
     * @description: 删除指定游泳记录信息
     */
    @Override
    public CardRecordExecution deleteCardRecord(long cardRecordId) {
        //删除该游泳记录信息
        int effectedNum = cardRecordDao.deleteCardRecord(cardRecordId);
        //判断是否删除成功
        if (effectedNum <= 0){
            throw new CardRecordOperationException("游泳记录信息删除失败");
        }else {
            return new CardRecordExecution(CardRecordStateEnum.SUCCESS);
        }
    }
}

