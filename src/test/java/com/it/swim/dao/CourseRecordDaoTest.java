package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.CourseRecord;
import com.it.swim.entity.Vip;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CourseRecordDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseRecordDaoTest extends BaseTest {
    @Autowired
    private CourseRecordDao courseRecordDao;

    @Test
    public void testAQueryCourseRecord(){
        List<CourseRecord> courseRecordList = courseRecordDao.queryCourseRecord();
        assertEquals(3,courseRecordList.size());
        System.out.println(courseRecordList.size());
    }

    @Test
    public void testBQueryCourseRecordById(){
        CourseRecord courseRecord = courseRecordDao.queryCourseRecordById(1L);
        System.out.println(courseRecord.getCoursePay().getVip().getVipName());
    }

    @Test
    public void testCAddCourseRecord(){
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseRecordId(33L);
        CoursePay coursePay = new CoursePay();
        coursePay.setCoursePayId(2L);
        courseRecord.setCoursePay(coursePay);
        Coach coach = new Coach();
        coach.setCoachId(30003L);
        courseRecord.setCoach(coach);
        coursePay.setCreateTime(new Date());
        courseRecord.setIsAttend(0);

        /*courseRecord.setCreateTime(new Date());*/
        int effectedNum = courseRecordDao.addCourseRecord(courseRecord);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testDModifyCourseRecord(){
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseRecordId(33L);
        courseRecord.setIsAttend(1);

        int effectedNum = courseRecordDao.modifyCourseRecord(courseRecord);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testEDeleteCourseRecord(){
        int effectedNum = courseRecordDao.deleteCourseRecord(33L);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }
}
