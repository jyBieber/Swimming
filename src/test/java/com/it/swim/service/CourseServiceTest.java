package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.CourseExecution;
import com.it.swim.entity.Course;
import com.it.swim.entity.Coach;
import com.it.swim.enums.CourseStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: CourseService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceTest extends BaseTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void testAQueryCourse(){
        List<Course> courseList = courseService.getCourseList(null);
        assertEquals(3,courseList.size());
        System.out.println(courseList.size());
    }

    @Test
    public void testBQueryCourseById(){
        Course course = courseService.getCourseById(1L);
        assertEquals("1",course.getCoach().getCoachName());
        System.out.println(course.getCoach().getCoachName());
    }

    @Test
    public void testCAddCourse(){
        Course course = new Course();
        course.setCourseId(53L);
        Coach coach = new Coach();
        coach.setCoachId(30003L);
        course.setCoach(coach);
        course.setCreateTime(new Date());

        CourseExecution courseExecution = courseService.addCourse(course);
        assertEquals(CourseStateEnum.SUCCESS.getState(),courseExecution.getState());
        System.out.println(courseExecution.getStateInfo());
    }

    @Test
    public void testDModifyCourse(){
        Course course = new Course();
        course.setCourseId(14L);
        course.setCreateTime(new Date());

        CourseExecution courseExecution = courseService.modifyCourse(course);
        assertEquals(CourseStateEnum.SUCCESS.getState(),courseExecution.getState());
        System.out.println(courseExecution.getStateInfo());
    }

    @Test
    public void testEDeleteCourse(){
        CourseExecution courseExecution = courseService.deleteCourse(14L);
        assertEquals(CourseStateEnum.SUCCESS.getState(),courseExecution.getState());
        System.out.println(courseExecution.getStateInfo());
    }
}
