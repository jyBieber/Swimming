package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.CardRecordExecution;
import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.*;
import com.it.swim.enums.CardRecordStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @description: CardRecordService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardRecordServiceTest extends BaseTest {
    @Autowired
    private CardRecordService cardRecordService;

    @Test
    public void testAQueryCardRecord(){
        List<CardRecord> classList = cardRecordService.getCardRecordList();
        assertEquals(2,classList.size());
        System.out.println(classList.size());
    }

    @Test
    public void testBQueryCardRecordById(){
        CardRecord cardRecord = cardRecordService.getCardRecordById(1L);
        assertEquals("1",cardRecord.getVipCard().getVip().getVipName());
        System.out.println(cardRecord.getVipCard().getVip().getVipName());
    }

    @Test
    public void testCAddCardRecord(){
        CardRecord cardRecord = new CardRecord();
        cardRecord.setCardRecordId(18L);
        VipCard vipCard = new VipCard();
        vipCard.setVipCardId(10L);
        cardRecord.setCreateTime(new Date());
        
        CardRecordExecution cardRecordExecution = cardRecordService.addCardRecord(cardRecord);
        assertEquals(CardRecordStateEnum.SUCCESS.getState(),cardRecordExecution.getState());
        System.out.println(cardRecordExecution.getStateInfo());
    }

    // @Test
    // public void testDUpdateCardRecord(){
    //     CardRecord cardRecord = new CardRecord();
    //     cardRecord.setCardRecordId(18L);
    //     cardRecord.setCardRecordNumber(80);
    //     cardRecord.setCardRecordName("泳镜1");
    //     CardRecordExecution cardRecordExecution = cardRecordService.modifyCardRecord(cardRecord);
    //     assertEquals(CardRecordStateEnum.SUCCESS.getState(),cardRecordExecution.getState());
    //     System.out.println(cardRecordExecution.getStateInfo());
    // }

    @Test
    public void testEDeleteCardRecord(){
        CardRecordExecution cardRecordExecution = cardRecordService.deleteCardRecord(18L);
        assertEquals(CardRecordStateEnum.SUCCESS.getState(),cardRecordExecution.getState());
        System.out.println(cardRecordExecution.getStateInfo());
    }
}
