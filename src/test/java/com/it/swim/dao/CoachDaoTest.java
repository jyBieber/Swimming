package com.it.swim.dao;

import com.it.swim.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CoachDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoachDaoTest extends BaseTest {
    @Autowired
    private CoachDao coachDao;

    @Test
    public void testAQueryCoach(){
        List<com.it.swim.entity.Coach> coachList = coachDao.queryCoach();
        assertEquals(2, coachList.size());
        System.out.println(coachList.size());
    }

    @Test
    public void testBQueryCoachById(){
        com.it.swim.entity.Coach coach = coachDao.queryCoachById(11L);
        System.out.println(coach.getCoachName());
    }

    @Test
    public void testCAddCoach(){
        com.it.swim.entity.Coach coach = new com.it.swim.entity.Coach();
        coach.setCoachId(33L);
        coach.setCoachName("张丽");
        coach.setProfileImg(null);
        coach.setGender("女");
        coach.setAge(22);
        coach.setPhone("18841774322");
        coach.setEmail("zhangli1@163.cn");
        coach.setRemarks("仰泳、自由泳");
        int effectedNum = coachDao.addCoach(coach);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testDModifyCoach(){
        com.it.swim.entity.Coach coach = new com.it.swim.entity.Coach();
        coach.setCoachId(33L);
        coach.setCoachName("张梅");
        /*coach.setLastEditTime(new Date());*/
        int effectedNum = coachDao.modifyCoach(coach);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testEDeleteCoach(){
        coachDao.deleteCoach(33L);
    }
}
