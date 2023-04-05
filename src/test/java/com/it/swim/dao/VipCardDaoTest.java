package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.CardRecord;
import com.it.swim.entity.Vip;
import com.it.swim.entity.VipCard;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: VipCardDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VipCardDaoTest extends BaseTest {
    @Autowired
    private VipCardDao vipCardDao;

    @Test
    public void testAQueryVipCard(){
        List<VipCard> vipCardList = vipCardDao.queryVipCard();
        assertEquals(2, vipCardList.size());
        System.out.println(vipCardList.size());
    }

    @Test
    public void testBQueryVipCardById(){
        VipCard vipCard = vipCardDao.queryVipCardById(1L);
        System.out.println(vipCard.getVip().getVipName());
    }

    @Test
    public void testCAddVipCard(){
        VipCard vipCard = new VipCard();
        vipCard.setVipCardId(10L);
        Vip vip = new Vip();
        vip.setVipId(10002L);
        vipCard.setVip(vip);
        vipCard.setType("年卡");
        vipCard.setAmount(5000);
        vipCard.setNum(0);
        vipCard.setState("已激活");
        vipCard.setCreateTime(new Date());
        vipCard.setExpireTime(new Date());

        int effectedNum = vipCardDao.addVipCard(vipCard);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDModifyVipCard(){
        VipCard vipCard = new com.it.swim.entity.VipCard();
        vipCard.setVipCardId(10L);
        vipCard.setState("已失效");

        int effectedNum = vipCardDao.modifyVipCard(vipCard);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testEDeleteVipCard(){
        int effectedNum = vipCardDao.deleteVipCard(10L);
        assertEquals(1,effectedNum);
    }
}
