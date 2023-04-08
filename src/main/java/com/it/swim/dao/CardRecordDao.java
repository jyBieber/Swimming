package com.it.swim.dao;

import com.it.swim.entity.CardRecord;

import java.util.List;

/*
 * @description: 游泳记录实体类dao层接口
 */
public interface CardRecordDao {
    /*
     * @description: 查询所有游泳记录列表
     * @param 
     * @return java.util.List< CardRecord>
     */
    List<CardRecord> queryCardRecord();
    
    /*
     * @description: 通过cardRecordId查询指定游泳记录信息
     * @param cardRecordId
     * @return  CardRecord
     */
     CardRecord queryCardRecordById(long cardRecordId);

    /*
     * @description: 通过vipId查询游泳记录信息列表
     * @param vipCardId
     * @return java.util.List<CardRecord>
     */
    List<CardRecord> queryCardRecordByVipCardId(long vipCardId);

    /*
     * @description: 通过vipId查询游泳记录信息列表
     * @param vipId
     * @return java.util.List<CardRecord>
     */
    List<CardRecord> queryCardRecordByVipId(long vipId);
    
    /*
     * @description: 新增游泳记录信息
     * @param cardRecord
     * @return int
     */
    int addCardRecord(CardRecord cardRecord);
    
    /*
     * @description: 修改游泳记录信息
     * @param cardRecord
     * @return int
     */
    int modifyCardRecord(CardRecord cardRecord);
    
    /*
     * @description: 通过cardRecordId删除指定游泳记录信息
     * @param cardRecordId
     * @return int
     */
    int deleteCardRecord(long cardRecordId);
    /*
     * @description: 通过vipCardId查询游泳记录信息次数
     * @param vipCardId
     * @return Integer
     */
    Integer countByCardId(Long vipCardId);
}
