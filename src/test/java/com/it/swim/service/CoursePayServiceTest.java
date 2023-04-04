package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.CoursePayExecution;
import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.CourseRecord;
import com.it.swim.entity.Vip;
import com.it.swim.enums.CoursePayStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CoursePayService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoursePayServiceTest extends BaseTest {
    @Autowired
    private CoursePayService coursePayService;

    @Test
    public void testAQueryCoursePay(){
        List<CoursePay> coursePayList = coursePayService.getCoursePayList();
        assertEquals(2,coursePayList.size());
        System.out.println(coursePayList.size());
    }

    @Test
    public void testBQueryCoursePayById(){
        CoursePay coursePay = coursePayService.getCoursePayById(2L);
        assertEquals("1",coursePay.getVip().getVipName());
        System.out.println(coursePay.getVip().getVipName());
    }

    @Test
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

        CoursePayExecution coursePayExecution = coursePayService.addCoursePay(coursePay);
        assertEquals(CoursePayStateEnum.SUCCESS.getState(), coursePayExecution.getState());
        System.out.println(coursePayExecution.getStateInfo());
    }

    @Test
    public void testDModifyCoursePay(){
        CoursePay coursePay = new CoursePay();
        coursePay.setCoursePayId(6L);
        coursePay.setNum(12);
        coursePay.setIsRedeem(1);
        coursePay.setRedeemNum(2);

        CoursePayExecution coursePayExecution = coursePayService.modifyCoursePay(coursePay);
        assertEquals(CoursePayStateEnum.SUCCESS.getState(), coursePayExecution.getState());
        System.out.println(coursePayExecution.getStateInfo());
    }

    @Test
    public void testEDeleteCoursePay(){
        CoursePayExecution coursePayExecution = coursePayService.deleteCoursePay(6L);
        assertEquals(CoursePayStateEnum.SUCCESS.getState(), coursePayExecution.getState());
        System.out.println(coursePayExecution.getStateInfo());
    }
}
