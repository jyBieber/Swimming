package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.CoursePayExecution;
import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.Vip;
import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CourseRecordService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseRecordServiceTest extends BaseTest {
    @Autowired
    private CourseRecordService courseRecordService;

    @Test
    public void testAQueryCourseRecord(){
        List<CourseRecord> courseRecordList = courseRecordService.getCourseRecordList();
        assertEquals(3,courseRecordList.size());
        System.out.println(courseRecordList.size());
    }

    @Test
    public void testBQueryCourseRecordById(){
        CourseRecord courseRecord = courseRecordService.getCourseRecordById(1L);
        assertEquals("1",courseRecord.getCoursePay().getVip().getVipName());
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

        CourseRecordExecution courseRecordExecution = courseRecordService.addCourseRecord(courseRecord);
        assertEquals(CourseRecordStateEnum.SUCCESS.getState(),courseRecordExecution.getState());
        System.out.println(courseRecordExecution.getStateInfo());
    }

    @Test
    public void testDModifyCourseRecord(){
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseRecordId(14L);
        courseRecord.setIsAttend(1);

        CourseRecordExecution courseRecordExecution = courseRecordService.modifyCourseRecord(courseRecord);
        assertEquals(CourseRecordStateEnum.SUCCESS.getState(),courseRecordExecution.getState());
        System.out.println(courseRecordExecution.getStateInfo());
    }

    @Test
    public void testEDeleteCourseRecord(){
        CourseRecordExecution courseRecordExecution = courseRecordService.deleteCourseRecord(14L);
        assertEquals(CourseRecordStateEnum.SUCCESS.getState(),courseRecordExecution.getState());
        System.out.println(courseRecordExecution.getStateInfo());
    }
}
