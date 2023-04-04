package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.CardRecord;
import com.it.swim.entity.VipCard;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CardRecordDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardRecordDaoTest extends BaseTest {
    @Autowired
    private CardRecordDao cardRecordDao;

    @Test
    public void testAQueryCardRecord(){
        List<CardRecord> cardRecordList = cardRecordDao.queryCardRecord();
        assertEquals(5, cardRecordList.size());
        System.out.println(cardRecordList.size());
        System.out.println(cardRecordList);
    }

    @Test
    public void testBQueryCardRecordById(){
        CardRecord cardRecord = cardRecordDao.queryCardRecordById(1701L);
        System.out.println(cardRecord.getVipCard().getVip().getVipName());
    }

    @Test
    public void testCAddCardRecord(){
        CardRecord cardRecord = new CardRecord();
        cardRecord.setCardRecordId(18L);
        VipCard vipCard = new VipCard();
        vipCard.setVipCardId(10L);
        cardRecord.setCreateTime(new Date());
        int effectedNum = cardRecordDao.addCardRecord(cardRecord);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    // @Test
    // public void testDModifyCardRecord(){
    //     CardRecord cardRecord = new CardRecord();
    //     cardRecord.setCardRecordId(2L);
    //     cardRecord.setCardRecordName("泳镜");
    //     cardRecord.setCardRecordNumber(80);
    //     cardRecord.setPrice(250);
    //     int effectedNum = cardRecordDao.modifyCardRecord(cardRecord);
    //     assertEquals(1,effectedNum);
    //     System.out.println(effectedNum);
    // }

    @Test
    public void testEDeleteCardRecord(){
        cardRecordDao.deleteCardRecord(18L);
    }
}
