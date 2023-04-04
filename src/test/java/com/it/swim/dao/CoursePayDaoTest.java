package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.Vip;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CoursePayDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoursePayDaoTest extends BaseTest {
    @Autowired
    private CoursePayDao coursePayDao;

    @Test
    public void testAQueryCoursePay(){
        List<CoursePay> coursePayList = coursePayDao.queryCoursePay();
        assertEquals(5, coursePayList.size());
        System.out.println(coursePayList.size());
    }

    @Test
    public void testBQueryCoursePayById(){
        CoursePay coursePay = coursePayDao.queryCoursePayById(1L);
        System.out.println(coursePay.getVip().getVipName());
    }

    @Test
    @Ignore
    public void testCAddCoursePay(){
        CoursePay coursePay = new CoursePay();
        coursePay.setCoursePayId(23L);
        Vip vip = new Vip();
        vip.setVipId(10002L);
        coursePay.setVip(vip);
        coursePay.setType(4);
        coursePay.setAmount(5000);
        coursePay.setNum(10);
        coursePay.setPoints(0);
        coursePay.setIsRedeem(0);
        coursePay.setRedeemNum(0);
        coursePay.setCreateTime(new Date());
        coursePay.setExpireTime(new Date());
        int effectedNum = coursePayDao.addCoursePay(coursePay);
        assertEquals(1,effectedNum);
        System.out.println(coursePay);
    }

    @Test
    public void testDModifyCoursePay(){
        CoursePay coursePay = new CoursePay();
        coursePay.setCoursePayId(23L);
        coursePay.setNum(12);
        coursePay.setIsRedeem(1);
        coursePay.setRedeemNum(2);

        int effectedNum = coursePayDao.modifyCoursePay(coursePay);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testEDeleteCoursePay(){
        int effectedNum = coursePayDao.deleteCoursePay(23L);
        assertEquals(1,effectedNum);
    }
}
