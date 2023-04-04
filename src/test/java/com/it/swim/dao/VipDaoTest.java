package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.Vip;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: VipDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VipDaoTest extends BaseTest {
    @Autowired
    private VipDao vipDao;

    @Test
    public void testAQueryVip(){
        List<Vip> vipList = vipDao.queryVip();
        assertEquals(3, vipList.size());
        System.out.println(vipList.size());
    }

    @Test
    public void testBQueryVipById(){
        Vip vip = vipDao.queryVipById(11L);
        System.out.println(vip.getVipName());
    }

    @Test
    public void testCAddVip(){
        Vip vip = new Vip();
        vip.setVipId(10003L);
        vip.setVipName("小明");
        vip.setProfileImg("test");
        vip.setGender("男");
        vip.setAge(22);
        vip.setPhone("18841774322");
        vip.setEmail("xiaoming@163.cn");
        vip.setRemarks("无");
        int effectedNum = vipDao.addVip(vip);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDModifyVip(){
        Vip vip = new Vip();
        /*CardRecord cardRecord = new CardRecord();
        cardRecord.setCardRecordId(1702L);*/
        vip.setVipId(10003L);
        vip.setVipName("小张");
        /*vip.setCardRecord(cardRecord);*/
        /*vip.setLastEditTime(new Date());*/

        int effectedNum = vipDao.modifyVip(vip);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testEDeleteVip(){
        int effectedNum = vipDao.deleteVip(33L);
        assertEquals(1,effectedNum);
    }
}
