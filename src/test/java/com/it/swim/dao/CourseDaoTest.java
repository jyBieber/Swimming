package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CoursePay;
import com.it.swim.entity.Course;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CourseDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseDaoTest extends BaseTest {
    @Autowired
    private CourseDao courseDao;

    @Test
    public void testAQueryCourse(){
        List<Course> courseList = courseDao.queryCourse();
        assertEquals(3,courseList.size());
        System.out.println(courseList.size());
    }

    @Test
    public void testBQueryCourseById(){
        Course course = courseDao.queryCourseById(1L);
        System.out.println(course.getCoach().getCoachName());
    }

    @Test
    public void testCAddCourse(){
        Course course = new Course();
        course.setCourseId(33L);
        Coach coach = new Coach();
        coach.setCoachId(30003L);
        course.setCoach(coach);
        course.setCreateTime(new Date());

        /*course.setCreateTime(new Date());*/
        int effectedNum = courseDao.addCourse(course);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testDModifyCourse(){
        Course course = new Course();
        course.setCourseId(33L);
        course.setCreateTime(new Date());

        int effectedNum = courseDao.modifyCourse(course);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testEDeleteCourse(){
        int effectedNum = courseDao.deleteCourse(33L);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }
}
