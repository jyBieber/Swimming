package com.it.swim.service;

import com.it.swim.dto.CardRecordExecution;
import com.it.swim.entity.CardRecord;
import com.it.swim.entity.VipCard;

import java.util.List;

public interface CardRecordService {
    /*
     * @description: 查询全部游泳记录信息
     * @param
     * @return java.util.List< CardRecord>
     */
    List<CardRecord> getCardRecordList();
    
    /*
     * @description: 通过游泳记录Id查询唯一的游泳记录信息
     * @param cardRecordId
     * @return java.util.List< CardRecord>
     */
    CardRecord getCardRecordById(long cardRecordId);

    /*
     * @description: 通过vipCardId查询游泳记录信息列表
     * @param vipCardId
     * @return java.util.List<CardRecord>
     */
    // List<CardRecord> getCardRecordByVipCardId(long vipCardId);

    /*
     * @description: 通过vipId查询游泳记录信息列表
     * @param vipId
     * @return java.util.List<CardRecord>
     */
    List<CardRecord> getCardRecordByVipId(long vipId);

    /*
     * @description: 新增游泳记录信息
     * @param cardRecord
     * @return  CardRecordExecution
     */
    CardRecordExecution addCardRecord(CardRecord cardRecord);

    /*
     * @description: 修改游泳记录信息
     * @param cardRecord
     * @return  CardRecordExecution
     */
    CardRecordExecution modifyCardRecord(CardRecord cardRecord);

    /*
     * @description: 删除指定游泳记录
     * @param cardRecordId
     * @return  CardRecordExecution
     */
    CardRecordExecution deleteCardRecord(long cardRecordId);
}
