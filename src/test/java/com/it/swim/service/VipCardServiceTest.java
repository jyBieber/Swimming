package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.Vip;
import com.it.swim.entity.VipCard;
import com.it.swim.enums.VipCardStateEnum;
import com.it.swim.enums.VipStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: VipCardService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VipCardServiceTest extends BaseTest {
    @Autowired
    private VipCardService vipCardService;

    @Test
    public void testAQueryVipCard(){
        List<VipCard> vipCardList = vipCardService.getVipCardList();
        assertEquals(3,vipCardList.size());
        System.out.println(vipCardList.size());
    }

    @Test
    public void testBQueryVipCardById(){
        VipCard vipCard = vipCardService.getVipCardById(1L);
        assertEquals("1",vipCard.getVip().getVipName());
        System.out.println(vipCard.getVip().getVipName());
    }

    @Test
    @Ignore
    public void testCAddVipCard() {
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

        VipCardExecution vipCardExecution = vipCardService.addVipCard(vipCard);
        assertEquals(VipCardStateEnum.SUCCESS.getState(),vipCardExecution.getState());
        System.out.println(vipCardExecution.getStateInfo());
    }

    @Test
    @Ignore
    public void testDModifyVipCard() {
        VipCard vipCard = new   VipCard();
        vipCard.setVipCardId(6L);
        vipCard.setState("已失效");

        VipCardExecution vipCardExecution = vipCardService.modifyVipCard(vipCard);
        assertEquals(VipCardStateEnum.SUCCESS.getState(),vipCardExecution.getState());
        System.out.println(vipCardExecution.getStateInfo());
    }

    @Test
    public void testEDeleteVipCard()  {
        VipCardExecution vipCardExecution2 = vipCardService.deleteVipCard(6L);
        assertEquals(VipStateEnum.SUCCESS.getState(),vipCardExecution2.getState());
        System.out.println(vipCardExecution2.getStateInfo());
    }
}
